package org.opens.tanaguru.processor;

import java.util.Collection;

import org.opens.tanaguru.contentadapter.css.CSSOMDeclaration;
import org.opens.tanaguru.contentadapter.css.CSSOMRule;
import org.opens.tanaguru.contentadapter.css.CSSOMStyleSheet;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.ruleimplementation.RuleHelper;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.w3c.css.sac.LexicalUnit;

public class CSSHandlerImpl implements CSSHandler {

    private boolean initialized = false;
    protected ProcessRemarkFactory processRemarkFactory;
    private Collection<ProcessRemark> remarkList;
    private Collection<CSSOMRule> selectedRuleList;
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected SSP ssp;
    private Set<CSSOMStyleSheet> styleSet;
    private Map<String, Set<CSSOMRule>> rulesByMedia ;
    private final String CSS_ON_ERROR = "CSS_ON_ERROR";
    Set<StylesheetContent> cssOnErrorSet;
    private static int  HTTP_OK_STATUS_CODE = 200;

    public CSSHandlerImpl() {
        super();
    }

    public CSSHandlerImpl(SSP ssp) {
        this.ssp = ssp;
    }

    private void addSourceCodeRemark(TestSolution processResult,
            CSSOMRule rule, String messageCode, String attrName) {// XXX
        SourceCodeRemark remark = sourceCodeRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);
        remark.setLineNumber(rule.getOwnerStyle().getLineNumber());
        remark.setTarget(attrName);
        remarkList.add(remark);
    }

    public CSSHandler beginSelection(){
        initialize();
        selectedRuleList = new HashSet<CSSOMRule>();
        remarkList = new ArrayList<ProcessRemark>();
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
                        addSourceCodeRemark(processResult, workingRule, "BadUnitType",
                            getLexicalStringFromValue(unit));
                        break;
                    }
                }
            }
            resultSet.add(processResult);
        }

        if (!cssOnErrorSet.isEmpty()) {
            TestSolution fakeSolution = TestSolution.NEED_MORE_INFO;
            for (StylesheetContent stylesheetContent : cssOnErrorSet){
                remarkList.add(processRemarkFactory.create(fakeSolution, "UnTested Resource :" + stylesheetContent.getURI()));
            }
            resultSet.add(fakeSolution);
        }

        if (selectedRuleList.isEmpty()){
            resultSet.add(TestSolution.NOT_APPLICABLE);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public Collection<ProcessRemark> getRemarkList() {
        return remarkList;
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
                if (((StylesheetContent)relatedContent).getHttpStatusCode() == HTTP_OK_STATUS_CODE &&
                        ((StylesheetContent)relatedContent).getAdaptedContent() != null &&
                        !((StylesheetContent)relatedContent).getAdaptedContent().equalsIgnoreCase(CSS_ON_ERROR) ){
                    styleSet.add(
                        (CSSOMStyleSheet) xstream.fromXML(
                        ((StylesheetContent)relatedContent).getAdaptedContent()));
                } else{
                    addStylesheetOnError((StylesheetContent)relatedContent);
                }
            }
        }
        rulesByMedia = setRulesByMedia(styleSet);
        initialized = true;
    }

    public CSSHandler selectAllRules() {
        if (styleSet == null) {
            return this;
        }

        for (CSSOMStyleSheet style : styleSet) {
            selectedRuleList.addAll(style.getRules());
        }
        return this;
    }

    public void setProcessRemarkFactory(
            ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setRemarkList(Collection<ProcessRemark> remarkList) {
        this.remarkList = remarkList;
    }

    public void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
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
            (Set<CSSOMStyleSheet> styleSet)  {

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
        if (styleSet==null){
            styleSet = new HashSet<CSSOMStyleSheet>();
        }
        styleSet.clear();
    }
}
