/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.processor;

import com.phloc.css.ECSSUnit;
import com.phloc.css.ECSSVersion;
import com.phloc.css.ICSSWriterSettings;
import com.phloc.css.decl.*;
import com.phloc.css.decl.visit.CSSVisitor;
import com.phloc.css.decl.visit.DefaultCSSVisitor;
import com.phloc.css.utils.CSSNumberHelper;
import com.phloc.css.writer.CSSWriterSettings;
import com.thoughtworks.xstream.XStream;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.css.CSSOMRule;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.ruleimplementation.RuleHelper;
import org.opens.tanaguru.service.ProcessRemarkService;
import org.w3c.css.sac.LexicalUnit;

/**
 *
 * @author jkowalczyk
 */
public class CSSHandlerPhlocImpl implements CSSHandler {

    private boolean initialized = false;
    private int selectedRuleSize = 0;
    private SSP ssp;
    private Map<String, CascadingStyleSheet> styleMap;
    private static final String CSS_ON_ERROR = "CSS_ON_ERROR";
    private static final String INLINE_CSS_URL_FORMAT = "#tanaguru-css-";
    private static final String INLINE_CSS = "inline";
    private Set<StylesheetContent> cssOnErrorSet;
    private static final String BAD_UNIT_TYPE_MSG_CODE = "BadUnitType";
    private static final String UNTESTED_RESOURCE_MSG_CODE = "UnTestedResource";
    private static final Pattern NON_ZERO_VALUE_PATTERN =
            Pattern.compile("0+\\.0+[a-zA-Z]*|0+[a-zA-Z]*");
    Collection<String> mediaNames;
    
