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
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class AuditDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "auditFlatXmlDataSet.xml";

    @Autowired
    private AuditDAO auditDAO;

    @Override
    protected String getDataSetFilename() throws Exception {
        return getInputDataFilePath()+INPUT_DATA_SET_FILENAME;
    }

    public AuditDAOImplTest() {
        super();
    }

    @Test
    public void testFindAll_AuditStatus() {
        Collection<Audit> auditList = auditDAO.findAll(AuditStatus.COMPLETED);
        assertEquals(2, auditList.size());
        auditList = auditDAO.findAll(AuditStatus.ANALYSIS);
        assertEquals(0, auditList.size());
    }

    @Test
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

    @Test
    public void testFindAuditWithTest() {
        Audit audit = auditDAO.findAuditWithTest(Long.valueOf(1));
        assertEquals(Long.valueOf(1), audit.getId());
        assertEquals(10, audit.getTestList().size());
        audit = auditDAO.findAuditWithTest(Long.valueOf(2));
        assertEquals(Long.valueOf(2), audit.getId());
        assertEquals(10, audit.getTestList().size());
    }

}