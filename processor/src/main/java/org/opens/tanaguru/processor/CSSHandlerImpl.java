package org.opens.tanaguru.processor;

import java.util.Collection;

import org.opens.tanaguru.contentadapter.css.CSSOMDeclaration;
import org.opens.tanaguru.contentadapter.css.CSSOMRule;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.RuleHelper;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.HttpStatus;
import org.opens.tanaguru.contentadapter.css.CSSOMStyleSheet;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.service.ProcessRemarkService;
import org.w3c.css.sac.LexicalUnit;

/**
 * 
 * @author jkowalczyk
 */
public class CSSHandlerImpl implements CSSHandler {

    private boolean initialized = false;
    private Collection<CSSOMRule> selectedRuleList;
    protected SSP ssp;
    private Map<String, CSSOMStyleSheet> styleMap;
    private Map<String, Set<CSSOMRule>> rulesByMedia ;
    private static final String CSS_ON_ERROR = "CSS_ON_ERROR";
    private static final String INLINE_CSS_URL_FORMAT = "#tanaguru-css-";
    private static final String INLINE_CSS = "inline";
    Set<StylesheetContent> cssOnErrorSet;
    private static final String BAD_UNIT_TYPE_MSG_CODE = "BadUnitType";
    private static final String UNTESTED_RESOURCE_MSG_CODE = "UnTestedResource";

