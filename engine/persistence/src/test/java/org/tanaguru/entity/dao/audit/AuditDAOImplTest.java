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
import org.dbunit.operation.DatabaseOperation;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;

/**
 *
 * @author jkowalczyk
 */
public class AuditDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "auditFlatXmlDataSet.xml";

    private final AuditDAO auditDAO;

    public AuditDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(getInputDataFilePath()+INPUT_DATA_SET_FILENAME);
        auditDAO = (AuditDAO)
                springBeanFactory.getBean("auditDAO");
        if (!testName.equalsIgnoreCase("testFindAuditWithTest")) {
            setTeardownOperationValue(DatabaseOperation.DELETE);
        }
    }

    public void testFindAll_AuditStatus() {
        Collection<Audit> auditList = auditDAO.findAll(AuditStatus.COMPLETED);
        assertEquals(2, auditList.size());
        auditList = auditDAO.findAll(AuditStatus.ANALYSIS);
        assertEquals(0, auditList.size());
    }

    public void testRead() {
        Audit audit = auditDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(1), audit.getId());
        assertTrue(audit.getSubject() instanceof Site);
        assertEquals(Long.valueOf(1), audit.getSubject().getId());
        audit = auditDAO.read(Long.valueOf(2));
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