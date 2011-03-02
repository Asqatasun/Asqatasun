package org.opens.tanaguru.contentadapter;

import java.util.List;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author Adex
 */
public interface ContentAdapter {// TODO Write javadoc

    /**
     *
     * @return
     *      the result of the adaptation
     */
    String getAdaptation();

    /**
     *
     * @return
     *      the list of content
     */
    List<Content> getContentList();

    /**
     * 
     * @return
     *      the current ssp
     */
    SSP getSSP();

    /**
     * 
     * @param parser
     */
    void setParser(ContentParser parser);

    /**
     *
     * @param urlIdentifier
     */
    void setURLIdentifier(URLIdentifier urlIdentifier);

    /**
     * 
     * @param contentFactory
     */
    void setContentFactory(ContentFactory contentFactory);

    /**
     * 
     * @param ssp
     */
    void setSSP(SSP ssp);
}
