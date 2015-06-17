/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.scenarioloader;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.apache.http.HttpStatus;
import static org.easymock.EasyMock.*;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.sebuilder.tools.ProfileFactory;
import org.tanaguru.util.factory.DateFactory;

/**
 *
 * @author jkowalczyk
 */
public class ScenarioLoaderImplTest extends TestCase {
    
    private Map<String, String> pageMap;
    private static final String ROOT_PAGE_URL = "http://site.tgqa.org/";
    private static final String PAGE_1_URL = "http://site.tgqa.org/page-1.html";
    private static final String PAGE_2_URL = "http://site.tgqa.org/page-2.html";
    private static final String PAGE_ACCESS_FORBIDDEN_URL = "http://site.tgqa.org/page-access-forbidden-for-robots.html";
    
    public ScenarioLoaderImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        pageMap = new LinkedHashMap<>();
        pageMap.put(ROOT_PAGE_URL, readFile("htmlFiles/root-page.html", true));
        pageMap.put(PAGE_1_URL, readFile("htmlFiles/page-1.html", true));
        pageMap.put(PAGE_ACCESS_FORBIDDEN_URL, readFile("htmlFiles/page-access-forbidden.html", true));
        pageMap.put(PAGE_2_URL, readFile("htmlFiles/page-2.html", true));
        
        // need to initilise properly the ProfileFactory needed by sebuilder
        ProfileFactory.getInstance().setNetExportPath("/tmp/");
        
//        List<String> extensionList = new ArrayList<>();
//        extensionList.add("../tanaguru-resources/src/main/resources/firefox/extensions/firebug@software.joehewitt.com.xpi");
//        extensionList.add("../tanaguru-resources/src/main/resources/firefox/extensions/firestarter@getfirebug.com.xpi");
//        extensionList.add("../tanaguru-resources/src/main/resources/firefox/extensions/netexport@getfirebug.com.xpi");
//        ProfileFactory.getInstance().setExtensionPathList(extensionList);
//        ProfileFactory.getInstance().setFirebugVersion("1.9.2");
        ProfileFactory.getInstance().setDeleteProfileData(true);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * For this test, we use the pages hosted at http://site.tgqa.org/.
     * The sequence is : 
     *      - get the page "http://site.tgqa.org/."
     *      - click on "This page won't be crawled due to the robots.txt restrictrion" link 
     *      - get the page "http://site.tgqa.org/page-access-forbidden-for-robots.html"
     *      - click on "Back" button
     *      - get the page "http://site.tgqa.org/."
     *      - click on "This page will be crawled" link  
     *      - get the page "http://site.tgqa.org/page-1.html"
     *      - click on "This page won't be crawled due to the robots.txt restrictrion" link 
     *      - get the page "http://site.tgqa.org/page-access-forbidden-for-robots.html"
     *      - click on "Back" button
     *      - get the page "http://site.tgqa.org/page-1.html"
     *      - click on "This page will be crawled" link
     *      - get the page "http://site.tgqa.org/page-2.html"
     *      - click on "This page won't be crawled due to the robots.txt restrictrion" link 
     *      - get the page "http://site.tgqa.org/page-access-forbidden-for-robots.html"
     * 
     * For each retrieved page, a webResource is created and added to the 
     * parent WebResource and a SSP is created that handles the source code of 
     * the page.
     * 
     * In this test, we don't mock the HarFileContentLoaderFactory and the ProfileFactory
     * 
     * This test is a Functionnal test
     * 
     * Test of run method, of class ScenarioLoaderImpl.
     */
    public void testRun() {
        System.out.println("run");
        
        Date date = new Date();
        DateFactory mockDateFactory = createMock(DateFactory.class);
        expect(mockDateFactory.createDate())
                .andReturn(date)
                .times(8);
        ContentDataService mockContentDataService = createMock(ContentDataService.class);
        WebResourceDataService mockWebResourceDataService = createMock(WebResourceDataService.class);

        Page mockPage1 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(ROOT_PAGE_URL))
                .andReturn(mockPage1)
                .once();
//        expect(mockPage1.getURL())
//                .andReturn(ROOT_PAGE_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage1))
                .andReturn(mockPage1)
                .once();
        
        Page mockPage2 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(PAGE_ACCESS_FORBIDDEN_URL))
                .andReturn(mockPage2)
                .once();
//        expect(mockPage2.getURL())
//                .andReturn(PAGE_ACCESS_FORBIDDEN_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage2))
                .andReturn(mockPage2)
                .once();

        Page mockPage3 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(ROOT_PAGE_URL))
                .andReturn(mockPage3)
                .once();
