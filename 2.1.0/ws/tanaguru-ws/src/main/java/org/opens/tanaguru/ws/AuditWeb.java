package org.opens.tanaguru.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import org.opens.tanaguru.entity.audit.AuditImpl;
import org.opens.tanaguru.service.AuditService;

/**
 * 
 * @author jkowalczyk
 */
@WebService
public class AuditWeb {

    private AuditService auditService;

    public AuditWeb() {
        super();
    }

    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @WebMethod
    public AuditImpl auditPage(String pageURL, String[] testCodeList) {
        return (AuditImpl) auditService.auditPage(pageURL, testCodeList);
    }

    @WebMethod
    public AuditImpl auditSite(String siteURL, String[] testCodeList) {
        return (AuditImpl) auditService.auditSite(siteURL, testCodeList);
    }

    @WebMethod
    public AuditImpl auditSiteWithoutCrawler(String siteUrl, String[] pageUrlList, String[] testCodeList) {
        return (AuditImpl) auditService.auditSite(siteUrl, pageUrlList, testCodeList);
    }
}
