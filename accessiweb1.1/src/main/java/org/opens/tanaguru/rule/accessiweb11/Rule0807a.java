package org.opens.tanaguru.rule.accessiweb11;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractSiteRuleImplementation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.w3c.dom.Node;

/**
 * Dans le site Web, le titre de chaque page (balise title) est-il unique ?
 * 
 * @author ndebeissat
 */
public class Rule0807a extends AbstractSiteRuleImplementation {

    protected DefiniteResult consolidateSiteImpl(Site group,
            List<ProcessResult> groupedGrossResultList) {
        TestSolution testSolution = TestSolution.NOT_APPLICABLE;
        List<ProcessRemark> remarkList = new ArrayList<ProcessRemark>();
        if (!groupedGrossResultList.isEmpty()) {
            testSolution = TestSolution.PASSED;
            Collection<String> previousTitles = new ArrayList<String>();
            for (ProcessResult grossResult : groupedGrossResultList) {
                Object titleObj = grossResult.getValue();
                if (titleObj != null) {
                    String title = (String) titleObj;
                    if (previousTitles.contains(title)) {
                        testSolution = TestSolution.FAILED;
                        SourceCodeRemark sourceCodeRemark = sourceCodeRemarkFactory.create();
                        sourceCodeRemark.setTarget(title);
                        sourceCodeRemark.setMessageCode("TitleNotUnique");
                        grossResult.addRemark(sourceCodeRemark);
                        remarkList.add(sourceCodeRemark);
                    }
                    previousTitles.add(title);
                }
            }
            // if all title are null
            if (previousTitles.isEmpty()) {
                testSolution = TestSolution.NOT_APPLICABLE;
            }
        }
        DefiniteResult netResult = definiteResultFactory.create(test, group,
                testSolution, remarkList);
        return netResult;
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection().domXPathSelectNodeSet("/HTML/HEAD/TITLE");
        Collection<Node> elements = sspHandler.getSelectedElementList();
        String title = null;
        if (!elements.isEmpty()) {
            Node titleNode = elements.iterator().next();
            title = titleNode.getTextContent();
        }
        IndefiniteResult result = indefiniteResultFactory.create(test,
                sspHandler.getPage(), title);
        return result;
    }
}
