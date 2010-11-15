/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.dao.reference.ScopeDAO;
import org.opens.tanaguru.entity.dao.reference.ThemeDAO;
import org.opens.tanaguru.entity.dao.subject.WebResourceDAO;
import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 *
 * @author jkowalczyk
 */
public class ProcessResultDAOImplTest extends AbstractDaoTestCase {
    
    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/main/resources/dataSets/processRemarkFlatXmlDataSet.xml";
    private ProcessResultDAO processResultDAO;
    private ThemeDAO themeDAO;
    private WebResourceDAO webresourceDAO;
    private ScopeDAO scopeDAO;

    public ProcessResultDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        processResultDAO = (ProcessResultDAO)
                springBeanFactory.getBean("processResultDAO");
        themeDAO = (ThemeDAO)
                springBeanFactory.getBean("themeDAO");
        webresourceDAO = (WebResourceDAO)
                springBeanFactory.getBean("webresourceDAO");
        scopeDAO = (ScopeDAO)
                springBeanFactory.getBean("scopeDAO");
    }

    public void testGetResultByThemeCount() {
        Theme theme1 = themeDAO.read(Long.valueOf(1));
        Theme theme2 = themeDAO.read(Long.valueOf(2));
        WebResource wa = webresourceDAO.read(Long.valueOf(1));
        assertEquals(0, processResultDAO.getResultByThemeCount
                (wa, TestSolution.PASSED, theme1));
        assertEquals(1, processResultDAO.getResultByThemeCount
                (wa, TestSolution.FAILED, theme1));
        assertEquals(2, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NEED_MORE_INFO, theme1));
        assertEquals(0, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NOT_APPLICABLE, theme1));
        assertEquals(2, processResultDAO.getResultByThemeCount
                (wa, TestSolution.PASSED, theme2));
        assertEquals(1, processResultDAO.getResultByThemeCount
                (wa, TestSolution.FAILED, theme2));
        assertEquals(0, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NEED_MORE_INFO, theme2));
        assertEquals(0, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NOT_APPLICABLE, theme2));
        assertEquals(2, processResultDAO.getResultByThemeCount
                (wa, TestSolution.PASSED, null));
        assertEquals(2, processResultDAO.getResultByThemeCount
                (wa, TestSolution.FAILED, null));
        assertEquals(2, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NEED_MORE_INFO, null));
        assertEquals(0, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NOT_APPLICABLE, null));
    }

    public void testGetResultByScopeList() {
        Scope scope1 = scopeDAO.read(Long.valueOf(1));
        Scope scope2 = scopeDAO.read(Long.valueOf(2));
        WebResource wa = webresourceDAO.read(Long.valueOf(1));
        assertEquals(3, processResultDAO.getResultByScopeList(wa, scope2).size());
        assertEquals(3, processResultDAO.getResultByScopeList(wa, scope1).size());
    }

}
