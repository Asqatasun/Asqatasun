package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import com.adex.sdk.entity.service.GenericDataService;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ContentDataService extends GenericDataService<Content, Long> {

    /**
     *
     * @param audit
     *            the audit to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link JavascriptContent} instance found.
     */
    JavascriptContent findJavascriptContent(Audit audit, String uri);

    /**
     *
     * @param audit
     *            the audit to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link SSP} instance found
     */
    SSP findSSP(Audit audit, String uri);

    /**
     *
     * @param webresource
     *            the webresource to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link SSP} instance found
     */
    SSP findSSP(WebResource webresource, String uri);

    /**
     *
     * @param audit
     *            the audit to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link StylesheetContent} instance found.
     */
    StylesheetContent findStylesheetContent(Audit audit, String uri);
}
