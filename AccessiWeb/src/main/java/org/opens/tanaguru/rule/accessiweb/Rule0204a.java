package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

import org.opens.tanaguru.ruleimplementation.RuleHelper;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Pour chaque jeu de cadres (balise frameset), le contenu de la balise noframes
 * propose-t-il une liste de liens permettant d'accèder directement au contenu
 * de chaque cadre ?
 * 
 * @author ADEX
 */
public class Rule0204a extends AbstractPageRuleImplementation {

    public Rule0204a() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        List<String> frameList = new ArrayList<String>();
        frameList.add("frame");
        frameList.add("FRAME");

        List<String> framesetList = new ArrayList<String>();
        framesetList.add("frameset");
        framesetList.add("FRAMESET");

        List<String> noframeList = new ArrayList<String>();
        noframeList.add("noframe");
        noframeList.add("noframe");

        List<String> linkList = new ArrayList<String>();
        linkList.add("a");
        linkList.add("A");

        sspHandler.beginSelection().selectDocumentNodes(frameList);
        List<String> srcValues = sspHandler.getAttributeValues("src");

        sspHandler.beginSelection().selectDocumentNodes(framesetList).selectChildNodes(noframeList).selectChildNodesRecursively(
                linkList);
        List<String> hrefValues = sspHandler.getAttributeValues("href");

        Set<TestSolution> resultList = new HashSet<TestSolution>();
        for (String srcValue : srcValues) {
            TestSolution result = TestSolution.PASSED;
            boolean found = false;
            for (String hrefValue : hrefValues) {
                if (srcValue.equals(hrefValue)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result = TestSolution.FAILED;
                // TODO Add Remark
            }
            resultList.add(result);
        }

        TestSolution finalResult = RuleHelper.synthesizeTestSolutionCollection(resultList);

        DefiniteResult processResult = definiteResultFactory.create(test,
                sspHandler.getSSP().getPage(), finalResult, sspHandler.getRemarkList());

        return processResult;
    }
}
