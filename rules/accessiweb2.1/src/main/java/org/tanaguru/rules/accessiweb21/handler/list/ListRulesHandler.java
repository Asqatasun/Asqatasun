/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.accessiweb21.handler.list;

import java.util.ArrayList;
import java.util.List;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.accessiweb21.NodeAndAttributeKeyStore;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public final class ListRulesHandler {

    private static final int TITLE_MAX_CHARACTERS = 80;

    /**
     * xpath expression to get suspected malformed lists (that doesn't use
     * ul, ol et li tags)
     */
    public static final String MALFORMED_LIST =
            "//P[count(descendant::BR)>1 and (count(descendant::*)=count(descendant::BR))]";

    /**
     * The SSPHandler
     */
    private SSPHandler sspHandler;
    public SSPHandler getSSPHandler() {
        return sspHandler;
    }

    public void setSSPHandler(SSPHandler sspHandler) {
        this.sspHandler = sspHandler;
    }

    /**
     * The processRemarkService
     */
    private ProcessRemarkService processRemarkService;
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }

    public ProcessRemarkService getProcessRemarkService() {
        return processRemarkService;
    }

    /**
     * Default private constructor
     */
    public ListRulesHandler(){}

    /**
     * 
     * @param isUnorderedList
     * @param remarkMessage
     */
    public void checkSuspectedList(
            boolean isUnorderedList,
            String remarkMessage) {

        sspHandler.beginSelection().domXPathSelectNodeSet(MALFORMED_LIST);

        for (Node node : sspHandler.getSelectedElementList()) {
            boolean suspectedMalformedList = true;
            List<String> titleList = new ArrayList<String>();
            for (int j=0 ; j<node.getChildNodes().getLength() ; j++) {
                if (node.getChildNodes().item(j).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE) &&
                        !node.getChildNodes().item(j).getNodeValue().trim().isEmpty()) {
                    titleList.add(
                            node.getChildNodes().item(j).getNodeValue().trim());
                }
            }
            if (isUnorderedList) {
                suspectedMalformedList = checkUnorderedList(titleList);
            } else {
                suspectedMalformedList = checkOrderedList(titleList);
            }
            if (suspectedMalformedList) {
                    processRemarkService.addSourceCodeRemark(
                            TestSolution.NEED_MORE_INFO,
                            node,
                            remarkMessage,
                            node.getNodeName());
            }
        }
    }

    /**
     * 
     * @param titleList
     * @return
     */
    private boolean checkUnorderedList (List<String> titleList) {
        if (titleList.isEmpty() || titleList.size() == 1) {
            return false;
        }
        else {
            for (String title : titleList) {
                if (title.length() > TITLE_MAX_CHARACTERS ){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param titleList
     * @return
     */
    private boolean checkOrderedList (List<String> titleList) {
        if (titleList.isEmpty() || titleList.size() == 1) {
            return false;
        }
        else {
            int currentStartChar = -1;
            for (String title : titleList) {
                if (title.length() > TITLE_MAX_CHARACTERS){
                    return false;
                }
                if (currentStartChar == -1) {
                    currentStartChar = title.codePointAt(0);
                } else {
                    if (title.codePointAt(0)-1 != currentStartChar) {
                        return false;
                    } else {
                        currentStartChar = title.codePointAt(0);
                    }
                }
            }
        }
        return true;
    }

}