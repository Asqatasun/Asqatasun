package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.audit.Audit;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface AuditService {// TODO Write javadoc

    void add(AuditServiceListener listener);

    void remove(AuditServiceListener listener);

    Audit adaptContent(Audit audit);

    Audit analyse(Audit audit);

    Audit audit(Audit audit);

    Audit consolidate(Audit audit);

    Audit crawl(Audit audit);

    Audit auditPage(String pageUrl, String[] testCodeList);

    Audit auditSite(String siteUrl, String[] testCodeList);

    Audit auditSite(String siteUrl, String[] pageUrlList, String[] testCodeList);// XXX Exists while there's no crawler

    Audit loadContent(Audit audit);

    Audit process(Audit audit);

    Audit init(Audit audit);
}
