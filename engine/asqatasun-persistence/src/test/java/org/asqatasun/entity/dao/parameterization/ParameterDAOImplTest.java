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
package org.asqatasun.entity.dao.parameterization;

import java.util.Set;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.dao.audit.AuditDAO;
import org.asqatasun.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "parameterizationDataSet.xml";
    @Autowired
    private ParameterDAO parameterDAO;
    @Autowired
    private ParameterElementDAO parameterElementDAO;
    @Autowired
    private AuditDAO auditDAO;

    @Override
    protected String getDataSetFilename() throws Exception {
        return getInputDataFilePath()+INPUT_DATA_SET_FILENAME;
    }

    public ParameterDAOImplTest() {
        super();
    }

    @Test
    public void testFindDefaultParameterSet() {
        Set<Parameter> paramSet = parameterDAO.findDefaultParameterSet();
        assertEquals(2, paramSet.size());
        // the two default elements are the one with value equals to "Value1"
        // and "Value5"
        for (Parameter param : paramSet) {
            if (param.getValue().equals("Value1") || param.getValue().equals("Value5")) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
        }
    }

    @Test
    public void testFindParameterSetFromAudit() {
        Audit audit = auditDAO.read(Long.valueOf(1));
        Set<Parameter> paramSet = parameterDAO.findParameterSetFromAudit(audit);
        assertEquals(Integer.valueOf(2), Integer.valueOf(paramSet.size()));

        audit = auditDAO.read(Long.valueOf(2));
        paramSet = parameterDAO.findParameterSetFromAudit(audit);
        assertEquals(Integer.valueOf(2), Integer.valueOf(paramSet.size()));

        audit = auditDAO.read(Long.valueOf(3));
        paramSet = parameterDAO.findParameterSetFromAudit(audit);
        assertEquals(Integer.valueOf(2), Integer.valueOf(paramSet.size()));

        audit = auditDAO.read(Long.valueOf(4));
        paramSet = parameterDAO.findParameterSetFromAudit(audit);
        assertEquals(Integer.valueOf(2), Integer.valueOf(paramSet.size()));

        audit = auditDAO.read(Long.valueOf(5));
        paramSet = parameterDAO.findParameterSetFromAudit(audit);
        assertEquals(Integer.valueOf(0), Integer.valueOf(paramSet.size()));
    }

    @Test
    public void testFindParameter() {
        ParameterElement paramElement = parameterElementDAO.read(Long.valueOf(1));
        Parameter param = parameterDAO.findParameter(paramElement, "0");
        assertNull(param);
        for (int i=1;i<5;i++) {
            param = parameterDAO.findParameter(paramElement, "Value"+i);
            assertEquals(i, param.getId().intValue());
        }
        paramElement = parameterElementDAO.read(Long.valueOf(2));
        param = parameterDAO.findParameter(paramElement, "4");
        assertNull(param);
        for (int i=5;i<9;i++) {
            param = parameterDAO.findParameter(paramElement, "Value"+i);
            assertEquals(i, param.getId().intValue());
        }
    }

    @Test
    public void testFindDefaultParameter() {
        ParameterElement paramElement = parameterElementDAO.read(Long.valueOf(1));
        Parameter param = parameterDAO.findDefaultParameter(paramElement);
        assertEquals(Long.valueOf(1), param.getId());
        paramElement = parameterElementDAO.read(Long.valueOf(2));
        param = parameterDAO.findDefaultParameter(paramElement);
        assertEquals(Long.valueOf(5), param.getId());
    }

}