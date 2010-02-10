package org.opens.tanaguru.rule.accessiweb;

import java.util.List;
import org.opens.tanaguru.ruleimplementation.AbstractSiteRuleImplementation;
import org.opens.tanaguru.ruleimplementation.RuleHelper;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.subject.Site;

/**
 * Dans les feuilles de styles du site Web, les unités non relatives (pt, pc,
 * mm, cm, in) ne doivent pas être utilisées. Cette règle est-elle respectée ?
 * 
 * @author ADEX
 * 
 */
public class Rule1005a extends AbstractSiteRuleImplementation {

    public Rule1005a() {
        super();
    }

    @Override
    protected DefiniteResult consolidateSiteImpl(Site group,
            List<ProcessResult> groupedGrossResultList) {
        DefiniteResult netResult = definiteResultFactory.create(test, group);
        TestSolution solution = RuleHelper.synthesizeProcessResultCollection(groupedGrossResultList);
        netResult.setDefiniteValue(solution);

        return netResult;
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        Nomenclature blacklist = nomenclatureLoaderService.loadByCode("RelativeCssUnits");

        TestSolution checkResult = sspHandler.beginSelection().selectAllRules().checkRelativeUnitExists(blacklist.getIntegerValueList());
        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getPage(), checkResult, sspHandler.getRemarkList());

        return result;
    }
}
