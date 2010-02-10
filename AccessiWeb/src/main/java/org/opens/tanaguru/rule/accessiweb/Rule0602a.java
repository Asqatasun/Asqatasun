package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import java.util.ArrayList;
import java.util.List;

/**
 * Pour chaque lien texte ayant un titre de lien (attribut title), ce titre
 * est-il nécessaire ?
 * 
 * @author ADEX
 */
public class Rule0602a extends AbstractPageRuleImplementation {

    public Rule0602a() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        List<String> linkList = new ArrayList<String>();
        linkList.add("a");
        linkList.add("A");

        sspHandler.beginSelection().selectDocumentNodes(linkList).keepNodesWithAttribute("title");

        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage());

        if (!sspHandler.isSelectedElementsEmpty()) {
            result.setDefiniteValue(TestSolution.NOT_APPLICABLE);
            return result;
        }

        List<String> titleValues = sspHandler.getAttributeValues("title");// XXX
        List<String> textContentValues = sspHandler.getTextContentValues();

        TestSolution checkResult = TestSolution.PASSED;

        for (int i = 0; i < titleValues.size(); i++) {
            if (titleValues.get(i).length() == 0 || textContentValues.get(i).length() == 0) {
                checkResult = TestSolution.FAILED;
                // TODO Add remark
                continue;
            }
            if (titleValues.get(i).length() < textContentValues.get(i).length()) {
                checkResult = TestSolution.FAILED;
                // TODO Add remark
                continue;
            }
            if (titleValues.get(i).length() == textContentValues.get(i).length()) {
                checkResult = TestSolution.FAILED;
                // TODO Add remark
                continue;
            }
        }

        result.setDefiniteValue(checkResult);

        return result;
    }
}
