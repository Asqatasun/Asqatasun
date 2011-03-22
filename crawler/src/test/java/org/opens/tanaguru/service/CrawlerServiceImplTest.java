/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import junit.framework.TestCase;
import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.crawler.CrawlerImpl;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.AuditFactory;
import org.opens.tanaguru.entity.factory.audit.AuditFactoryImpl;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactoryImpl;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * This class is a functionnal test class of the crawler service.
 * @author jkowalczyk
 */
public class CrawlerServiceImplTest extends TestCase {

    private static final String FULL_SITE_CRAWL_URL_KEY = "full-site-crawl-url";
    private static final String ROBOTS_RESTRICTED_CRAWL_URL_KEY =
            "robots-restricted-crawl-url";
    private static final String SITES_URL_BUNDLE_NAME = "sites-url";
    private static final String FULL_SITE_CRAWL_CONF_FILE_PATH =
            System.getenv("PWD") + "/src/test/resources/full-site-crawl-conf/";
    private static final String PAGE_CRAWL_CONF_FILE_PATH =
            System.getenv("PWD") + "/src/test/resources/page-crawl-conf/";
    private static final String PAGE_NAME_LEVEL1 = "page-1.html";
    private static final String PAGE_NAME_LEVEL2 = "page-2.html";
    private static final String FORBIDDEN_PAGE_NAME = "page-access-forbidden-for-robots.html";

    private final ResourceBundle bundle =
            ResourceBundle.getBundle(SITES_URL_BUNDLE_NAME);

    private CrawlerService crawlerService;
    private Crawler crawler;
    private WebResourceFactory webResourceFactory = new WebResourceFactoryImpl();
    private AuditFactory auditFactory = new AuditFactoryImpl();
    private ContentDataService contentDataService;
    private AuditDataService auditDataService;
    protected BeanFactory springBeanFactory;
    private static final String SPRING_FILE_PATH =
            "../crawler/src/test/resources/context/application-context.xml";

    public CrawlerServiceImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ApplicationContext springApplicationContext =
                new FileSystemXmlApplicationContext(SPRING_FILE_PATH);
        springBeanFactory = springApplicationContext;
        crawler = (Crawler)
                springBeanFactory.getBean("crawlerComponent");
        crawlerService = (CrawlerService)
                springBeanFactory.getBean("crawlerService");
        contentDataService= (ContentDataService)
                springBeanFactory.getBean("contentDataService");
        auditDataService= (AuditDataService)
                springBeanFactory.getBean("auditDataService");
        auditFactory = (AuditFactory)springBeanFactory.getBean("auditFactory");
        webResourceFactory = (WebResourceFactory)springBeanFactory.getBean("webResourceFactory");
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
        Audit audit = auditFactory.create(new Date());
        auditDataService.saveOrUpdate(audit);
        Site site = webResourceFactory.createSite(siteUrl);
        site.setAudit(auditFactory.create());
        site = crawlerService.crawl(site);
        List<Long> contentListId = contentDataService.getSSPFromWebResource(site.getId(), 0, 10);
        List<Content> contentList = new ArrayList<Content>();
        for (Long id : contentListId) {
            Content content = contentDataService.readWithRelatedContent(id);
            if (content != null) {
                contentList.add(content);
            }
        }
        for (Content content : contentList) {
            System.out.println("content  " + content.getURI() + " code " + content.getHttpStatusCode());
        }
        assertEquals(4, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
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
        page = crawlerService.crawl(page);
        List<Long> contentListId = contentDataService.getSSPFromWebResource(page.getId(), 0, 10);
        System.out.println("contentListId  " + contentListId.size());
        List<Content> contentList = new ArrayList<Content>();
        for (Long id : contentListId) {
            System.out.println("id " + id +" " + contentDataService.read(id).getHttpStatusCode());
            contentList.add(contentDataService.readWithRelatedContent(id));
        }
        assertEquals(1, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
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
        site = crawlerService.crawl(site);
        List<Long> contentListId = contentDataService.getSSPFromWebResource(site.getId(), 0, 10);
        List<Content> contentList = new ArrayList<Content>();
        for (Long id : contentListId) {
            contentList.add(contentDataService.readWithRelatedContent(id));
        }
        assertEquals(3, contentList.size()+((SSP)contentList.iterator().next()).getRelatedContentSet().size());
        Set<String> urlSet = getUrlSet(contentList);
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