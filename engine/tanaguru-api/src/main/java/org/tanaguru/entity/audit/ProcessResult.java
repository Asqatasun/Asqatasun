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

import java.util.Collection;

import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessResult extends Entity {

    /**
     *
     * @param remarkSet
     *            the remark list to add
     */
    void addAllRemark(Collection<ProcessRemark> remarkSet);

    /**
     *
     * @param childResult
     *            the child process result to add
     */
    void addChildResult(ProcessResult childResult);

    /**
     *
     * @param remark
     *            the remark to add
     */
    void addRemark(ProcessRemark remark);

    /**
     *
     * @return the sub result list
     */
    Collection<ProcessResult> getChildResultList();

    /**
     *
     * @return the audit a the gross result
     */
    Audit getGrossResultAudit();

    /**
     *
     * @return the audit of a net result
     */
    Audit getNetResultAudit();

    /**
     *
     * @return the parent result
     */
    ProcessResult getParentResult();

    /**
     *
     * @return the remark list
     */
    Collection<ProcessRemark> getRemarkSet();

    /**
     *
     * @return the subject
     */
    WebResource getSubject();

    /**
     *
     * @return the test
     */
    Test getTest();

    /**
     *
     * @return the value
     */
    Object getValue();

    /**
     *
     * @return the number of tested elements
     */
    int getElementCounter();

    /**
     * Return the manual audit status
     * */
    Object getManualValue();
    
    /**
     *
     * @param subResultList
     *            the sub result list to set
     */
    void setChildResultList(Collection<ProcessResult> subResultList);

    /**
     *
     * @param grossResultAudit
     *            the audit of a gross result to set
     */
    void setGrossResultAudit(Audit grossResultAudit);

    /**
     *
     * @param netResultAudit
     *            the audit of a net result to set
     */
    void setNetResultAudit(Audit netResultAudit);

    /**
     *
     * @param parentResult
     *            the parent result to set
     */
    void setParentResult(ProcessResult parentResult);

    /**
     *
     * @param remarkList
     *            the remark list to set
     */
    void setRemarkSet(Collection<ProcessRemark> remarkList);

    /**
     *
     * @param subject
     *            the subject to set
     */
    void setSubject(WebResource subject);

    /**
     *
     * @param test
     *            the test to set
     */
    void setTest(Test test);

    /**
     *
     * @param value
     *            the value to set
     */
    void setValue(Object value);
    
    /**
     * 
     * @param elementCounter
     *              the number of tested elements
     */
    void setElementCounter(int elementCounter);
 
    /**
     *
     * @param manualValue
     *            the value to set
     */
    void setManualValue(Object manualValue);
}