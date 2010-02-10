package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import java.util.Collection;

import org.w3c.dom.Node;

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import java.util.ArrayList;
import java.util.List;

/**
 * Pour chaque image (balise img, balise img ou balise object avec l'attribut
 * usemap) ayant un attribut longdesc, le contenu de cet attribut est-il une url
 * valide ?
 * 
 * @author ADEX
 */
public class Rule0105b extends AbstractPageRuleImplementation {

    public Rule0105b() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Collection<String> imageList = new ArrayList<String>();
        imageList.add("img");
        imageList.add("IMG");
        Collection<String> objectList = new ArrayList<String>();
        objectList.add("object");
        objectList.add("OBJECT");

        sspHandler.beginSelection().selectDocumentNodes(imageList).keepNodesWithAttribute("longdesc");
        List<Node> workingElements = sspHandler.getSelectedElementList();

        sspHandler.beginSelection().selectDocumentNodes(objectList).keepNodesWithAttribute("usemap").keepNodesWithAttribute(
                "longdesc");
        workingElements.addAll(sspHandler.getSelectedElementList());

        sspHandler.setSelectedElementList(workingElements);

        TestSolution checkResult = sspHandler.checkAttributeValueExpression(
                "longdesc", "(https?://){1}.{3,}(/.*)*");

        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage(), checkResult, sspHandler.getRemarkList());

        return result;
    }
}
