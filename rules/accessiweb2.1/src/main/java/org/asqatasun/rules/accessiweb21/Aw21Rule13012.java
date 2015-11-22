/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.accessiweb21;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.entity.audit.DefiniteResult;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;

/**
 * Implementation of the rule 13.1.2 of the referential Accessiweb 2.1.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw21-rule-13-1-2">the rule 13.1.2 design page.</a>
 * @see <a href="http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-13-1-2"> 13.1.2 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Aw21Rule13012 extends AbstractPageRuleImplementation {

    public static final String ERROR_MESSAGE = "NotImmediateRedirectionViaMeta";
    private static final String URL_STR = "URL";
    private static final String SEMI_COLON_CHAR = ";";
    private static final String REFRESH_META_TAG =
            "//HEAD//META[@http-equiv='refresh' and (contains(@content, 'URL') or contains(@content, 'url'))]";
    
    public Aw21Rule13012() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        TestSolution testSolution = TestSolution.NOT_APPLICABLE;
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        List<Node> nodeList = 
                sspHandler.beginSelection().domXPathSelectNodeSet(REFRESH_META_TAG).getSelectedElementList();
        
        if (nodeList.size() == 1) {
            Node node = nodeList.iterator().next();
            if (!isImmediateRedirection(nodeList.iterator().next())) {
                testSolution = TestSolution.FAILED;
                sspHandler.getProcessRemarkService().addSourceCodeRemark(
                            testSolution,
                            node,
                            ERROR_MESSAGE,
                            node.getNodeName());
            } else {
                testSolution = TestSolution.PASSED;
            }
        }
        
        processRemarkList.addAll(sspHandler.getRemarkList());
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                processRemarkList);
        return result;
    }

    private boolean isImmediateRedirection(Node metaNode) {
        String contentAttributeContent = metaNode.getAttributes().getNamedItem(NodeAndAttributeKeyStore.CONTENT_ATTR).getNodeValue();
        String[] contentAttributeValues = contentAttributeContent.split(SEMI_COLON_CHAR);
        if (contentAttributeValues != null && 
                contentAttributeValues.length == 2 && 
                Integer.valueOf(StringUtils.trim(contentAttributeValues[0]))>0 && 
                contentAttributeValues[1].toUpperCase().startsWith(URL_STR)) {
            return false;
        }
        return true;
    }
    
}