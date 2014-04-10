package com.oceaneconsulting.tanaguru.decorator;



import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

import com.oceaneconsulting.tanaguru.ws.types.AuditResult;

public interface WebResourceDataServiceDecorator extends WebResourceDataService {
	public AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit);
}
