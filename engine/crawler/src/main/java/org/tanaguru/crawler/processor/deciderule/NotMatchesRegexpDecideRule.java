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
package org.tanaguru.crawler.processor.deciderule;

import java.util.List;

import java.util.regex.Pattern;

import org.archive.modules.CrawlURI;
import org.archive.modules.deciderules.NotMatchesListRegexDecideRule;

/**
 * Rule applies configured decision to any CrawlURIs whose String URI
 * matches the supplied regexs.
 * <p>
 * The list of regular expressions can be considered logically AND or OR.
 *
 * @author Kristinn Sigurdsson
 * 
 * @see MatchesRegexDecideRule
 */
public class NotMatchesRegexpDecideRule extends NotMatchesListRegexDecideRule {
    
    /**
     * Override heritrix implementation to deal with emtpy list injection
     * and thus avoid a rejection on empty list
     * 
     * @param object Object to make decision about.
     * @return true if the regexs are not matched
     */
    @Override
    protected boolean evaluate(CrawlURI object) {
        List<Pattern> regexes = getRegexList();
        if(regexes.isEmpty()){
            return false;
        }
        return super.evaluate(object);
    }

}