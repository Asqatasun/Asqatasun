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
package org.tanaguru.processing;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author jkowalczyk
 */
public class ProcessRemarkServiceImplTest extends TestCase {
    
    public ProcessRemarkServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of setDocument method, of class ProcessRemarkServiceImpl.
     */
    public void testGetSnippetFromElement() {
        ProcessRemarkServiceImpl instance = new ProcessRemarkServiceImpl(null, null, null);
        
        //--------------------------------------------------------------------//
        //-----------------------Test1----------------------------------------//
        //--------------------------------------------------------------------//
        String rawHtml = "<label> <span>Rechercher:</span> "
                + "<input type=\"text\" onkeyup=\"return CatchEnter(event);\" "
                + "class=\"text\" id=\"searchfield\" "
                + "name=\"search&qudsqqqssqdsqdsqdo\" /></label>";
        Document document = Jsoup.parse(rawHtml);
        Element element = document.getElementsByTag("label").iterator().next();
        String snippet = 
                StringEscapeUtils.unescapeHtml4(instance.getSnippetFromElement(element));
        String expectedSnippet = "<label> <span>Rechercher:</span> "
                + "<input type=\"text\" onkeyup=\"return CatchEnter(event);\" "
                + "class=\"text\" id=\"searchfield\" "
                + "name=\"search&amp;qudsqqqssqdsqdsqdo\" />[...]</label>";
        assertEquals(expectedSnippet, snippet);
        
        //--------------------------------------------------------------------//
        //-----------------------Test2----------------------------------------//
        //--------------------------------------------------------------------//
        rawHtml = "<label> <span>New Rechercher:</span> "
                + "<p title=\"some title here\" onkeyup=\"return CatchEnter(event);\" "
                + " id=\"searchfield\" class=\"myclass other-class1 other-class2\" > "
                + "anything</p></label>";
        document = Jsoup.parse(rawHtml);
        element = document.getElementsByTag("label").iterator().next();
        snippet = 
                StringEscapeUtils.unescapeHtml4(instance.getSnippetFromElement(element));
        expectedSnippet = "<label> <span>New Rechercher:</span> "
                + "<p title=\"some title here\" onkeyup=\"return CatchEnter(event);\""
                + " id=\"searchfield\" class=\"myclass other-class1 other-class2\">"
                + "[...]</p>[...]</label>";
        assertEquals(expectedSnippet, snippet);
        
        //--------------------------------------------------------------------//
        //-----------------------Test3----------------------------------------//
        //--------------------------------------------------------------------//
        rawHtml = "<iframe align=\"left\" width=\"315px\" "
                + "scrolling=\"no\" height=\"160px\" frameborder=\"0\" "
                + "id=\"link-meteo\" src=\"http://www.anyUrl.com/module/onelocationsearch?ShowSearch=true&amp;StartDate=2012-06-01&amp;Days=2&amp;location=bruxelles&amp;url=http://meteo1.lavenir.net&amp;cssfile=http://lavenir.net/extra/weather/styles.css\">"
                + "</iframe> ";
        document = Jsoup.parse(rawHtml);
        element = document.getElementsByTag("iframe").iterator().next();
        snippet = 
                StringEscapeUtils.unescapeHtml4(instance.getSnippetFromElement(element));
        expectedSnippet = rawHtml.trim();
        assertEquals(expectedSnippet, snippet);
        
        //--------------------------------------------------------------------//
        //-----------------------Test4----------------------------------------//
        //--------------------------------------------------------------------//
        rawHtml = " <center>  <script type=\"text/javascript\">    if (articledetail == false) initAdhese('IMU.SUPER.WIDE');     </script> "
                 + "<script src=\"http://anyUrl.com/ad3/sl_ave_home_-IMU.SUPER.WIDE/lafr/rn92/pv1/brFirefox;Firefox17;Linux;screenundefined/in;prx;;gmbl;/?t=1381234838205\" type=\"text/javascript\"></script> "
                 + " <div class=\"adhese_300x250\">  <script src=\"http://1.adhesecdn.be/pool/lib/68641.js?t=1371729603000\"></script> "
                 + "<script src=\"http://anyUrl.com/pagead/show_ads.js\" type=\"text/javascript\"></script>"
                 + "<ins style=\"display:inline-table;border:none;height:250px;margin:0;padding:0;position:relative;visibility:visible;width:300px\">"
                 + "<ins style=\"display:block;border:none;height:250px;margin:0;padding:0;position:relative;visibility:visible;width:300px\" id=\"aswift_1_anchor\">"
                 + "<iframe width=\"300\" scrolling=\"no\" height=\"250\" frameborder=\"0\" style=\"left:0;position:absolute;top:0;\" name=\"aswift_1\" id=\"aswift_1\" onload=\"var i=this.id,s=window.google_iframe_oncopy,H=s&amp;&amp;s.handlers,h=H&amp;&amp;H[i],w=this.contentWindow,d;try{d=w.document}catch(e){}if(h&amp;&amp;d&amp;&amp;(!d.body||!d.body.firstChild)){if(h.call){setTimeout(h,0)}else if(h.match){w.location.replace(h)}}\" allowtransparency=\"true\" hspace=\"0\" vspace=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>"
                 + "</ins>"
                 + "</ins>"
                 + "</div> "
                 +"</center> ";
        document = Jsoup.parse(rawHtml);
        element = document.getElementsByTag("center").iterator().next();
        snippet = 
                StringEscapeUtils.unescapeHtml4(instance.getSnippetFromElement(element));
        expectedSnippet = "<center> <script type=\"text/javascript\"> if (articledetail == false) initAdhese('IMU.SUPER.WIDE'); </script> "
                 + "<script src=\"http://anyUrl.com/ad3/sl_ave_home_-IMU.SUPER.WIDE/lafr/rn92/pv1/brFirefox;Firefox17;Linux;screenundefined/in;prx;;gmbl;/?t=1381234838205\" type=\"text/javascript\">[...]</script>"
                 + "[...]</center>";
        assertEquals(expectedSnippet, snippet);
    }
    
}