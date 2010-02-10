package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;
import com.adex.sdk.entity.factory.GenericFactory;
import java.util.List;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface IndefiniteResultFactory extends
        GenericFactory<IndefiniteResult> {

    /**
     *
     * @param test
     *            the test to set
     * @param subject
     *            the subject to set
     * @param value
     *            the value to set
     * @return a new instance of IndefiniteResult
     */
    public IndefiniteResult create(Test test, WebResource subject, String value);

    /**
     *
     * @param test
     *            the test to set
     * @param subject
     *            the subject to set
     * @param value
     *            the value to set
     * @param remarkList
     *            the remark list to set
     * @return a new instance of IndefiniteResult
     */
    IndefiniteResult create(Test test, WebResource subject, String value,
            List<ProcessRemark> remarkList);
}
