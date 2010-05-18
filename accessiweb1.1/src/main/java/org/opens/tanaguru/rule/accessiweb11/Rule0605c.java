package org.opens.tanaguru.rule.accessiweb11;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import java.util.ArrayList;

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;

/**
 * Pour chaque image réactive (balise img ou balise object avec un attribut
 * usemap), pour chaque zone cliquable (balise area) ayant un attribut alt, le
 * contenu de cet attribut est-il pertinent ?
 * 
 * @author ADEX
 */
public class Rule0605c extends AbstractPageRuleImplementation {

	public Rule0605c() {
		super();
	}

	@Override
	protected ProcessResult processImpl(SSPHandler sspHandler) {
		// Nomenclature possibleImageTagNames =
		// getNomenclatureService().findByLabel("PossibleImageTags"); // XXX
		Nomenclature blacklist = nomenclatureLoaderService
				.loadByCode("LinkTextBlacklist");
		Nomenclature whitelist = nomenclatureLoaderService
				.loadByCode("LinkTextWhitelist");

		// sspHandler.initializeWorkingElements().selectDocumentNodesByTagName(possibleImageTagNames.getValues()).keepNodesWithAttribute("usemap");

		ArrayList<String> areaTagList = new ArrayList<String>();// XXX
		areaTagList.add("area");
		areaTagList.add("AREA");

		sspHandler.beginSelection().selectDocumentNodes(areaTagList)
				.selectAttributeByName("alt");

		TestSolution checkResult = sspHandler.checkNodeValue(blacklist
				.getValueList(), whitelist.getValueList());

		DefiniteResult result = definiteResultFactory.create(test, sspHandler
				.getPage(), checkResult, sspHandler.getRemarkList());

		return result;
	}
}
