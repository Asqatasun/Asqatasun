package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Chaque image objet (balise object avec l'attribut type="image/...") a-t-elle
 * une alternative textuelle entre <object> et </object> ?
 * 
 * @author ADEX
 */
public class Rule0101e extends AbstractPageRuleImplementation {

	public Rule0101e() {
		super();
	}

	@Override
	protected ProcessResult processImpl(SSPHandler sspHandler) {
		Collection<String> objectList = new ArrayList<String>();
		objectList.add("object");
		objectList.add("OBJECT");

		sspHandler.beginSelection().selectDocumentNodes(objectList)
				.keepNodesWithAttributeValueStartingWith("type", "image");
		TestSolution checkResult = sspHandler.checkContentNotEmpty();

		DefiniteResult processResult = definiteResultFactory.create(test,
				sspHandler.getSSP().getPage(), checkResult, sspHandler
						.getRemarkList());

		return processResult;
	}
}