    private ProcessRemarkService processRemarkService;
    @Override
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }

    public CSSHandlerPhlocImpl() {
        super();
    }

    public CSSHandlerPhlocImpl(SSP ssp) {
        this.ssp = ssp;
    }

    private void addSourceCodeRemark(
            TestSolution processResult,
            String selectorValue, 
            String messageCode, 
            String attrName,
            String fileName) {// XXX
        processRemarkService.addCssCodeRemark(
                processResult,
                selectorValue,
                messageCode,
                attrName,
                fileName);
    }

    private void initialize() {
        if (initialized) {
            return;
        }
        initializeStyleSet();
        initializeCssOnErrorSet();
        XStream xstream = new XStream();
        for (RelatedContent relatedContent : ssp.getRelatedContentSet()) {
            if (relatedContent instanceof StylesheetContent) {
                if (((StylesheetContent) relatedContent).getHttpStatusCode() == HttpStatus.SC_OK
                        && ((StylesheetContent) relatedContent).getAdaptedContent() != null
                        && !((StylesheetContent) relatedContent).getAdaptedContent().startsWith(CSS_ON_ERROR)) {
                    styleMap.put(((Content) relatedContent).getURI(),
                                (CascadingStyleSheet) xstream.fromXML(
                                ((StylesheetContent) relatedContent).getAdaptedContent()));
                } else {
                    addStylesheetOnError((StylesheetContent) relatedContent);
                }
            }
        }
        if (mediaNames == null) {
            mediaNames = new ArrayList<String>();
        }
        mediaNames.clear();
        selectedRuleSize = 0;
        initialized = true;
    }
    
        /**
     * This method initializes or re-initializes the set of stylesheets on error
     * related with a css
     */
    private void initializeCssOnErrorSet() {
        if (cssOnErrorSet == null) {
            cssOnErrorSet = new HashSet<StylesheetContent>();
        }
        cssOnErrorSet.clear();
    }

    /**
     * This method initializes or re-initializes the set of stylesheets related
     * with a css
     */
    private void initializeStyleSet() {
        if (styleMap == null) {
            styleMap = new HashMap<String, CascadingStyleSheet>();
        }
        styleMap.clear();
    }
    
    public void addStylesheetOnError(StylesheetContent css) {
        cssOnErrorSet.add(css);
    }
    
    @Override
    public CSSHandler beginSelection() {
        initialize();
        processRemarkService.initializeService(null, null);
        return this;
    }

    @Override
    public TestSolution checkRelativeUnitExists(Collection<Integer> blacklist) {
        
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        
        int globalSelectorCounter=0;
        int effectiveSelectorCounter=0;
        
        for (Map.Entry<String, CascadingStyleSheet> entry : styleMap.entrySet()) {
            CheckUnitCSSVisitor visitor = new CheckUnitCSSVisitor(
                    mediaNames, 
                    getFileNameFromCssName(entry.getKey()), 
                    getECSSUnitListFromLexicalUnitBlackList(blacklist));
            CSSVisitor.visitCSS(entry.getValue(), visitor);
            if (visitor.hasOneResultOnError()) {
                resultSet.add(TestSolution.FAILED);
            }
            globalSelectorCounter+=visitor.getGlobalSelectorCounter();
            effectiveSelectorCounter+=visitor.getEffectiveSelectorCounter();
        }
        
        // if no effective selectors have been encountered (regarding the media
        // selection, the test is not applicable
        if (effectiveSelectorCounter == 0) {
            resultSet.add(TestSolution.NOT_APPLICABLE);
        } else {
            resultSet.add(TestSolution.PASSED);
        }
        selectedRuleSize = globalSelectorCounter;

        addCssOnErrorRemarks(resultSet);

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    @Override
    public Collection<ProcessRemark> getRemarkList() {
        return processRemarkService.getRemarkList();
    }

    @Override
    public CSSHandler selectAllRules() {
        return this;
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        initialized = false;
    }

    @Override
    public CSSHandler keepRulesWithMedia(Collection<String> mediaNames) {
        this.mediaNames = mediaNames;
        return this;
    }

    /**
     * Each not adapted css generates a process remark to indicate that 
     * some selectors haven't been tested
     * 
     * @param resultSet 
     */
    private void addCssOnErrorRemarks(Collection<TestSolution> resultSet) {
        if (!cssOnErrorSet.isEmpty()) {
            TestSolution fakeSolution = TestSolution.NEED_MORE_INFO;
            for (StylesheetContent stylesheetContent : cssOnErrorSet) {
                List<EvidenceElement> evidenceElementList =
                        new ArrayList<EvidenceElement>();
                evidenceElementList.add(processRemarkService.getEvidenceElement(
                        ProcessRemarkService.DEFAULT_EVIDENCE,
                        stylesheetContent.getURI()));
                processRemarkService.addSourceCodeRemarkOnElement(
                        fakeSolution,
                        null,
                        UNTESTED_RESOURCE_MSG_CODE,
                        evidenceElementList);
            }
            resultSet.add(fakeSolution);
        }
    }

    /**
     * 
     * @param value
     * @return 
     */
    private String getLexicalStringFromValue(int value) {
        switch (value) {
            case LexicalUnit.SAC_INCH:
                return "in";
            case LexicalUnit.SAC_MILLIMETER:
                return "mm";
            case LexicalUnit.SAC_POINT:
                return "pt";
            case LexicalUnit.SAC_CENTIMETER:
                return "cm";
            case LexicalUnit.SAC_PICA:
                return "pc";
            default:
                return "";
        }
    }

    public Set<StylesheetContent> getStylesheetOnError() {
        return cssOnErrorSet;
    }

    @Override
    public int getCssSelectorNumber() {
        return selectedRuleSize;
    }

    @Override
    public Collection<CSSOMRule> getSelectedCSSOMRuleList() {
        return null;
    }

    /**
     * Return a list of ECCSUnit from a unit blacklist. The units in the blacklist 
     * are defined 
     * 
     * @param blacklist
     * @return 
     */
    private List<ECSSUnit> getECSSUnitListFromLexicalUnitBlackList(Collection<Integer> blacklist) {
        List<ECSSUnit> unitList = new ArrayList<ECSSUnit>();
        for (Integer lexicalUnit : blacklist) {
            unitList.add(ECSSUnit.getFromNameOrNull(getLexicalStringFromValue(lexicalUnit)));
        }
        return unitList;
    }

    /**
     * If the css is inline or locale, the associated name of the css is inline.
     * 
     * @param css
     * @return 
     */
    private String getFileNameFromCssName(String css) {
        if (css.contains(INLINE_CSS_URL_FORMAT)) {
                    return INLINE_CSS;
        } else {
            return css;
        }
    }

    /**
     * Inner CSS visitor that only parse selectors regarding a media list, extract 
     * values and units, and checks whether the unit doesn't belong to a 
     * forbidden unit list.
     */
    private class CheckUnitCSSVisitor extends DefaultCSSVisitor {

        private String currentSelector;
        private String currentFileName;
        private Collection<String> includeMediaList;
        private boolean excludeStyle = false;
        private ICSSWriterSettings writterSetting = new CSSWriterSettings(ECSSVersion.CSS30);
        private List<ECSSUnit> unitList;
        
        private boolean hasOneResultOnError = false;
        public boolean hasOneResultOnError() {
            return hasOneResultOnError;
        }
        
        private int globalSelectorCounter = 0;
        public int getGlobalSelectorCounter() {
            return globalSelectorCounter;
        }
        
        private int effectiveSelectorCounter = 0;
        public int getEffectiveSelectorCounter() {
            return effectiveSelectorCounter;
        }
                
        public CheckUnitCSSVisitor(
                final Collection<String> includeMediaList, 
                final String fileName, 
                final List<ECSSUnit> unitList) {
            this.includeMediaList = includeMediaList;
            this.currentFileName = fileName;
            this.unitList = unitList;
        }
        
        @Override
        public void onEndStyleRule(final CSSStyleRule aStyleRule) {
            this.currentSelector = null;
        }

        @Override
        public void onStyleRuleSelector (final CSSSelector aSelector){ 
            globalSelectorCounter++;
            if (excludeStyle) {
                return;
            }
            this.currentSelector = aSelector.getAsCSSString(writterSetting, 0);
            // if this style rule is not excluded from the media type, it is 
            // added to the counter
            effectiveSelectorCounter++;
        }
        
        @Override
        public void onDeclaration(final CSSDeclaration aDeclaration) {
            if (excludeStyle) {
                return;
            }
            checkCSSDeclarationMembers(aDeclaration.getExpression().getAllMembers());
        }
        
        @Override
        public void onBeginMediaRule (final CSSMediaRule aMediaRule) {
            for (CSSMediaQuery mediaQuery : aMediaRule.getAllMediaQueries()) {
                if (includeMediaList.contains(mediaQuery.getMedium())) {
                    return;
                }
            }
            excludeStyle = true;
        }
        
        @Override
        public void onEndMediaRule (final CSSMediaRule aMediaRule) {
            excludeStyle = false;
        }
        
        private void checkCSSDeclarationMembers(List<ICSSExpressionMember> memberList) {
            for (ICSSExpressionMember expr : memberList) {
                checkICSSExpressionMember(expr);
            }
        }
        
        private void checkICSSExpressionMember(ICSSExpressionMember exprMember) {
            if (exprMember instanceof CSSExpressionMemberTermSimple) {
                checkCSSExpressionMemberTermSimple((CSSExpressionMemberTermSimple) exprMember);
            } else if (exprMember instanceof CSSExpressionMemberFunction) {
                checkCSSExpressionMemberFunction((CSSExpressionMemberFunction) exprMember);
            } else {
                Logger.getLogger(this.getClass()).warn("expressionMember " + exprMember.getClass());
            }
        }
      
        private void checkCSSExpressionMemberTermSimple(CSSExpressionMemberTermSimple exprMember) {
            String exprValue = exprMember.getOptimizedValue();
            ECSSUnit unit = extractUnitFromExpressionValue(exprValue);
            // if the selector has not unit, or the value is 0
            if (unit == null) {
                return;
            }
            String value = extractValueFromExpressionValueAndUnit(exprValue, unit);
            checkUnitAndValue(unit, value);
        }

        private void checkCSSExpressionMemberFunction(CSSExpressionMemberFunction exprMember) {
            if (exprMember.getExpression() != null) {
                checkCSSDeclarationMembers(exprMember.getExpression().getAllMembers());
            }
        }

        private ECSSUnit extractUnitFromExpressionValue(String exprValue) {
            return CSSNumberHelper.getMatchingUnitInclPercentage(exprValue);
        }

        private String extractValueFromExpressionValueAndUnit(String exprValue, ECSSUnit unit) {
            String value = exprValue.substring(0, exprValue.length() - unit.getName().length());
            try {
                Integer.valueOf(value);
            } catch (NumberFormatException nfe) {
                Logger.getLogger(this.getClass()).debug("not numerical value " + exprValue + " " +unit);
                return null;
            }
            return value;
        }
        
        private void checkUnitAndValue(ECSSUnit unit, String value) {
            if (value != null && !NON_ZERO_VALUE_PATTERN.matcher(value).matches() && unit != null) {
                if (unitList.contains(unit)) {
                    hasOneResultOnError = true;
                    Logger.getLogger(this.getClass()).debug("wrong unit encountered " + currentSelector + " "+ unit.getName());
                    addSourceCodeRemark(
                            TestSolution.FAILED,
                            currentSelector,
                            BAD_UNIT_TYPE_MSG_CODE,
                            unit.getName(), 
                            currentFileName);
                }
            }
        }
    };

}