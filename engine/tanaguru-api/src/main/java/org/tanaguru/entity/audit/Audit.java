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

import java.util.Date;

import org.tanaguru.sdk.entity.Entity;
import java.util.Collection;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public interface Audit extends Entity {

    /**
     *
     * @param contentList
     *            the content list to add
     */
    void addAllContent(Collection<Content> contentList);

    /**
     *
     * @param grossResultList
     *            the gross result list to add
     */
    void addAllGrossResult(Collection<ProcessResult> grossResultList);

    /**
     *
     * @param netResultList
     *            the net result list to add
     */
    void addAllNetResult(Collection<ProcessResult> netResultList);

    /**
     *
     * @param testList
     *            the test set to add
     */
    void addAllTest(Collection<Test> testList);

    /**
     *
     * @param content
     *            the content to add
     */
    void addContent(Content content);

    /**
     *
     * @param grossResult
     *            the gross result to add
     */
    void addGrossResult(ProcessResult grossResult);

    /**
     *
     * @param netResult
     *            the net result to add
     */
    void addNetResult(ProcessResult netResult);

    /**
     *
     * @param subject
     *            the subject to set
     */
    void setSubject(WebResource subject);

    /**
     *
     * @param test
     *            the test to add
     */
    void addTest(Test test);

    /**
     *
     * @return the comment
     */
    String getComment();

    /**
     *
     * @return the content list
     */
    Collection<Content> getContentList();

    /**
     *
     * @return the date of creation
     */
    Date getDateOfCreation();
    
    /**
    *
    * @return the date of creation
    */
    Date getManualAuditDateOfCreation();

    /**
     *
     * @return the gross result list
     */
    Collection<ProcessResult> getGrossResultList();

    /**
     *
     * @return the net result list
     */
    Collection<ProcessResult> getNetResultList();

    /**
     *
     * @return the status
     */
    AuditStatus getStatus();

    /**
     *
     * @return the subject
     */
    WebResource getSubject();

    /**
     *
     * @return the test list
     */
    Collection<Test> getTestList();

    /**
     *
     * @param comment
     *            the comment to set
     */
    void setComment(String comment);

    /**
     *
     * @param contentList
     *            the content list to set
     */
    void setContentList(Collection<Content> contentList);

    /**
     *
     * @param manualAuditDateOfCreation
     *            the date of manual audit launch to set
     */
    void setManualAuditDateOfCreation(Date manualAuditDateOfCreation);
    
    /**
    *
    * @param dateOfCreaction
    *            the date of content loading to set
    */
    void setDateOfCreation(Date dateOfCreaction);

    /**
     *
     * @param grossResultList
     *            the gross result list to set
     */
    void setGrossResultList(Collection<ProcessResult> grossResultList);

    /**
     *
     * @param netResultList
     *            the net result list to set
     */
    void setNetResultList(Collection<ProcessResult> netResultList);

    /**
     *
     * @param status
     *            the status to set
     */
    void setStatus(AuditStatus status);

    /**
     *
     * @param testList
     *            the test list to set
     */
    void setTestList(Collection<Test> testList);

    /**
     * 
     * @param parameterSet
     */
    void setParameterSet(Collection<Parameter> parameterSet);

    /**
     * 
     * @return
     */
    Collection<Parameter> getParameterSet();

    /**
     * 
     * @param parameter
     */
    void addParameter(Parameter parameter);
}