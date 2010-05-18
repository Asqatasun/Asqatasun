package org.opens.tanaguru.rule.accessiweb11;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Chaque image (balise img) a-t-elle un attribut alt ?
 * 
 * @author ADEX
 */
public class Rule0101a extends AbstractPageRuleImplementation {

	public Rule0101a() {
		super();
	}

	@Override
	protected ProcessResult processImpl(SSPHandler sspHandler) {
		Collection<String> imageList = new ArrayList<String>();
		imageList.add("img");
		imageList.add("IMG");

		sspHandler.beginSelection().selectDocumentNodes(imageList);

		TestSolution checkResult = sspHandler.checkAttributeExists("alt");

		DefiniteResult result = definiteResultFactory.create(test, sspHandler
				.getSSP().getPage(), checkResult, sspHandler.getRemarkList());

		return result;
	}
}
