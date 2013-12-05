package org.opens.tanaguru.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 
 * @author jkowalczyk
 */
@WebService
public class HelloWorld {

//    private AuditService auditService;

    public HelloWorld() {
        super();
    }

//    public void setAuditService(AuditService auditService) {
//        this.auditService = auditService;
//    }

    @WebMethod
    public String hello() {
        return "world";
    }

//    @WebMethod
//    public AuditImpl auditSite(String siteURL, String[] testCodeList) {
//    public AuditImpl auditSite(String siteURL, String[] testCodeList) {
//        return (AuditImpl) auditService.auditSite(siteURL, testCodeList);
//    }

//    @WebMethod
//    public AuditImpl auditSiteWithoutCrawler(String siteUrl, String[] pageUrlList, String[] testCodeList) {
//        return (AuditImpl) auditService.auditSite(siteUrl, pageUrlList, testCodeList);
//    }
}
