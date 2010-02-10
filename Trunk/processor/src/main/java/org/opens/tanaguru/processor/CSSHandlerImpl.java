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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSSHandlerImpl implements CSSHandler {

    private boolean initialized = false;
    protected ProcessRemarkFactory processRemarkFactory;
    private Collection<ProcessRemark> remarkList;
    private Collection<CSSOMRule> selectedRuleList;
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected SSP ssp;
    private Set<CSSOMStyleSheet> styleSet;

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
        // remark.setCharacterPosition(index + 1);
        remark.setTarget(attrName);
    }

    public CSSHandler beginSelection() {
        initialize();

        selectedRuleList = new ArrayList<CSSOMRule>();
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
                        break;
                    }
                }

                addSourceCodeRemark(processResult, workingRule, "BadUnitType",
                        workingRule.toString());
            }
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
}
