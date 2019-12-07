/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.dao.audit;

import java.util.Collection;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.dao.reference.ScopeDAO;
import org.asqatasun.entity.dao.reference.ThemeDAO;
import org.asqatasun.entity.dao.subject.WebResourceDAO;
import org.asqatasun.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.entity.reference.Scope;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.subject.WebResource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ProcessResultDAOImplTest extends AbstractDaoTestCase {
    
    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "processRemarkFlatXmlDataSet.xml";
    @Autowired
    private ProcessResultDAO processResultDAO;
    @Autowired
    private ThemeDAO themeDAO;
    @Autowired
    private WebResourceDAO webresourceDAO;
    @Autowired
    private ScopeDAO scopeDAO;
    @Autowired
    private AuditDAO auditDAO;

    @Override
    protected String getDataSetFilename() throws Exception {
        return getInputDataFilePath()+INPUT_DATA_SET_FILENAME;
    }

    public ProcessResultDAOImplTest() {
        super();
    }

    @Test
    public void testGetResultByThemeCount() {
        Theme theme1 = themeDAO.read(Long.valueOf(1));
        Theme theme2 = themeDAO.read(Long.valueOf(2));
        WebResource wa = webresourceDAO.read(Long.valueOf(1));
        assertEquals(1, processResultDAO.getResultByThemeCount
                (wa, TestSolution.PASSED, theme1));
        assertEquals(0, processResultDAO.getResultByThemeCount
                (wa, TestSolution.FAILED, theme1));
        assertEquals(1, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NEED_MORE_INFO, theme1));
        assertEquals(0, processResultDAO.getResultByThemeCount
                (wa, TestSolution.NOT_APPLICABLE, theme1));
        assertEquals(1, processResultDAO.getResultByThemeCount
                (wa, TestSolution.PASSED, theme2));
        assertEquals(2, processResultDAO.getResultByThemeCount
                (wa, TestSolution.FAILED, theme2));
        assertEquals(1, processResultDAO.getResultByThemeCount
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

    @Test
    public void testGetResultByScopeList() {
        Scope scope1 = scopeDAO.read(Long.valueOf(1));
        Scope scope2 = scopeDAO.read(Long.valueOf(2));
        WebResource wa = webresourceDAO.read(Long.valueOf(1));
        assertEquals(1, processResultDAO.getResultByScopeList(wa, scope2).size());
        assertEquals(5, processResultDAO.getResultByScopeList(wa, scope1).size());
    }

    @Test
    public void testRetrieveNumberOfGrossResultFromAudit(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(10), processResultDAO.retrieveNumberOfGrossResultFromAudit(audit));
        audit = auditDAO.read(Long.valueOf(2));
        assertEquals(Long.valueOf(2), processResultDAO.retrieveNumberOfGrossResultFromAudit(audit));
    }

    @Test
    public void testRetrieveNumberOfNetResultFromAudit(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(10), processResultDAO.retrieveNumberOfNetResultFromAudit(audit));
        audit = auditDAO.read(Long.valueOf(2));
        assertEquals(Long.valueOf(2), processResultDAO.retrieveNumberOfNetResultFromAudit(audit));
    }

    @Test
    public void testRetrieveNetResultFromAudit(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        Collection<ProcessResult> processResultList =
                processResultDAO.retrieveNetResultFromAudit(audit);
        assertEquals(10, processResultList.size());
        assertEquals(Long.valueOf(1), ((ProcessResult)processResultList.iterator().next()).getSubject().getId());
        audit = auditDAO.read(Long.valueOf(2));
        processResultList =
                processResultDAO.retrieveNetResultFromAudit(audit);
        assertEquals(2, processResultList.size());
        assertEquals(Long.valueOf(2), ((ProcessResult)processResultList.iterator().next()).getSubject().getId());
    }

    @Test
    public void testRetrieveGrossResultFromAudit(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        Collection<ProcessResult> processResultList =
                processResultDAO.retrieveGrossResultFromAudit(audit);
        assertEquals(10, processResultList.size());
        assertEquals(Long.valueOf(1), ((ProcessResult)processResultList.iterator().next()).getSubject().getId());
        audit = auditDAO.read(Long.valueOf(2));
        processResultList =
                processResultDAO.retrieveGrossResultFromAudit(audit);
        assertEquals(2, processResultList.size());
        assertEquals(Long.valueOf(2), ((ProcessResult)processResultList.iterator().next()).getSubject().getId());
    }

    @Test
    public void testRetrieveGrossResultFromAuditAndWebResource(){
        Audit audit = auditDAO.read(Long.valueOf(1));
        WebResource wr = webresourceDAO.read(Long.valueOf(1));
        Collection<ProcessResult> processResultList =
                processResultDAO.retrieveNetResultFromAuditAndWebResource(audit, wr);
        assertEquals(6, processResultList.size());
        wr = webresourceDAO.read(Long.valueOf(2));
        processResultList =
                processResultDAO.retrieveNetResultFromAuditAndWebResource(audit, wr);
        assertEquals(0, processResultList.size());
    }

}