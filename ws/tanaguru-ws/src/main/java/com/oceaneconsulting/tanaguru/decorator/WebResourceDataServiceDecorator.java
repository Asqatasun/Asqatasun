package com.oceaneconsulting.tanaguru.decorator;

import java.util.List;

import org.tanaguru.entity.service.subject.WebResourceDataService;

import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import com.oceaneconsulting.tanaguru.ws.types.GlobalStatsOrder;
import org.tanaguru.entity.subject.WebResource;

/**
 * This interface define added statistic methods to the wrapped statistics
 * service.
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
public interface WebResourceDataServiceDecorator extends WebResourceDataService {

    /**
     * Global statistics for a given audit.
     *
     * @param idAudit audit identifier
     * @return Audit global result
     */
    AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit);

    /**
     * 
     * @param globalStatsOrder
     * @return 
     */
    List<AuditResult> findWeightedMarkAndStatus(GlobalStatsOrder globalStatsOrder);
    
    /**
     * 
     * @param id
     * @return 
     */
    WebResource getWebResourceFullDeep(Long id);
}
