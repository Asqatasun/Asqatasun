package org.opens.tanaguru.entity.factory.audit;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.DefiniteResultImpl;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class DefiniteResultFactoryImpl implements DefiniteResultFactory {

    public DefiniteResultFactoryImpl() {
        super();
    }

    public DefiniteResult create() {
        return new DefiniteResultImpl();
    }

    public DefiniteResult create(Test test, WebResource subject) {
        DefiniteResult definiteResult = this.create();
        definiteResult.setTest(test);
        definiteResult.setSubject(subject);
        return definiteResult;
    }

    public DefiniteResult create(Test test, WebResource subject,
            TestSolution value, Collection<ProcessRemark> remarkSet) {
        DefiniteResult definiteResult = this.create();
        definiteResult.setTest(test);
        definiteResult.setSubject(subject);
        definiteResult.setDefiniteValue(value);
        definiteResult.addAllRemark(remarkSet);
        return definiteResult;
    }
}
