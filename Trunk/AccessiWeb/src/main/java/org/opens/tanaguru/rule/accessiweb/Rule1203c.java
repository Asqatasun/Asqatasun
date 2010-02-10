package org.opens.tanaguru.rule.accessiweb;

import org.opens.tanaguru.ruleimplementation.AbstractGroupRuleImplementation;
import java.io.IOException;
import java.util.Collection;

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Site;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;

/**
 * Dans chaque page d'un ensemble de pages Web, chaque raccourci clavier
 * (attribut accesskey) défini pour cet ensemble de pages est-il présent ?
 * 
 * @author ADEX
 */
public class Rule1203c extends AbstractGroupRuleImplementation {

    public Rule1203c() {
        super();
    }

    protected DefiniteResult consolidateGroupImpl(Site group,
            List<ProcessResult> processResultList) {
        DefiniteResult netResult = definiteResultFactory.create(test, group);
        TestSolution resultValue = TestSolution.NOT_APPLICABLE;

        HashMap<String, String> map = new HashMap<String, String>();
        for (ProcessResult grossResult : processResultList) {
            try {
                String value = (String) grossResult.getValue();
                StringReader sr = new StringReader(value);
                BufferedReader br = new BufferedReader(sr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(":");
                    String foundValue = map.get(tokens[0]);
                    if (foundValue == null) {
                        map.put(tokens[0], tokens[1]);
                        if (resultValue.equals(TestSolution.NOT_APPLICABLE)) {
                            resultValue = TestSolution.PASSED;
                        }
                        continue;
                    }
                    if (!foundValue.equals(tokens[1])) {
                        resultValue = TestSolution.FAILED;
                        ProcessRemark remark = processRemarkFactory.create(
                                resultValue, "AccessKeyNotMatch");
                        grossResult.addRemark(remark);
                    }
                }
                netResult.addChildResult(grossResult);
            } catch (IOException ex) {
                Logger.getLogger(Rule1203c.class.getName()).log(Level.SEVERE,
                        null, ex);
                throw new RuntimeException(ex);
            }
        }

        netResult.setDefiniteValue(resultValue);

        return netResult;
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        // On récupère ts les noeuds possédant un attribut accesskey
        sspHandler.beginSelection().selectDocumentNodesWithAttribute(
                "accesskey");

        Collection<Node> elements = sspHandler.getSelectedElementList();

        StringBuilder sb = new StringBuilder();
        for (Node node : elements) {
            String accesskeyValue = node.getAttributes().getNamedItem(
                    "accesskey").getNodeValue();
            Node hrefAttribute = node.getAttributes().getNamedItem("href");
            if (hrefAttribute != null) {
                String accesskeyTarget = hrefAttribute.getNodeValue();
                sb.append(accesskeyValue + ":" + accesskeyTarget + "\n");
            }
        }

        IndefiniteResult result = indefiniteResultFactory.create(test,
                sspHandler.getPage(), sb.toString());

        return result;
    }
}
