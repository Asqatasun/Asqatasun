package org.opens.tanaguru.entity.audit;

import java.util.Date;

import com.adex.sdk.entity.Entity;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @version 1.0.0
 * @author ADEX
 */
public interface Audit extends Entity {

    /**
     *
     * @param contentList
     *            the content list to add
     */
    void addAllContent(List<? extends Content> contentList);

    /**
     *
     * @param grossResultList
     *            the gross result list to add
     */
    void addAllGrossResult(List<? extends ProcessResult> grossResultList);

    /**
     *
     * @param netResultList
     *            the net result list to add
     */
    void addAllNetResult(List<? extends ProcessResult> netResultList);

    /**
     *
     * @param testList
     *            the test set to add
     */
    void addAllTest(List<? extends Test> testList);

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
    List<? extends Content> getContentList();

    /**
     *
     * @return the date of creation
     */
    Date getDateOfCreation();

    /**
     *
     * @return the gross result list
     */
    List<? extends ProcessResult> getGrossResultList();

    /**
     *
     * @return the net result list
     */
    List<? extends ProcessResult> getNetResultList();

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
    List<? extends Test> getTestList();

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
    void setContentList(List<? extends Content> contentList);

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
    void setGrossResultList(List<? extends ProcessResult> grossResultList);

    /**
     *
     * @param netResultList
     *            the net result list to set
     */
    void setNetResultList(List<? extends ProcessResult> netResultList);

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
    void setTestList(List<? extends Test> testList);

    /**
     * 
     * @param parameterSet
     */
    void setParameterSet(Collection<? extends Parameter> parameterSet);

    /**
     * 
     * @return
     */
    Collection<? extends Parameter> getParameterSet();

    /**
     * 
     * @param parameter
     */
    void addParameter(Parameter parameter);
}