//        expect(mockPage3.getURL())
//                .andReturn(ROOT_PAGE_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage3))
                .andReturn(mockPage3)
                .once();

        Page mockPage4 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(PAGE_1_URL))
                .andReturn(mockPage4)
                .once();
//        expect(mockPage4.getURL())
//                .andReturn(PAGE_1_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage4))
                .andReturn(mockPage4)
                .once();

        Page mockPage5 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(PAGE_ACCESS_FORBIDDEN_URL))
                .andReturn(mockPage5)
                .once();
//        expect(mockPage5.getURL())
//                .andReturn(PAGE_ACCESS_FORBIDDEN_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage5))
                .andReturn(mockPage5)
                .once();
        
        Page mockPage6 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(PAGE_1_URL))
                .andReturn(mockPage6)
                .once();
//        expect(mockPage6.getURL())
//                .andReturn(PAGE_1_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage6))
                .andReturn(mockPage6)
                .once();
        
        Page mockPage7 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(PAGE_2_URL))
                .andReturn(mockPage7)
                .once();
//        expect(mockPage7.getURL())
//                .andReturn(PAGE_2_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage7))
                .andReturn(mockPage7)
                .once();
        
        Page mockPage8 = createMock(Page.class); 
        expect(mockWebResourceDataService.createPage(PAGE_ACCESS_FORBIDDEN_URL))
                .andReturn(mockPage8)
                .once();
//        expect(mockPage8.getURL())
//                .andReturn(PAGE_ACCESS_FORBIDDEN_URL)
//                .once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage8))
                .andReturn(mockPage8)
                .once();

        SSP ssp1 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                ROOT_PAGE_URL, 
                pageMap.get(ROOT_PAGE_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp1)
                    .once();
        expect(ssp1.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp1.getURI())
                    .andReturn(ROOT_PAGE_URL)
                    .once();
        ssp1.setDOM(readFile("htmlFiles/root-page_1.html", false));
        ssp1.setPage(mockPage1);
        expect(mockContentDataService.saveOrUpdate(ssp1))
                .andReturn(ssp1)
                .once();

        SSP ssp2 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                PAGE_ACCESS_FORBIDDEN_URL, 
                pageMap.get(PAGE_ACCESS_FORBIDDEN_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp2)
                    .once();
        expect(ssp2.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp2.getURI())
                    .andReturn(PAGE_ACCESS_FORBIDDEN_URL)
                    .once();
        ssp2.setDOM(readFile("htmlFiles/page-access-forbidden_1.html", false));
        ssp2.setPage(mockPage2);
        expect(mockContentDataService.saveOrUpdate(ssp2))
                .andReturn(ssp2)
                .once();
        
        SSP ssp3 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                ROOT_PAGE_URL, 
                pageMap.get(ROOT_PAGE_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp3)
                    .once();
        expect(ssp3.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp3.getURI())
                    .andReturn(ROOT_PAGE_URL)
                    .once();
        ssp3.setDOM(readFile("htmlFiles/root-page_1.html", false));
        ssp3.setPage(mockPage3);
        expect(mockContentDataService.saveOrUpdate(ssp3))
                .andReturn(ssp3)
                .once();

        SSP ssp4 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                PAGE_1_URL, 
                pageMap.get(PAGE_1_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp4)
                    .once();
        expect(ssp4.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp4.getURI())
                    .andReturn(PAGE_1_URL)
                    .once();
        ssp4.setDOM(readFile("htmlFiles/page-1_1.html", false));
        ssp4.setPage(mockPage4);
        expect(mockContentDataService.saveOrUpdate(ssp4))
                .andReturn(ssp4)
                .once();

        SSP ssp5 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                PAGE_ACCESS_FORBIDDEN_URL, 
                pageMap.get(PAGE_ACCESS_FORBIDDEN_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp5)
                    .once();
        expect(ssp5.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp5.getURI())
                    .andReturn(PAGE_ACCESS_FORBIDDEN_URL)
                    .once();
        ssp5.setDOM(readFile("htmlFiles/page-access-forbidden_1.html", false));
        ssp5.setPage(mockPage5);
        expect(mockContentDataService.saveOrUpdate(ssp5))
                .andReturn(ssp5)
                .once();

        SSP ssp6 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                PAGE_1_URL, 
                pageMap.get(PAGE_1_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp6)
                    .once();
        expect(ssp6.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp6.getURI())
                    .andReturn(PAGE_1_URL)
                    .once();
        ssp6.setDOM(readFile("htmlFiles/page-1_1.html", false));
        ssp6.setPage(mockPage6);
        expect(mockContentDataService.saveOrUpdate(ssp6))
                .andReturn(ssp6)
                .once();

        SSP ssp7 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                PAGE_2_URL, 
                pageMap.get(PAGE_2_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp7)
                    .once();
        expect(ssp7.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp7.getURI())
                    .andReturn(PAGE_2_URL)
                    .once();
        ssp7.setDOM(readFile("htmlFiles/page-2_1.html", false));
        ssp7.setPage(mockPage7);
        expect(mockContentDataService.saveOrUpdate(ssp7))
                .andReturn(ssp7)
                .once();

        SSP ssp8 = createMock(SSP.class);
        expect(mockContentDataService.getSSP(
                date, 
                PAGE_ACCESS_FORBIDDEN_URL, 
                pageMap.get(PAGE_ACCESS_FORBIDDEN_URL), 
                null, 
                HttpStatus.SC_OK))
                    .andReturn(ssp8)
                    .once();
        expect(ssp8.getHttpStatusCode())
                    .andReturn(HttpStatus.SC_OK)
                    .once();
        expect(ssp8.getURI())
                    .andReturn(PAGE_ACCESS_FORBIDDEN_URL)
                    .once();
        ssp8.setDOM(readFile("htmlFiles/page-access-forbidden_1.html", false));
        ssp8.setPage(mockPage8);
        expect(mockContentDataService.saveOrUpdate(ssp8))
                .andReturn(ssp8)
                .once();
                
        Site mockSite = createMock(Site.class); 