    protected ProcessRemarkService processRemarkService;
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }

    public CSSHandlerImpl() {
        super();
    }

    public CSSHandlerImpl(SSP ssp) {
        this.ssp = ssp;
    }

    private void addSourceCodeRemark(TestSolution processResult,
            CSSOMRule rule, String messageCode, String attrName) {// XXX
        processRemarkService.addCssCodeRemark(processResult, rule, messageCode, attrName, getFileNameFromCssomRule(rule));
    }

    public CSSHandler beginSelection(){
        initialize();
        selectedRuleList = new HashSet<CSSOMRule>();
        processRemarkService.initializeService(null, null);
        return this;
    }

    public TestSolution checkRelativeUnitExists(Collection<Integer> blacklist) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        for (CSSOMRule workingRule : selectedRuleList) {
            List<CSSOMDeclaration> declarations = workingRule.getDeclarations();
            TestSolution processResult = TestSolution.PASSED;
            for (CSSOMDeclaration declaration : declarations) {
                int unit = declaration.getUnit();
                for (int blackListedUnit : blacklist) {
                    if (unit == blackListedUnit) {
                        processResult = TestSolution.FAILED;
                        resultSet.add(processResult);
                        addSourceCodeRemark(processResult, workingRule,
                                BAD_UNIT_TYPE_MSG_CODE,
                                getLexicalStringFromValue(unit));
                        break;
                    }
                }
            }
            resultSet.add(processResult);
        }

        if (!cssOnErrorSet.isEmpty()) {
            TestSolution fakeSolution = TestSolution.NEED_MORE_INFO;
            for (StylesheetContent stylesheetContent : cssOnErrorSet) {
                List<EvidenceElement> evidenceElementList =
                        new ArrayList<EvidenceElement>();
                evidenceElementList.add(processRemarkService.
                        getEvidenceElement(
                        ProcessRemarkService.DEFAULT_EVIDENCE,
                        stylesheetContent.getURI()));
                processRemarkService.addSourceCodeRemark(
                        fakeSolution,
                        null,
                        UNTESTED_RESOURCE_MSG_CODE,
                        evidenceElementList);
            }
            resultSet.add(fakeSolution);
        }

        if (selectedRuleList.isEmpty()){
            resultSet.add(TestSolution.NOT_APPLICABLE);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public Collection<ProcessRemark> getRemarkList() {
        return processRemarkService.getRemarkList();
    }

    private void initialize() {
        if (initialized) {
            return;
        }
        initializeStyleSet();
        initializeCssOnErrorSet();
        XStream xstream = new XStream();
        for (RelatedContent relatedContent : ssp.getRelatedContentSet()){
            if (relatedContent instanceof StylesheetContent){
                if (((StylesheetContent)relatedContent).getHttpStatusCode() == HttpStatus.SC_OK &&
                        ((StylesheetContent)relatedContent).getAdaptedContent() != null &&
                        !((StylesheetContent)relatedContent).getAdaptedContent().equalsIgnoreCase(CSS_ON_ERROR) ){
                    if (((Content)relatedContent).getURI().contains(INLINE_CSS_URL_FORMAT)) {
                        styleMap.put(((Content)relatedContent).getURI() ,
                            (CSSOMStyleSheet) xstream.fromXML(
                            ((StylesheetContent)relatedContent).getAdaptedContent()));
                    } else {
                        styleMap.put(((Content)relatedContent).getURI() ,
                            (CSSOMStyleSheet) xstream.fromXML(
                            ((StylesheetContent)relatedContent).getAdaptedContent()));
                    }
                } else{
                    addStylesheetOnError((StylesheetContent)relatedContent);
                }
            }
        }
        rulesByMedia = setRulesByMedia(styleMap.values());
        initialized = true;
    }

    public CSSHandler selectAllRules() {
        if (styleMap == null) {
            return this;
        }

        for (CSSOMStyleSheet style : styleMap.values()) {
            selectedRuleList.addAll(style.getRules());
        }
        return this;
    }

    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        initialized = false;
    }

    @Override
    public CSSHandler keepRulesWithMedia(Collection<String> mediaNames) {
        if (rulesByMedia.isEmpty()) {
            return this;
        }

        for (String media : mediaNames) {
            if (rulesByMedia.containsKey(media)) {
                selectedRuleList.addAll(rulesByMedia.get(media));
            }
        }
        return this;
    }

    private HashMap<String, Set<CSSOMRule>> setRulesByMedia
            (Collection<CSSOMStyleSheet> styleSet)  {

        HashMap<String, Set<CSSOMRule>> rules =
                new HashMap<String, Set<CSSOMRule>>();

        if (styleSet == null || styleSet.isEmpty()) {
            return rules;
        }

        String mediaName;
        for (CSSOMStyleSheet style : styleSet) {
            for (CSSOMRule rule : style.getRules()) {
                for (int i=0 ; i < ((CSSOMRule)rule).getMediaList().getLength();i++){
                    mediaName = ((CSSOMRule)rule).getMediaList().item(i);
                    if (rules.containsKey(mediaName)) {
                        rules.get(mediaName).add(((CSSOMRule)rule));
                    } else {
                        Set<CSSOMRule> rulesSet = new HashSet<CSSOMRule>();
                        rulesSet.add(((CSSOMRule)rule));
                        rules.put(mediaName, rulesSet);
                    }
                }
            }
        }
        return rules;
    }

    private String getLexicalStringFromValue(int value){
        switch (value) {
            case LexicalUnit.SAC_INCH:
                return "in";
            case LexicalUnit.SAC_MILLIMETER:
                return "mm";
            case LexicalUnit.SAC_POINT:
                return "pt";
            case LexicalUnit.SAC_CENTIMETER:
                return "in";
            case LexicalUnit.SAC_PICA:
                return "pc";
            default:
                return "";
        }
    }

    public Set<StylesheetContent> getStylesheetOnError(){
        return cssOnErrorSet;
    }

    public void addStylesheetOnError(StylesheetContent css){
        cssOnErrorSet.add(css);
    }

    /**
     * This method initializes or re-initializes the set of stylesheets
     * on error related with a css
     */
    private void initializeCssOnErrorSet(){
        if (cssOnErrorSet == null){
            cssOnErrorSet = new HashSet<StylesheetContent>();
        }
        cssOnErrorSet.clear();
    }

    /**
     * This method initializes or re-initializes the set of stylesheets related
     * with a css
     */
    private void initializeStyleSet(){
        if (styleMap==null){
            styleMap = new HashMap<String, CSSOMStyleSheet>();
        }
        styleMap.clear();
    }

    @Override
    public int getCssSelectorNumber() {
        return selectedRuleList.size();
    }

    private String getFileNameFromCssomRule(CSSOMRule cssomRule) {
        for (Map.Entry<String, CSSOMStyleSheet> entry : styleMap.entrySet()) {
            if (entry.getValue().getRules().contains(cssomRule)) {
                if (entry.getKey().contains(INLINE_CSS_URL_FORMAT)) {
                    return INLINE_CSS;
                }
                return entry.getKey();
            }
        }
        return "";
    }

}