package com.oceaneconsulting.tanaguru.decorator.impl;

import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;

import com.oceaneconsulting.tanaguru.decorator.AuditDataServiceDecorator;
import java.util.Collection;
import java.util.Date;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.springframework.stereotype.Service;

/**
 * This class wrap
 * {@link org.opens.tanaguru.entity.service.subject.WebResourceDataService}
 * class. It enlarge default webresource data service by adding specific
 * statistics.
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
@Service("decoratorAuditDataService")
public class AuditDataServiceDecoratorImpl extends AbstractGenericDataService<Audit, Long>
        implements AuditDataServiceDecorator {

    @Autowired
    private AuditDataService auditDataService;

    @Override
    public Audit getAuditFullDeep(Long id) {
        Audit audit = auditDataService.read(id);
        for (ProcessResult pr : audit.getSubject().getProcessResultList()) {
            for (ProcessRemark prk : pr.getRemarkSet()) {
                for (EvidenceElement ee : prk.getElementList()) {}
            }
        }
        for (Parameter param : audit.getParameterSet()) {}
        return audit;
    }

    @Override
    public Audit create(Date date) {
        return auditDataService.create(date);
    }

    @Override
    public Collection<Audit> findAll(AuditStatus status) {
        return auditDataService.findAll(status);
    }

    @Override
    public Audit getAuditWithTest(Long id) {
        return auditDataService.getAuditWithTest(id);
    }

}
