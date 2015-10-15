/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.seo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractSiteRuleImplementation;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SITEMAP_MSG;
import org.tanaguru.service.ProcessRemarkService;
import org.tanaguru.util.http.HttpRequestHandler;

/**
 * Is the sitemap.xml file present in the root directory of the site? 
 * 
 * @author jkowalczyk
 */
public class SeoRule01061 extends AbstractSiteRuleImplementation {

    /* the sitemap xml URL suffix*/
    private static final String SITEMAP_XML_URL_SUFFIX = "/sitemap.xml";
    /* the sitemap txt URL suffix*/
    private static final String SITEMAP_TXT_URL_SUFFIX = "/sitemap.txt";

    public SeoRule01061() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.getProcessRemarkService().resetService();
        return processResultDataService.getIndefiniteResult(
                        test,
                        sspHandler.getPage(), 
                        sspHandler.getSSP().getSource());
    }

    @Override
    protected DefiniteResult consolidateSiteImpl(
            Site group, 
            List<ProcessResult> groupedGrossResultList, 
            ProcessRemarkService processRemarkService) {
        processRemarkService.resetService();
        String robotsTxtContent = null;
        try {
            robotsTxtContent = 
                    HttpRequestHandler.getInstance().getHttpContent(group.getURL()+SITEMAP_XML_URL_SUFFIX);
        } catch (URISyntaxException ex) {
            Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (StringUtils.isEmpty(robotsTxtContent)) {
            try {
            robotsTxtContent = 
                    HttpRequestHandler.getInstance().getHttpContent(group.getURL()+SITEMAP_TXT_URL_SUFFIX);
            } catch (URISyntaxException ex) {
                Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (StringUtils.isEmpty(robotsTxtContent)) {
            processRemarkService.addConsolidationRemark(
                    TestSolution.FAILED, 
                    SITEMAP_MSG);
            return processResultDataService.getDefiniteResult(
                        test, 
                        group,
                        TestSolution.FAILED, 
                        processRemarkService.getRemarkList());
        }
        return processResultDataService.getDefiniteResult(
                        test, 
                        group,
                        TestSolution.PASSED, 
                        processRemarkService.getRemarkList());
    }

}
