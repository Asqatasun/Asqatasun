/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler.extractor.listener;

import org.archive.modules.CrawlURI;

/**
 *
 * @author jkowalczyk
 */
public interface ExtractorCSSListener {

    public void computeCSSResource(CrawlURI curi);

}
