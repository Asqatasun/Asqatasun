package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import java.util.ArrayList;

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;

/**
 * Chaque lien texte sans titre de lien est-il explicite hors contexte ?
 * 
 * @author ADEX
 */
public class Rule0601a extends AbstractPageRuleImplementation {

    public Rule0601a() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Nomenclature blacklist = nomenclatureLoaderService.loadByCode("LinkTextBlacklist");
        Nomenclature whitelist = nomenclatureLoaderService.loadByCode("LinkTextWhitelist");

        // sspHandler.BeginSelection().selectDocumentNodes("a").excludeNodesWithAttribute("title").excludeNodesWithChildNode("img");
        ArrayList<String> linkTagList = new ArrayList<String>();
        linkTagList.add("a");
        linkTagList.add("A");
        ArrayList<String> imageTagList = new ArrayList<String>();
        imageTagList.add("img");
        imageTagList.add("IMG");
        sspHandler.beginSelection().selectDocumentNodes(linkTagList).excludeNodesWithAttribute("title").excludeNodesWithChildNode(
                imageTagList);

        TestSolution checkResult = sspHandler.checkTextContentValue(blacklist.getValueList(), whitelist.getValueList());

        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage(), checkResult, sspHandler.getRemarkList());

        return result;
    }
}
