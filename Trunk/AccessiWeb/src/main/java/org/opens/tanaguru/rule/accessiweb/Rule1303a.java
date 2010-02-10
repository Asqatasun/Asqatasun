package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import java.util.Collection;

import org.w3c.dom.Node;

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import java.util.ArrayList;
import java.util.List;

/**
 * Dans chaque page Web, pour chaque ouverture de nouvelle fenêtre (attribut
 * target=_blank, script, balises object ou applet) l'utilisateur est-il averti?
 * 
 * @author ADEX
 */
public class Rule1303a extends AbstractPageRuleImplementation {

    public Rule1303a() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Nomenclature jsWindowOpen = nomenclatureLoaderService.loadByCode("JSWindowOpen");
        Nomenclature whitelist = nomenclatureLoaderService.loadByCode("NotificationNewWindowWhitelist");

        Collection<String> linkList = new ArrayList<String>();
        linkList.add("a");
        linkList.add("A");

        sspHandler.beginSelection().selectDocumentNodes(linkList).keepNodesWithAttributeValueNonEmpty("target");
        List<Node> workingElements = sspHandler.getSelectedElementList();

        sspHandler.beginSelection().selectDocumentNodes(linkList).keepNodesWithAttributeValueStartingWith("href",
                jsWindowOpen.getValueList());
        workingElements.addAll(sspHandler.getSelectedElementList());

        sspHandler.beginSelection().selectDocumentNodes(linkList).keepNodesWithAttributeValueStartingWith("onclick",
                jsWindowOpen.getValueList());
        workingElements.addAll(sspHandler.getSelectedElementList());

        sspHandler.setSelectedElementList(workingElements);

        TestSolution checkResult = sspHandler.checkTextContentAndAttributeValue("title", null, whitelist.getValueList());

        DefiniteResult processResult = definiteResultFactory.create(test,
                sspHandler.getSSP().getPage(), checkResult, sspHandler.getRemarkList());

        return processResult;
    }
}
