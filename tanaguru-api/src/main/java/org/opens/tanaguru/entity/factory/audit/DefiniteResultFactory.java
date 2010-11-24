package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;
import com.adex.sdk.entity.factory.GenericFactory;
import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface DefiniteResultFactory extends GenericFactory<DefiniteResult> {

    /**
     *
     * @param test
     *            the test to set
     * @param subject
     *            the subject to set
     * @return a new instance of DefiniteResult
     */
    public DefiniteResult create(Test test, WebResource subject);

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
     * @return a new instance of DefiniteResult
     */
    public DefiniteResult create(Test test, WebResource subject,
            TestSolution value, Collection<ProcessRemark> remarkSet);
}
