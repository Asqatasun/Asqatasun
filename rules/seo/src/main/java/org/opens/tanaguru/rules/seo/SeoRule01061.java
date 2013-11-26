/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.seo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractSiteRuleImplementation;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.SITEMAP_MSG;
import org.opens.tanaguru.service.ProcessRemarkService;
import org.opens.tanaguru.util.http.HttpRequestHandler;

/**
 * Is the sitemap.xml file present in the root directory of the site? 
 * 
 * @author jkowalczyk
 */
public class SeoRule01061 extends AbstractSiteRuleImplementation {

    /* the sitemap URL suffix*/
    private static final String SITEMAP_URL_SUFFIX = "/sitemap.xml";

    public SeoRule01061() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.getProcessRemarkService().resetService();
        return indefiniteResultFactory.create(
                        test, 
                        sspHandler.getPage(),
                        "");
    }

    @Override
    protected DefiniteResult consolidateSiteImpl(
            Site group, 
            List<ProcessResult> groupedGrossResultList, 
            ProcessRemarkService processRemarkService) {
        processRemarkService.resetService();
        String robotsTxtContent = null;
        try {
            robotsTxtContent = HttpRequestHandler.getInstance().getHttpContent(group.getURL()+SITEMAP_URL_SUFFIX);
        } catch (URISyntaxException ex) {
            Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SeoRule01051.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (StringUtils.isEmpty(robotsTxtContent)) {
            processRemarkService.addConsolidationRemark(
                    TestSolution.FAILED, 
                    SITEMAP_MSG);
            return definiteResultFactory.create(
                        test, 
                        group,
                        TestSolution.FAILED, 
                        processRemarkService.getRemarkList());
        }
        return definiteResultFactory.create(
                        test, 
                        group,
                        TestSolution.PASSED, 
                        processRemarkService.getRemarkList());
    }

}
