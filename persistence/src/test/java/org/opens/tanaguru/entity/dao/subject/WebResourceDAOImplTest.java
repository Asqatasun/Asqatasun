/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.subject;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.dao.audit.AuditDAO;
import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;

/**
 *
 * @author jkowalczyk
 */
public class WebResourceDAOImplTest extends AbstractDaoTestCase {

    private static final String URL1="http://www.open-s.com/";
    private static final String URL2="http://www.mock-url.com/";

    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/main/resources/dataSets/webresourceFlatXmlDataSet.xml";
    private WebResourceDAO webresourceDAO;
    private AuditDAO auditDAO;

    public WebResourceDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        auditDAO = (AuditDAO)
                springBeanFactory.getBean("auditDAO");
        webresourceDAO = (WebResourceDAO)
                springBeanFactory.getBean("webresourceDAO");
    }

    public void testFindByAuditAndUrl() {
        Audit audit1 = auditDAO.read(Long.valueOf(1));
        Audit audit2 = auditDAO.read(Long.valueOf(2));
        Audit audit3 = auditDAO.read(Long.valueOf(3));
        assertEquals(Long.valueOf(1), webresourceDAO.findByAuditAndUrl(audit1, URL1).getId());
        assertEquals(Long.valueOf(2), webresourceDAO.findByAuditAndUrl(audit2, URL1).getId());
        assertEquals(Long.valueOf(3), webresourceDAO.findByAuditAndUrl(audit3, URL2).getId());
        assertNull(webresourceDAO.findByAuditAndUrl(audit1, URL2));
        assertNull(webresourceDAO.findByAuditAndUrl(audit2, URL2));
        assertNull(webresourceDAO.findByAuditAndUrl(audit3, URL1));
    }

}
