/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.audit;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.dao.subject.WebResourceDAO;
import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;

/**
 *
 * @author jkowalczyk
 */
public class AuditDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "src/main/resources/dataSets/auditFlatXmlDataSet.xml";
    private WebResourceDAO webresourceDAO;
    private ContentDAO contentDAO;
    private AuditDAO auditDAO;

    public AuditDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        webresourceDAO = (WebResourceDAO)
                springBeanFactory.getBean("webresourceDAO");
        auditDAO = (AuditDAO)
                springBeanFactory.getBean("auditDAO");
        contentDAO = (ContentDAO)
                springBeanFactory.getBean("contentDAO");
    }

    public void testFindAll_AuditStatus() {
        Collection<Audit> auditList = auditDAO.findAll(AuditStatus.COMPLETED);
        assertEquals(2, auditList.size());
        auditList = auditDAO.findAll(AuditStatus.ANALYSIS);
        assertEquals(0, auditList.size());
    }

    public void testFindAuditWithWebResource() {
        Audit audit = auditDAO.findAuditWithWebResource(Long.valueOf(1));
        assertEquals(Long.valueOf(1), audit.getId());
        assertTrue(audit.getSubject() instanceof Site);
        assertEquals(Long.valueOf(1), audit.getSubject().getId());
        audit = auditDAO.findAuditWithWebResource(Long.valueOf(2));
        assertEquals(Long.valueOf(2), audit.getId());
        assertEquals(Long.valueOf(2), audit.getSubject().getId());
        assertTrue(audit.getSubject() instanceof Page);
    }

    public void testFindAuditWithTest() {
        Audit audit = auditDAO.findAuditWithTest(Long.valueOf(1));
        assertEquals(Long.valueOf(1), audit.getId());
        assertEquals(10, audit.getTestList().size());
        audit = auditDAO.findAuditWithTest(Long.valueOf(2));
        assertEquals(Long.valueOf(2), audit.getId());
        assertEquals(10, audit.getTestList().size());
    }

}