package org.opens.tanaguru.rule.accessiweb11;

import java.util.List;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractSiteRuleImplementation;
import org.opens.tanaguru.ruleimplementation.RuleHelper;

/**
 * 
 * @author ndebeissat
 */
public class Rule1001a extends AbstractSiteRuleImplementation {

    @Override
    protected DefiniteResult consolidateSiteImpl(Site group,
            List<ProcessResult> groupedGrossResultList) {
        TestSolution testSolution = RuleHelper.synthesizeProcessResultCollection(groupedGrossResultList);
        DefiniteResult result = definiteResultFactory.create(test, group,
                testSolution, null);
        return result;
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection().domXPathSelectNodeSet(
                "//B|//I|//TT|//U|//S|//STRIKE|//BIG|//SMALL|//SUP");
        TestSolution testSolution = TestSolution.FAILED;
        if (sspHandler.getSelectedElementList().isEmpty()) {
            testSolution = TestSolution.PASSED;
        }
        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage(), testSolution, sspHandler.getRemarkList());
        return result;
    }
}
