package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.List;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ContentDAO extends GenericDAO<Content, Long> {

    /**
     *
     * @param audit
     *            the audit to find from
     * @param uri
     *            the URI to find
     * @return the content
     */
    Content find(Audit audit, String uri);

    /**
     *
     * @param webresource
     *            the webresource to find from
     * @param uri
     *            the URI to find
     * @return the content
     */
    Content find(WebResource webresource, String uri);

    /**
     *
     * @param audit
     * @param start
     * @param chunkSize
     * @return
     */
    public List<? extends Content> retrieveSSPContentWithRelatedContent(Audit audit, int start, int chunkSize);

    /**
     * 
     * @param audit
     * @return
     */
    public Long retrieveNumberOfSSPContentFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    public boolean hasContent(Audit audit);

    /**
     * 
     * @param audit
     * @return
     */
    public boolean hasAdaptedSSP(Audit audit);
}
