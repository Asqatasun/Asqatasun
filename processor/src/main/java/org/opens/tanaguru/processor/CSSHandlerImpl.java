package org.opens.tanaguru.processor;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class CSSHandlerImpl implements CSSHandler {

    private boolean initialized = false;
    protected ProcessRemarkFactory processRemarkFactory;
    private Collection<ProcessRemark> remarkList;
    private Collection<CSSOMRule> selectedRuleList;
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected SSP ssp;
    private Set<CSSOMStyleSheet> styleSet;
    private Map<String, Set<CSSOMRule>> rulesByMedia =
            new HashMap<String, Set<CSSOMRule>>();

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
            try  {
            for (CSSOMDeclaration declaration : declarations) {
                int unit = declaration.getUnit();
                for (int blackListedUnit : blacklist) {
                    if (unit == blackListedUnit) {
                        processResult = TestSolution.FAILED;
                        resultSet.add(processResult);
                        addSourceCodeRemark(processResult, workingRule, "BadUnitType",
                            workingRule.toString());
                        break;
                    }
                }
            }
            } catch (Exception e){
                
            }
            resultSet.add(processResult);
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

        XStream xstream = new XStream();
        styleSet = (Set<CSSOMStyleSheet>) xstream.fromXML(ssp.getStylesheet());
        setRulesByMedia();
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
        if (styleSet == null) {
            return this;
        }

        for (String media : mediaNames) {
            if (rulesByMedia.containsKey(media)) {
                selectedRuleList.addAll(rulesByMedia.get(media));
            }
        }
        return this;
    }

    private void setRulesByMedia()  {
        if (styleSet == null) {
            return;
        }
        String mediaName;

        for (CSSOMStyleSheet style : styleSet) {
            for (CSSOMRule rule : style.getRules()) {
                for (int i=0 ; i < ((CSSOMRule)rule).getMediaList().getLength();i++){
                    mediaName = ((CSSOMRule)rule).getMediaList().item(i);
                    if (rulesByMedia.containsKey(mediaName)) {
                        rulesByMedia.get(mediaName).add(((CSSOMRule)rule));
                    } else {
                        Set<CSSOMRule> rulesSet = new HashSet<CSSOMRule>();
                        rulesSet.add(((CSSOMRule)rule));
                        rulesByMedia.put(mediaName, rulesSet);
                    }
                }
            }
        }
    }

}
