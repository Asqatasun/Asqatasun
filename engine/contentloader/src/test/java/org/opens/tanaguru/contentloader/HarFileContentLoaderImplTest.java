/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.contentloader;

//import java.io.*;
//import java.net.URI;
//import java.net.URISyntaxException;
import java.util.Map;
//import org.apache.commons.pool.impl.GenericObjectPool;
//import org.apache.log4j.Logger;

/**
 *
 * @author jkowalczyk
 */
//public class HarFileContentLoaderImplTest extends TestCase {
public class HarFileContentLoaderImplTest  {
  
    private Map<String, String> contentMap;
    private static final String PAGE_URL = "http://my.test.org/";
    private static final String CSS_1_URL = "http://my.test.org/CSS/css1.css";
    private static final String CSS_2_URL = "http://my.test.org/CSS/css2.css";

    
    public HarFileContentLoaderImplTest(String testName) {
//        super(testName);
    }
    
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        contentMap = new LinkedHashMap<String, String>();
//        contentMap.put(PAGE_URL, readFile("contents/page1.html"));
//        contentMap.put(CSS_1_URL, readFile("contents/css1.css"));
//        contentMap.put(CSS_2_URL, readFile("contents/css2.css"));
//    }
//    
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }

    /**
     * Test of getResult method, of class HarFileContentLoaderImpl.
     */
//    public void testGetResult() {
//        System.out.println("getResult");
//        
//        Site mockSite = createMock(Site.class);
//        
//        Date date = new Date();
//        ContentFactory mockContentFactory = createMock(ContentFactory.class);
//        DateFactory mockDateFactory = createMock(DateFactory.class);
//        expect(mockDateFactory.createDate())
//                .andReturn(date)
//                .times(3);
//        
//        expect(mockContentFactory.createSSP(
//                date, 
//                PAGE_URL, 
//                contentMap.get(PAGE_URL), 
//                null, 
//                HttpStatus.SC_OK))
//                        .andReturn(null)
//                        .once();
//        
//        StylesheetContent mockCss1 = createMock(StylesheetContent.class);
//        expect(mockContentFactory.createStylesheetContent(
//                date, 
//                CSS_1_URL, 
//                null,
//                contentMap.get(CSS_1_URL), 
//                HttpStatus.SC_OK))
//                        .andReturn(mockCss1)
//                        .once();
//        StylesheetContent mockCss2 = createMock(StylesheetContent.class);
//        expect(mockContentFactory.createStylesheetContent(
//                date, 
//                CSS_2_URL, 
//                null,
//                contentMap.get(CSS_2_URL), 
//                HttpStatus.SC_OK))
//                        .andReturn(mockCss2)
//                        .once();
//        
//        replay(mockContentFactory);
//        replay(mockSite);
//        replay(mockDateFactory);
//        replay(mockCss1);
//        replay(mockCss2);
//        
//        HarFileContentLoaderImpl instance = new HarFileContentLoaderImpl(
//                mockContentFactory, 
//                mockDateFactory, 
//                mockSite, 
//                "src/test/resources");
//
//        instance.run();
//        List result = instance.getResult();
//        assertEquals(2, result.size());
//        verify(mockContentFactory);
//        verify(mockSite);
//        verify(mockDateFactory);
//        verify(mockCss1);
//        verify(mockCss2);
//    }

//    /**
//     * 
//     * @param scenarioPath
//     * @return 
//     */
//    private String readFile(String filePath) {
//        try {
//            java.net.URL url = GenericObjectPool.Config.class.getClassLoader().getResource(filePath);
//            BufferedReader in = new BufferedReader(new FileReader(new File(new URI(url.toString()))));
//            String str;
//            StringBuilder file = new StringBuilder();
//            int i=0;
//            try {
//                while ((str = in.readLine()) != null) {
//                    if (i>0) {
//                        file.append('\n');
//                    }
//                    file.append(str);
//                    i++;
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(HarFileContentLoaderImplTest.class.getName()).error(ex);
//            }
//            return file.toString();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(HarFileContentLoaderImplTest.class.getName()).error(ex);
//        } catch (URISyntaxException ex) {
//                Logger.getLogger(HarFileContentLoaderImplTest.class.getName()).error(ex);
//        }
//        return "";
//    }
    
}