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
 * Dans chaque formulaire, chaque bouton sans alternative textuelle est-il
 * explicite visuellement ?
 * 
 * @author ADEX
 */
public class Rule1109a extends AbstractPageRuleImplementation {

    public Rule1109a() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Nomenclature formsButtonTypes = nomenclatureLoaderService.loadByCode("FormsButtonTypes");

        Nomenclature blacklist = nomenclatureLoaderService.loadByCode("FormsButtonBlacklist");
        Nomenclature whitelist = nomenclatureLoaderService.loadByCode("FormsButtonWhitelist");

        Collection<String> buttonList = new ArrayList<String>();
        buttonList.add("button");
        buttonList.add("BUTTON");

        Collection<String> inputList = new ArrayList<String>();
        inputList.add("input");
        inputList.add("INPUT");

        sspHandler.beginSelection().selectDocumentNodes(buttonList).keepNodesWithAttributeValueEquals("type",
                formsButtonTypes.getValueList());
        List<Node> workingElements = sspHandler.getSelectedElementList();

        sspHandler.beginSelection().selectDocumentNodes(inputList).keepNodesWithAttributeValueEquals("type",
                formsButtonTypes.getValueList());
        workingElements.addAll(sspHandler.getSelectedElementList());

        sspHandler.setSelectedElementList(workingElements);

        TestSolution checkResult = sspHandler.checkTextContentAndAttributeValue("value", blacklist.getValueList(), whitelist.getValueList());

        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage(), checkResult, sspHandler.getRemarkList());

        return result;
    }
}
