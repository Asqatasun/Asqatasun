package com.oceaneconsulting.tanaguru.decorator.impl;

import java.util.List;

import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;

import com.oceaneconsulting.tanaguru.dao.StatisticsDAO;
import com.oceaneconsulting.tanaguru.decorator.WebResourceDataServiceDecorator;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import com.oceaneconsulting.tanaguru.ws.types.GlobalStatsOrder;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
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
@Service("webResourceDataServiceDecorator")
public class WebResourceDataServiceDecoratorImpl extends AbstractGenericDataService<WebResource, Long> 
                implements WebResourceDataServiceDecorator {

    @Autowired
    private WebResourceDataService webResourceDataService;

    @Autowired
    private StatisticsDAO statisticsDAO;

    @Override
    public AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit) {
        return statisticsDAO.findWeightedMarkAndStatusByAuditId(idAudit);
    }

    @Override
    public List<AuditResult> findWeightedMarkAndStatus(GlobalStatsOrder globalStatsOrder) {
        return statisticsDAO.findWeightedMarkAndStatus(globalStatsOrder);
    }

    @Override
    public WebResource getWebResourceFullDeep(Long id) {
        WebResource wr = this.read(id);
        for (ProcessResult pr : wr.getProcessResultList()) {
            for (ProcessRemark prk : pr.getRemarkSet()) {
                for (EvidenceElement ee : prk.getElementList()) {}
            }
        }
        return wr;
    }
    
    @Override
    public Page createPage(String arg0) {
        return webResourceDataService.createPage(arg0);
    }

    @Override
    public Site createSite(String arg0) {
        return webResourceDataService.createSite(arg0);
    }

    @Override
    public WebResource getByUrl(String arg0) {
        return webResourceDataService.getByUrl(arg0);
    }

    @Override
    public WebResource getByUrlAndParentWebResource(String arg0,
            WebResource arg1) {
        return webResourceDataService.getByUrlAndParentWebResource(arg0, arg1);
    }

    @Override
    public Long getNumberOfChildWebResource(WebResource arg0) {
        return webResourceDataService.getNumberOfChildWebResource(arg0);
    }

    @Override
    public List<WebResource> getWebResourceFromItsParent(WebResource arg0,
            int arg1, int arg2) {
        return webResourceDataService.getWebResourceFromItsParent(arg0, arg1, arg2);
    }

}