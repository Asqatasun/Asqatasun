package org.opens.tanaguru.contentadapter.html.util;

import java.util.Set;
import org.xml.sax.ContentHandler;

/**
 * 
 * @author ADEX
 */
public interface DOMContentHandlerDecorator extends ContentHandler {

    /**
     *
     * @param contentHandler
     */
    void addContentHandler(ContentHandler contentHandler);

    /**
     *
     * @return
     */
    Set<ContentHandler> getContentHandlerSet();

    /**
     *
     * @param contentHandler
     */
    void removeContentHandler(ContentHandler contentHandler);

    /**
     * 
     * @param contentHandlerSet
     */
    void setContentHandlerSet(Set<ContentHandler> contentHandlerSet);
}
