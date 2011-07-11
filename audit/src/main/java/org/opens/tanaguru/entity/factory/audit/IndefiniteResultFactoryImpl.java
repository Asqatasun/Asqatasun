package org.opens.tanaguru.entity.factory.audit;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.IndefiniteResultImpl;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class IndefiniteResultFactoryImpl implements IndefiniteResultFactory {

    public IndefiniteResultFactoryImpl() {
        super();
    }

    @Override
    public IndefiniteResult create() {
        return new IndefiniteResultImpl();
    }

    @Override
    public IndefiniteResult create(Test test, WebResource subject, String value) {
        IndefiniteResult instance = this.create();
        instance.setTest(test);
        instance.setSubject(subject);
        instance.setIndefiniteValue(value);
        return instance;
    }

    @Override
    public IndefiniteResult create(Test test, WebResource subject,
            String value, Collection<ProcessRemark> remarkList) {
        IndefiniteResult instance = this.create();
        instance.setTest(test);
        instance.setSubject(subject);
        instance.setIndefiniteValue(value);
        instance.addAllRemark(remarkList);
        return instance;
    }

}