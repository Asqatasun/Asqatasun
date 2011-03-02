/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler;

import java.io.IOException;
import org.archive.io.RecordingInputStream;
import org.archive.modules.CrawlURI;

/**
 *
 * @author jkowalczyk
 */
public interface ContentWriter {

    /**
     *
     * @param resourceContentType
     * @param curi
     */
    void computeAndPersistUnsuccessfullFetchedResource(CrawlURI curi);

    /**
     * 
     * @param resourceContentType
     * @param curi
     */
    void computeAndPersistSuccessfullFetchedResource(
            CrawlURI curi,
            RecordingInputStream recis) throws IOException;

}
