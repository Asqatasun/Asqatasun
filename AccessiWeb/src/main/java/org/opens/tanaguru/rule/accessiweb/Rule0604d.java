package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import java.util.Collection;

import org.opens.tanaguru.ruleimplementation.RuleHelper;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Chaque lien composite a-t-il une longueur maximum de 80 caractères (hors cas
 * particuliers) ?
 * 
 * @author ADEX
 */
public class Rule0604d extends AbstractPageRuleImplementation {

    public Rule0604d() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Set<TestSolution> checkResultList = new HashSet<TestSolution>();
        List<ProcessRemark> remarks = new ArrayList<ProcessRemark>();

        Collection<String> linkList = new ArrayList<String>();
        linkList.add("a");
        linkList.add("A");

        Collection<String> imageList = new ArrayList<String>();
        imageList.add("img");
        imageList.add("IMG");

        sspHandler.beginSelection().selectDocumentNodes(linkList).keepNodesWithoutChildNode(imageList);
        checkResultList.add(sspHandler.checkTextContentValueLengthLower(80,
                TestSolution.NEED_MORE_INFO));
        remarks.addAll(sspHandler.getRemarkList());

        sspHandler.beginSelection().selectDocumentNodes(linkList).selectChildNodes(imageList);// XXX
        checkResultList.add(sspHandler.checkAttributeValueLengthLower("alt", 80,
                TestSolution.NEED_MORE_INFO));
        remarks.addAll(sspHandler.getRemarkList());

        TestSolution finalResult = RuleHelper.synthesizeTestSolutionCollection(checkResultList);

        DefiniteResult result = definiteResultFactory.create(test, sspHandler.getSSP().getPage(), finalResult, sspHandler.getRemarkList());

        return result;
    }
}
