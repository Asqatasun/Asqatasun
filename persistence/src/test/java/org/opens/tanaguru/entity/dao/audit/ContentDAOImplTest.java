/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.audit;

import java.util.Iterator;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.ImageContent;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.StylesheetContent;
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

    public void testFindRelatedContentFromUriWithParentContent() {
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        RelatedContent relatedContent = contentDAO.findRelatedContentFromUriWithParentContent(wr, "http://www.open-s.com/css.css");
        assertTrue(relatedContent instanceof StylesheetContent);
        assertEquals(Long.valueOf(3), ((Content)relatedContent).getId());
        assertEquals(2,relatedContent.getParentContentSet().size());
        wr = webresourceDAO.read(Long.valueOf(2));
        relatedContent = contentDAO.findRelatedContentFromUriWithParentContent(wr, "http://www.open-s.com/css.css");
        assertNotNull(relatedContent);
        assertEquals(Long.valueOf(8), ((Content)relatedContent).getId());
        assertEquals(1,relatedContent.getParentContentSet().size());
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

    public void testFindRelatedContent() {
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        RelatedContent relatedContent = contentDAO.findRelatedContent(wr, "http://www.open-s.com/css.css");
        assertTrue(relatedContent instanceof StylesheetContent);
        assertEquals(Long.valueOf(3), ((Content)relatedContent).getId());
        relatedContent = contentDAO.findRelatedContent(wr, "http://www.open-s.com/image.jpg");
        assertTrue(relatedContent instanceof ImageContent);
        assertEquals(Long.valueOf(4), ((Content)relatedContent).getId());
        relatedContent = contentDAO.findRelatedContent(wr, "http://www.open-s.com/unknown");
        assertEquals(Long.valueOf(5), ((Content)relatedContent).getId());
        wr = webresourceDAO.read(Long.valueOf(2));
        relatedContent = contentDAO.findRelatedContentFromUriWithParentContent(wr, "http://www.open-s.com/css.css");
        assertNotNull(relatedContent);
        assertEquals(Long.valueOf(8), ((Content)relatedContent).getId());
    }

    public void testFindContentWithRelatedContentFromWebResource() {
        WebResource wr = webresourceDAO.read(Long.valueOf(8));
        List<Content> contentList = contentDAO.findContentWithRelatedContentFromWebResource(wr, 0, 1);
        assertEquals(4, contentList.size()+((SSP)contentList.iterator().next()).getRelatedContentSet().size());
        contentList = contentDAO.findContentWithRelatedContentFromWebResource(wr, 1, 1);
        assertEquals(3, contentList.size()+((SSP)contentList.iterator().next()).getRelatedContentSet().size());
    }

    public void testFindNumberOfSSPFromWebResource() {
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPFromWebResource(wr));
        wr = webresourceDAO.read(Long.valueOf(2));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPFromWebResource(wr));
        wr = webresourceDAO.read(Long.valueOf(8));
        assertEquals(Long.valueOf(2), contentDAO.findNumberOfSSPFromWebResource(wr));
        wr = webresourceDAO.read(Long.valueOf(12));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPFromWebResource(wr));
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

    public void testFindNumberOfSSPContentFromAudit(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPContentFromAudit(audit));
        audit = auditDAO.read(Long.valueOf(2));
        assertEquals(Long.valueOf(0), contentDAO.findNumberOfSSPContentFromAudit(audit));
        audit = auditDAO.read(Long.valueOf(3));
        assertEquals(Long.valueOf(2), contentDAO.findNumberOfSSPContentFromAudit(audit));
    }

    public void testFindSSPContentWithRelatedContent(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        List<? extends Content> contentList = contentDAO.findSSPContentWithRelatedContent(audit, 0, 100);
        assertEquals(0, contentList.size());
        audit = auditDAO.read(Long.valueOf(2));
        contentList = contentDAO.findSSPContentWithRelatedContent(audit, 0, 100);
        assertEquals(0, contentList.size());
        audit = auditDAO.read(Long.valueOf(3));
        contentList = contentDAO.findSSPContentWithRelatedContent(audit, 0, 100);
        Iterator iter = contentList.iterator();
        iter = contentList.iterator();
        assertEquals(7, contentList.size() +
                ((SSP)iter.next()).getRelatedContentSet().size() +
                ((SSP)iter.next()).getRelatedContentSet().size());
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

    public void testFindSSPList(){
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(0),contentDAO.findNumberOfSSPFromWebResource(wr));
        assertEquals(0, contentDAO.findSSPList(wr, 0, 10).size());
        wr = webresourceDAO.read(Long.valueOf(8));
        assertEquals(Long.valueOf(2),contentDAO.findNumberOfSSPFromWebResource(wr));
        assertEquals(2, contentDAO.findSSPList(wr, 0, 10).size());
    }

    public void testFindRelatedContentList(){
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(0),contentDAO.findNumberOfRelatedContentFromWebResource(wr));
        assertEquals(0, contentDAO.findRelatedContentList(wr, 0, 10).size());
        wr = webresourceDAO.read(Long.valueOf(8));
        assertEquals(Long.valueOf(3),contentDAO.findNumberOfRelatedContentFromWebResource(wr));
        assertEquals(3, contentDAO.findRelatedContentList(wr, 0, 10).size());
    }

}