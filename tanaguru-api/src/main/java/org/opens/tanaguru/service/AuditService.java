package org.opens.tanaguru.service;

import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.entity.parameterization.Parameter;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface AuditService {

    /**
     *
     * @param listener
     */
    void add(AuditServiceListener listener);

    /**
     *
     * @param listener
     */
    void remove(AuditServiceListener listener);

    /**
     *
     * @param audit
     * @return
     */
    Audit adaptContent(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Audit analyse(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Audit audit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Audit consolidate(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Audit crawl(Audit audit);

    /**
     *
     * @param pageUrl
     * @param testCodeList
     * @param paramSet
     * @return
     */
    Audit auditPage(String pageUrl, String[] testCodeList, Set<Parameter> paramSet);

    /**
     *
     * @param siteUrl
     * @param testCodeList
     * @param paramSet
     * @return
     */
    Audit auditSite(String siteUrl, String[] testCodeList, Set<Parameter> paramSet);

    /**
     *
     * @param siteUrl
     * @param pageUrlList
     * @param testCodeList
     * @param paramSet
     * @return
     */
    Audit auditSite(String siteUrl, String[] pageUrlList, String[] testCodeList, Set<Parameter> paramSet);

    /**
     *
     * @param audit
     * @return
     */
    Audit loadContent(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Audit process(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Audit init(Audit audit);
}
