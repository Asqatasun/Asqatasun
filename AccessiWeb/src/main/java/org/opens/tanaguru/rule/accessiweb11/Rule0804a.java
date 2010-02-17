/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb11;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Pour chaque page Web ayant une langue par défaut (attribut lang associé à la balise html), le contenu de l'attribut lang est-il valide ?
 * @author ndebeissat
 */
public class Rule0804a extends AbstractPageRuleImplementation {

    /**
     * select the html tag and check the validity of default language
     * @param sspHandler
     * @return
     */
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection();
        sspHandler.domXPathSelectNodeSet("/html/@lang|/HTML/@lang");

        TestSolution checkResult = sspHandler.domCheckNodeValueInNomenclature("ValidLanguageCode", "InvalidLanguageCode");

        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage(), checkResult, sspHandler.getRemarkList());
        return result;
    }
}
