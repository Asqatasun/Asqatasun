/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.audit;

import java.util.List;
import org.apache.http.HttpStatus;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.dao.subject.WebResourceDAO;
import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 *
 * @author jkowalczyk
 */
public class ContentDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "src/main/resources/dataSets/contentFlatXmlDataSet.xml";
    private WebResourceDAO webresourceDAO;
    private ContentDAO contentDAO;
    private AuditDAO auditDAO;

    public ContentDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        webresourceDAO = (WebResourceDAO)
                springBeanFactory.getBean("webresourceDAO");
        auditDAO = (AuditDAO)
                springBeanFactory.getBean("auditDAO");
        contentDAO = (ContentDAO)
                springBeanFactory.getBean("contentDAO");
    }

    public void testFindOrphanContentList() {
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(2), contentDAO.findNumberOfOrphanContentFromWebResource(wr));
        List<Content> contentList = contentDAO.findOrphanContentList(wr,0, 100);
        assertEquals(2, contentList.size());
        assertEquals(Long.valueOf(3), contentDAO.findNumberOfOrphanRelatedContentFromWebResource(wr));
        contentList = contentDAO.findOrphanRelatedContentList(wr,0, 10);
        assertEquals(3, contentList.size());
        wr = webresourceDAO.read(Long.valueOf(2));
        contentList = contentDAO.findOrphanContentList(wr, 0, 10);
        assertEquals(1, contentList.size());
        contentList = contentDAO.findOrphanRelatedContentList(wr, 0, 10);
        assertEquals(1, contentList.size());
    }

    public void testFindNumberOfSSPFromWebResource() {
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPFromWebResource(wr, HttpStatus.SC_OK));
        wr = webresourceDAO.read(Long.valueOf(2));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPFromWebResource(wr, HttpStatus.SC_OK));
        wr = webresourceDAO.read(Long.valueOf(8));
        assertEquals(Long.valueOf(1), contentDAO.findNumberOfSSPFromWebResource(wr,HttpStatus.SC_OK));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPFromWebResource(wr,HttpStatus.SC_BAD_GATEWAY));
        assertEquals(Long.valueOf(2), contentDAO.findNumberOfSSPFromWebResource(wr,-1));
        wr = webresourceDAO.read(Long.valueOf(12));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPFromWebResource(wr,HttpStatus.SC_OK));
    }

    public void testHasContent(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        assertFalse(contentDAO.hasContent(audit));
        audit = auditDAO.read(Long.valueOf(2));
        assertFalse(contentDAO.hasContent(audit));
        audit = auditDAO.read(Long.valueOf(3));
        assertTrue(contentDAO.hasContent(audit));
    }

    public void testHasAdaptedSSP(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        assertFalse(contentDAO.hasAdaptedSSP(audit));
        audit = auditDAO.read(Long.valueOf(2));
        assertFalse(contentDAO.hasAdaptedSSP(audit));
        audit = auditDAO.read(Long.valueOf(3));
        assertTrue(contentDAO.hasAdaptedSSP(audit));
    }

    public void testFind() {
        Audit audit = auditDAO.read(Long.valueOf(1));
        assertNull(contentDAO.find(audit, "http://www.open-s.com/2.html"));
        assertNull(contentDAO.find(audit, "http://www.open-s.com/3.html"));
        audit = auditDAO.read(Long.valueOf(3));
        assertEquals(Long.valueOf(10), contentDAO.find(audit, "http://www.open-s.com/2.html").getId());
        assertNull(contentDAO.find(audit, "http://www.open-s.com/3.html"));
        WebResource wr = webresourceDAO.read(Long.valueOf(6));
        assertEquals(Long.valueOf(2), contentDAO.find(wr, "http://www.open-s.com/2.html").getId());
        wr = webresourceDAO.read(Long.valueOf(5));
        assertNull(contentDAO.find(wr, "http://www.open-s.com/2.html"));
    }

}