//        expect(mockSite.getLabel())
//                .andReturn("My Test Label")
//                .once();
        
        mockSite.addChild(mockPage1);
        expectLastCall().once();
        mockSite.addChild(mockPage2);
        expectLastCall().once();
        mockSite.addChild(mockPage3);
        expectLastCall().once();
        mockSite.addChild(mockPage4);
        expectLastCall().once();
        mockSite.addChild(mockPage5);
        expectLastCall().once();
        mockSite.addChild(mockPage6);
        expectLastCall().once();
        mockSite.addChild(mockPage7);
        expectLastCall().once();
        mockSite.addChild(mockPage8);
        expectLastCall().once();

        expect(mockSite.getURL())
                .andReturn(ROOT_PAGE_URL)
                .once();
                
//        expect(mockWebResourceDataService.saveOrUpdate(mockSite))
//                .andReturn(mockSite)
//                .times(8);
        
        replay(mockPage1);
        replay(mockPage2);
        replay(mockPage3);
        replay(mockPage4);
        replay(mockPage5);
        replay(mockPage6);
        replay(mockPage7);
        replay(mockPage8);
        replay(mockSite);
        replay(mockWebResourceDataService);
        replay(mockDateFactory);
        replay(mockContentDataService);
        replay(ssp1);
        replay(ssp2);
        replay(ssp3);
        replay(ssp4);
        replay(ssp5);
        replay(ssp6);
        replay(ssp7);
        replay(ssp8);
        
//        ScenarioLoaderImpl instance = new ScenarioLoaderImpl(
//                mockSite,
//                readFile("MyTest.json", true), 
//                harFileContentLoaderFactory);
//        instance.setContentDataService(mockContentDataService);
//        instance.setContentFactory(mockContentFactory);
//        instance.setDateFactory(mockDateFactory);
//        instance.setWebResourceDataService(mockWebResourceDataService);
//        
//        instance.run(); 
        
        verify(mockPage1);
        verify(mockPage2);
        verify(mockPage3);
        verify(mockPage4);
        verify(mockPage5);
        verify(mockPage6);
        verify(mockPage7);
        verify(mockPage8);
        verify(mockSite);
        verify(mockWebResourceDataService);
        verify(mockDateFactory);
        verify(mockContentDataService);
        verify(ssp1);
        verify(ssp2);
        verify(ssp3);
        verify(ssp4);
        verify(ssp5);
        verify(ssp6);
        verify(ssp7);
        verify(ssp8);
    }

    /**
     * 
     * @param scenarioPath
     * @return 
     */
    private String readFile(String filePath, boolean addLastReturn) {
        try {
            java.net.URL url = Config.class.getClassLoader().getResource(filePath);
            BufferedReader in = new BufferedReader(new FileReader(new File(new URI(url.toString()))));
            String str;
            StringBuilder file = new StringBuilder();
            int i=0;
            try {
                while ((str = in.readLine()) != null) {
                    if (i>0) {
                        file.append('\n');
                    }
                    file.append(str);
                    i++;
                }
                if (addLastReturn) {
                    file.append('\n');
                }
            } catch (IOException ex) {
                Logger.getLogger(ScenarioLoaderImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return file.toString();
        } catch (FileNotFoundException | URISyntaxException ex) {
            Logger.getLogger(ScenarioLoaderImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}