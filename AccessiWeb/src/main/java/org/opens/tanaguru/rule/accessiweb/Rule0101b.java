package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Test 1.1.b [Bronze] : Chaque zone (balise area) d'une image réactive a-t-elle
 * un attribut alt ?
 * 
 * @author ndebeissat
 */
public class Rule0101b extends AbstractPageRuleImplementation {

	public Rule0101b() {
		super();
	}

	@Override
	protected ProcessResult processImpl(SSPHandler sspHandler) {
		sspHandler.beginSelection().domXPathSelectNodeSet(
				"//AREA[concat('#', ancestor::MAP/@id) = //IMG/@usemap]");
		TestSolution checkResult = sspHandler.checkAttributeExists("alt");

		DefiniteResult result = definiteResultFactory.create(test, sspHandler
				.getSSP().getPage(), checkResult, sspHandler.getRemarkList());
		return result;
	}
}
