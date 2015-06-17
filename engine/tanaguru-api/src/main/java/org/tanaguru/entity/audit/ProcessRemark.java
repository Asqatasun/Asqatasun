/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
package org.tanaguru.entity.audit;

import org.tanaguru.sdk.entity.Entity;
import java.util.Collection;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessRemark extends Entity {

    /**
     *
     * @return the issue
     */
    TestSolution getIssue();

    /**
     * 
     * @return the message code
     */
    String getMessageCode();

    /**
     *
     * @return the process result
     */
    ProcessResult getProcessResult();

    /**
     *
     * @return the selected element
     */
    String getSelectedElement();

    /**
     *
     * @return the selection expression
     */
    String getSelectionExpression();

    /**
     *
     * @param issue
     *            the issue to set
     */
    void setIssue(TestSolution issue);

    /**
     *
     * @param messageCode
     *              the message code to set
     */
    void setMessageCode(String messageCode);

    /**
     *
     * @param processResult
     *            the process result to set
     */
    void setProcessResult(ProcessResult processResult);

    /**
     *
     * @param element the selected element to set
     */
    void selectedElement(String element);

    /**
     * 
     * @param selectionExpression the selection expression to set
     */
    void setSelectionExpression(String selectionExpression);

    /**
     *
     * @param element
     *            the element to add
     */
    void addElement(EvidenceElement element);

    /**
     *
     * @return the elements
     */
    Collection<EvidenceElement> getElementList();

    /**
     *
     * @param elements
     *            the elements to set
     */
    void setElementList(Collection<EvidenceElement> elements);

}