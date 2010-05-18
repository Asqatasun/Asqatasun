/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public class Aw20Rule10041 extends AbstractPageRuleImplementation {

    public Aw20Rule10041() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        Nomenclature blacklist = 
                nomenclatureLoaderService.loadByCode("RelativeCssUnits");

        Nomenclature mediaList =
                nomenclatureLoaderService.loadByCode("MediaListNotAcceptingRelativeUnits");

        TestSolution checkResult = TestSolution.NEED_MORE_INFO;
        checkResult = sspHandler.beginSelection().
                keepRulesWithMedia(mediaList.getValueList()).
                checkRelativeUnitExists(blacklist.getIntegerValueList());

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                checkResult,
                sspHandler.getRemarkList());

        return result;
    }
}
