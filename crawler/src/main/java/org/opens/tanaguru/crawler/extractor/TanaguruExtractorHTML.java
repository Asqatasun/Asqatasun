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


import java.util.Collection;
import org.archive.modules.CrawlURI;
import org.archive.modules.extractor.ExtractorHTML;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorHTMLListener;

/**
 * Basic link-extraction, from an HTML content-body,
 * using regular expressions.
 *
 * @author gojomo
 *
 */
public class TanaguruExtractorHTML extends ExtractorHTML {

    public Collection<ExtractorHTMLListener> getExtractorHTMLListenerSet() {
        return (Collection<ExtractorHTMLListener>) kp.get("extractorHTMLListenerSet");
    }

    public void setExtractorHTMLListenerSet(
            Collection<ExtractorHTMLListener> extractorHTMLListenerSet) {
        kp.put("extractorHTMLListenerSet", extractorHTMLListenerSet);
    }

   @Override
   public boolean innerExtract(CrawlURI curi) {
       boolean extractionResult = super.innerExtract(curi);
       if (extractionResult){
           dispatchToListener(curi);
       }
       return extractionResult;
    }

    private void dispatchToListener(CrawlURI curi){
        for (ExtractorHTMLListener extractorHTMLListener : getExtractorHTMLListenerSet()) {
            extractorHTMLListener.computeResource(curi);
        }
    }

}

