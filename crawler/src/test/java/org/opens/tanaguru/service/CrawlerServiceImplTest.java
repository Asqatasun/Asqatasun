/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.service;

import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import junit.framework.TestCase;
import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.crawler.CrawlerImpl;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.factory.audit.AuditFactory;
import org.opens.tanaguru.entity.factory.audit.AuditFactoryImpl;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactoryImpl;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactoryImpl;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;

/**
 * This class is a functionnal test class of the crawler service.
 * @author jkowalczyk
 */
public class CrawlerServiceImplTest extends TestCase {

    private static final String FULL_SITE_CRAWL_URL_KEY = "full-site-crawl-url";
    private static final String ROBOTS_RESTRICTED_CRAWL_URL_KEY =
            "robots-restricted-crawl-url";
    private static final String SITEMAP_RESTRICTED_CRAWL_URL_KEY =
            "sitemap-restricted-crawl-url";
    private static final String SITES_URL_BUNDLE_NAME = "sites-url";
    private static final String FULL_SITE_CRAWL_CONF_FILE_PATH =
            System.getenv("PWD") + "/src/test/resources/full-site-crawl-conf/";
    private static final String PAGE_CRAWL_CONF_FILE_PATH =
            System.getenv("PWD") + "/src/test/resources/page-crawl-conf/";
    private static final String PAGE_NAME_LEVEL1 = "page-1.html";
    private static final String PAGE_NAME_LEVEL2 = "page-2.html";
    private static final String FORBIDDEN_PAGE_NAME = "page-access-forbidden-for-robots.html";
    private static final String OUTPUT_DIR = "/tmp";

    private final ResourceBundle bundle =
            ResourceBundle.getBundle(SITES_URL_BUNDLE_NAME);

    private CrawlerService crawlerService = new CrawlerServiceImpl();
    private Crawler crawler = new CrawlerImpl();
    private WebResourceFactory webResourceFactory = new WebResourceFactoryImpl();
    private AuditFactory auditFactory = new AuditFactoryImpl();
    private ContentFactory contentFactory = new ContentFactoryImpl();
    
    public CrawlerServiceImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        crawler.setWebResourceFactory(webResourceFactory);
        ((CrawlerImpl)crawler).setContentFactory(contentFactory);
        ((CrawlerImpl)crawler).setOutputDir(OUTPUT_DIR);
        crawlerService.setCrawler(crawler);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * * Test the crawl of a site without robots.txt file
     */
    public void testCrawl_Site() {
        System.out.println("crawl_full_site");
        ((CrawlerImpl)crawler).setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        Site site = webResourceFactory.createSite(siteUrl);
        site.setAudit(auditFactory.create());
        crawlerService.crawl(site);
        assertEquals(4, ((CrawlerImpl)crawler).getContentListResult().size());
        Set<String> urlSet = getUrlSet(((CrawlerImpl)crawler).getContentListResult());
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl+PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl+PAGE_NAME_LEVEL2));
        assertTrue(urlSet.contains(siteUrl+FORBIDDEN_PAGE_NAME));
    }

    /**
     * Test the crawl of a page
     */
    public void testCrawl_Page() {
        System.out.println("crawl_page");
        ((CrawlerImpl)crawler).setCrawlConfigFilePath(PAGE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        Page page = webResourceFactory.createPage(siteUrl);
        page.setAudit(auditFactory.create());
        crawlerService.crawl(page);
        assertEquals(1, ((CrawlerImpl)crawler).getContentListResult().size());
        Set<String> urlSet = getUrlSet(((CrawlerImpl)crawler).getContentListResult());
        assertTrue(urlSet.contains(siteUrl));
        assertFalse(urlSet.contains(siteUrl+PAGE_NAME_LEVEL1));
        assertFalse(urlSet.contains(siteUrl+PAGE_NAME_LEVEL2));
        assertFalse(urlSet.contains(siteUrl+FORBIDDEN_PAGE_NAME));
    }

    /**
     * Test the crawl of a site with robots.txt file
     */
    public void testCrawl_Site_With_Robots() {
        System.out.println("crawl_site_with_robots");
        ((CrawlerImpl)crawler).setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(ROBOTS_RESTRICTED_CRAWL_URL_KEY);
        Site site = webResourceFactory.createSite(siteUrl);
        site.setAudit(auditFactory.create());
        crawlerService.crawl(site);
        assertEquals(3, ((CrawlerImpl)crawler).getContentListResult().size());
        Set<String> urlSet = getUrlSet(((CrawlerImpl)crawler).getContentListResult());
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl+PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl+PAGE_NAME_LEVEL2));
        assertFalse(urlSet.contains(siteUrl+FORBIDDEN_PAGE_NAME));
    }

    private Set<String> getUrlSet(List<Content> contentList) {
        Set<String> urlSet = new HashSet<String>();
        for (Content content  : contentList) {
            urlSet.add(content.getURI());
        }
        return urlSet;
    }

//    /**
//     * Test of crawl method, of class CrawlerServiceImpl.
//     */
//    public void testCrawl_Site_With_SiteMap() {
//        System.out.println("crawl_site_with_sitemap");
//        crawler.setSiteURL(null);
//        CrawlerServiceImpl instance = new CrawlerServiceImpl();
//        Site expResult = null;
//        Site result = instance.crawl(site);
//    }

}