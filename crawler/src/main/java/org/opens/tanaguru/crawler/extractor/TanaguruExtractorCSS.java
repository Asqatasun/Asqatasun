/*
 *  This file is part of the Heritrix web crawler (crawler.archive.org).
 *
 *  Licensed to the Internet Archive (IA) by one or more individual 
 *  contributors. 
 *
 *  The IA licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.opens.tanaguru.crawler.extractor;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import org.apache.commons.httpclient.URIException;
import org.archive.io.ReplayCharSequence;
import org.archive.modules.CrawlURI;
import org.archive.modules.deciderules.MatchesFilePatternDecideRule;
import org.archive.modules.extractor.Extractor;
import org.archive.modules.extractor.ExtractorCSS;
import org.archive.modules.extractor.Hop;
import org.archive.modules.extractor.Link;
import org.archive.modules.extractor.LinkContext;
import org.archive.net.UURI;
import org.archive.util.ArchiveUtils;
import org.archive.util.DevUtils;
import org.archive.util.TextUtils;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorCSSListener;

/**
 * This extractor extends the Extractor CSS class from heritrix.
 * It only keeps css imports from the extraction (to avoid to download other
 * useless contents like images for example).
 * @author Igor Ranitovic
 *
 **/

public class TanaguruExtractorCSS extends ExtractorCSS {

    private static Logger logger =
        Logger.getLogger("org.opens.tanaguru.crawler.extractor.TanaguruExtractorCSS");

    private static String ESCAPED_AMP = "&amp";
    // CSS escapes: "Parentheses, commas, whitespace characters, single
    // quotes (') and double quotes (") appearing in a URL must be
    // escaped with a backslash"
    static final String CSS_BACKSLASH_ESCAPE = "\\\\([,'\"\\(\\)\\s])";
    
    /**
     *  CSS URL extractor pattern.
     *
     *  This pattern extracts URIs for CSS files
     **/
    static final String CSS_URI_EXTRACTOR =
        "(?i)(?:@import (?:url[(]|)|url[(])\\s*([\\\"\']?)" + // G1
        "([^\\\"\'].{0,"+UURI.MAX_URL_LENGTH+"}?)\\1\\s*[);]"; // G2

    public Collection<ExtractorCSSListener> getExtractorCSSListenerSet() {
        return (Collection<ExtractorCSSListener>) kp.get("extractorCSSListenerSet");
    }

    public void setExtractorCSSListenerSet(
            Collection<ExtractorCSSListener> extractorCSSListenerSet) {
        kp.put("extractorCSSListenerSet", extractorCSSListenerSet);
    }
    
    /**
     */
    public TanaguruExtractorCSS() {
        super();
    }

    /**
     * @param curi Crawl URI to process.
     */
    @Override
    public boolean innerExtract(CrawlURI curi) {
        ReplayCharSequence cs = null;
        try {
            cs = curi.getRecorder().getReplayCharSequence();
            numberOfLinksExtracted.addAndGet(
                processStyleCode(this, curi, cs));
            // Set flag to indicate that link extraction is completed.
            dispatchToListener(curi);
            return true;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Problem with ReplayCharSequence: " + e.getMessage(), e);
        } finally {
            ArchiveUtils.closeQuietly(cs);
        }
        return false; 
    }

    public static long processStyleCode(Extractor ext, 
            CrawlURI curi, CharSequence cs) {
        long foundLinks = 0;
        Matcher uris = null;
        String cssUri;
        try {
            uris = TextUtils.getMatcher(CSS_URI_EXTRACTOR, cs);
            while (uris.find()) {
                cssUri = uris.group(2);
                // TODO: Escape more HTML Entities.
                cssUri = TextUtils.replaceAll(ESCAPED_AMP, cssUri, "&");
                // Remove backslashes when used as escape character in CSS URL
                cssUri = TextUtils.replaceAll(CSS_BACKSLASH_ESCAPE, cssUri,
                        "$1");
                foundLinks++;
                int max = ext.getExtractorParameters().getMaxOutlinks();
                try {
                    // We check here that the extracted uri is not an image
                    if (!MatchesFilePatternDecideRule.Preset.IMAGES.
                            getPattern().matcher(cssUri).matches()) {
                        Link.addRelativeToBase(curi, max, cssUri,
                            LinkContext.EMBED_MISC, Hop.EMBED);
                    }
                } catch (URIException e) {
                    ext.logUriError(e, curi.getUURI(), cssUri);
                }
            }
        } catch (StackOverflowError e) {
            DevUtils.warnHandle(e, "ExtractorCSS StackOverflowError");
        } finally {
            TextUtils.recycleMatcher(uris);
        }
        return foundLinks;
    }

    private void dispatchToListener(CrawlURI curi){
        for (ExtractorCSSListener extractorCSSListener : getExtractorCSSListenerSet()) {
            extractorCSSListener.computeCSSResource(curi);
        }
    }
}
