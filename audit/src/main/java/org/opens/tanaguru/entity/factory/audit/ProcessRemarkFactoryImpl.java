package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessRemarkImpl;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * 
 * @author ADEX
 */
public class ProcessRemarkFactoryImpl implements ProcessRemarkFactory {

    public ProcessRemarkFactoryImpl() {
        super();
    }

    @Override
    public ProcessRemark create() {
        return new ProcessRemarkImpl();
    }

    @Override
    public ProcessRemark create(TestSolution issue, String messageCode) {
        ProcessRemark remark = this.create();

        remark.setIssue(issue);
        remark.setMessageCode(messageCode);

        return remark;
    }

}