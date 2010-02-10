package org.opens.tanaguru.contentadapter;

import java.util.Set;
import org.opens.tanaguru.entity.audit.SSP;

public interface HTMLParser {

    /**
     *
     * @return a set of content adapters
     */
    Set<ContentAdapter> getContentAdapterSet();

    /**
     *
     * @return a SSP
     */
    SSP getSSP();

    /**
     * parses an xml document and get the CSS and JavaScript resources using
     * handlers
     */
    void run();

    /**
     *
     * @param contentAdapterSet the set of content adapters to set
     */
    void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet);

    /**
     *
     * @param ssp
     *            the SSP to set
     */
    void setSSP(SSP ssp);
}
