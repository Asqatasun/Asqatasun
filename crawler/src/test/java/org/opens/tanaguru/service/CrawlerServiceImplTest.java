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
import org.apache.http.HttpStatus;
import org.opens.tanaguru.crawler.util.CrawlConfigurationUtils;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.AuditFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterElementDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterFamilyDataService;
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
    private WebResourceFactory webResourceFactory;
    private AuditFactory auditFactory;
    private ContentDataService contentDataService;
    private AuditDataService auditDataService;
    private ParameterFamilyDataService parameterFamilyDataService;
    private ParameterElementDataService parameterElementDataService;
    private ParameterDataService parameterDataService;
    CrawlConfigurationUtils ccu = CrawlConfigurationUtils.getInstance();
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
        crawlerService = (CrawlerService) springBeanFactory.getBean("crawlerService");
        contentDataService = (ContentDataService) springBeanFactory.getBean("contentDataService");
        auditDataService = (AuditDataService) springBeanFactory.getBean("auditDataService");
        auditFactory = (AuditFactory) springBeanFactory.getBean("auditFactory");
        webResourceFactory = (WebResourceFactory) springBeanFactory.getBean("webResourceFactory");
        parameterDataService = (ParameterDataService) springBeanFactory.getBean("parameterDataService");
        parameterElementDataService = (ParameterElementDataService) springBeanFactory.getBean("parameterElementDataService");
        parameterFamilyDataService = (ParameterFamilyDataService) springBeanFactory.getBean("parameterFamilyDataService");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private List<Content> initialiseAndLaunchCrawl (
            String siteUrl,
            String crawlParam1,
            String crawlParam2,
            String crawlParam3,
            String crawlParam4) {
        Audit audit = auditFactory.create(new Date());
        audit.setParameterSet(setCrawlParameters(crawlParam1, crawlParam2, crawlParam3, crawlParam4));
        auditDataService.saveOrUpdate(audit);
        Site site = webResourceFactory.createSite(siteUrl);
        site.setAudit(audit);
        site = crawlerService.crawl(site);
        List<Long> contentListId = contentDataService.getSSPFromWebResource(site.getId(), HttpStatus.SC_OK, 0, 10);
        List<Content> contentList = new ArrayList<Content>();
        for (Long id : contentListId) {
            Content content = contentDataService.readWithRelatedContent(id, false);
            if (content != null) {
                contentList.add(content);
            }
        }
        return contentList;
    }

    public void testCrawl_SiteWithDepthLevel0Option() {
        System.out.println("crawl_full_site_With_Depth_Level0_Option");
        crawlerService.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "0", "", "", "");
        assertEquals(1, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
    }

    public void testCrawl_SiteWithDepthLevel1Option() {
        System.out.println("crawl_full_site_With_Depth_Level0_Option");
        crawlerService.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "1", "", "", "");
        assertEquals(3, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }
    
    public void testCrawl_SiteWithRegexpExclusionOption() {
        System.out.println("crawl_full_site_With_Regexp_Exclusion_Option");
        crawlerService.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "4", ".html", "", "");
        assertEquals(1, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
    }
    
    public void testCrawl_SiteWithRegexpExclusionOption2() {
        System.out.println("crawl_full_site_With_Regexp_Exclusion_Option2");
        crawlerService.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "4", "robot", "", "");
        assertEquals(3, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
    }

    public void testCrawl_SiteWithRegexpExclusionOption3() {
        System.out.println("crawl_full_site_With_Regexp_Exclusion_Option3");
        crawlerService.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "4", "robot;page-2", "", "");
        assertEquals(2, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
    }

    /**
     * * Test the crawl of a site without robots.txt file
     */
    public void testCrawl_Site() {
        System.out.println("crawl_full_site");
        crawlerService.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "3", "", "", "");
        assertEquals(4, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
        assertTrue(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }

    /**
     * Test the crawl of a page
     */
    public void testCrawl_Page() {
        System.out.println("crawl_page");
        crawlerService.setCrawlConfigFilePath(PAGE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(FULL_SITE_CRAWL_URL_KEY);
        Page page = webResourceFactory.createPage(siteUrl);
        Audit audit  = auditFactory.create();
        page.setAudit(audit);
        audit.setParameterSet(setCrawlParameters("3", "", "", ""));
        page = crawlerService.crawl(page);
        List<Long> contentListId = contentDataService.getSSPFromWebResource(page.getId(), HttpStatus.SC_OK, 0, 10);
        List<Content> contentList = new ArrayList<Content>();
        for (Long id : contentListId) {
            contentList.add(contentDataService.readWithRelatedContent(id, false));
        }
        assertEquals(1, contentList.size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertFalse(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertFalse(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
        assertFalse(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }

    /**
     * Test the crawl of a site with robots.txt file
     */
    public void testCrawl_Site_With_Robots() {
        System.out.println("crawl_site_with_robots");
        crawlerService.setCrawlConfigFilePath(FULL_SITE_CRAWL_CONF_FILE_PATH);
        String siteUrl = bundle.getString(ROBOTS_RESTRICTED_CRAWL_URL_KEY);
        List<Content> contentList = initialiseAndLaunchCrawl(siteUrl, "3", "", "", "");
        assertEquals(3, contentList.size() + ((SSP) contentList.iterator().next()).getRelatedContentSet().size());
        Set<String> urlSet = getUrlSet(contentList);
        assertTrue(urlSet.contains(siteUrl));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL1));
        assertTrue(urlSet.contains(siteUrl + PAGE_NAME_LEVEL2));
        assertFalse(urlSet.contains(siteUrl + FORBIDDEN_PAGE_NAME));
    }

    private Set<String> getUrlSet(List<Content> contentList) {
        Set<String> urlSet = new HashSet<String>();
        for (Content content : contentList) {
            urlSet.add(content.getURI());
        }
        return urlSet;
    }

    private Set<Parameter> setCrawlParameters(String depth, String regexp, String maxDuration, String maxDocuments) {
        Set<Parameter> crawlParameters = new HashSet<Parameter>();
        ParameterFamily pf = parameterFamilyDataService.create();
        pf.setParameterFamilyCode("CRAWLER");
        parameterFamilyDataService.saveOrUpdate(pf);
        //DEPTH
        ParameterElement ped = parameterElementDataService.create(pf);
        ped.setParameterElementCode("DEPTH");
        parameterElementDataService.saveOrUpdate(ped);

        Parameter pedValue= parameterDataService.getParameter(ped, depth);
        crawlParameters.add(pedValue);

        //EXCLUSION_REGEX
        ParameterElement peer = parameterElementDataService.create(pf);
        peer.setParameterElementCode("EXCLUSION_REGEX");
        parameterElementDataService.saveOrUpdate(peer);

        Parameter peerValue= parameterDataService.getParameter(peer, regexp);
        crawlParameters.add(peerValue);

        //MAX_DURATION
        ParameterElement pemdu = parameterElementDataService.create(pf);
        pemdu.setParameterElementCode("MAX_DURATION");
        parameterElementDataService.saveOrUpdate(pemdu);

        Parameter pemduValue= parameterDataService.getParameter(pemdu, maxDuration);
        crawlParameters.add(pemduValue);

        //MAX_DOCUMENTS
        ParameterElement pemdo = parameterElementDataService.create(pf);
        pemdo.setParameterElementCode("MAX_DOCUMENTS");
        parameterElementDataService.saveOrUpdate(pemdo);

        Parameter pemdoValue= parameterDataService.getParameter(pemdo, maxDocuments);
        crawlParameters.add(pemdoValue);
        parameterDataService.saveOrUpdate(crawlParameters);
        return crawlParameters;
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