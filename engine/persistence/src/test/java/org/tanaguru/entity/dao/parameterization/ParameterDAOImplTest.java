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

/**
 *
 * @author jkowalczyk
 */
public class ParameterDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "parameterizationDataSet.xml";
    private ParameterDAO parameterDAO;
    private ParameterElementDAO parameterElementDAO;
    private ParameterFamilyDAO parameterFamilyDAO;
    private AuditDAO auditDAO;

    public ParameterDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(getInputDataFilePath()+INPUT_DATA_SET_FILENAME);
        parameterDAO = (ParameterDAO)
                springBeanFactory.getBean("parameterDAO");
        auditDAO = (AuditDAO)
                springBeanFactory.getBean("auditDAO");
        parameterElementDAO = (ParameterElementDAO)
                springBeanFactory.getBean("parameterElementDAO");
        parameterFamilyDAO = (ParameterFamilyDAO)
                springBeanFactory.getBean("parameterFamilyDAO");
    }

    public void testFindParameterSet() {
        // TO DO : implement the findParameterSet from parameterFamily and Audit
//        ParameterFamily paramFam = parameterFamilyDAO.read(Long.valueOf(1));
//        
//        Audit audit = auditDAO.read(Long.valueOf(1));
//        Set<Parameter> paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        Parameter param = paramSet.iterator().next();
//        assertEquals("Value1", param.getValue());
//        
//        audit = auditDAO.read(Long.valueOf(2));
//        paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        param = paramSet.iterator().next();
//        assertEquals("Value2", param.getValue());
//        
//        audit = auditDAO.read(Long.valueOf(3));
//        paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        param = paramSet.iterator().next();
//        assertEquals("Value3", param.getValue());
//        
//        audit = auditDAO.read(Long.valueOf(4));
//        paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        param = paramSet.iterator().next();
//        assertEquals("Value4", param.getValue());
//        
//        paramFam = parameterFamilyDAO.read(Long.valueOf(2));
//        
//        audit = auditDAO.read(Long.valueOf(5));
//        paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        param = paramSet.iterator().next();
//        assertEquals("Value5", param.getValue());
//        
//        audit = auditDAO.read(Long.valueOf(6));
//        paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        param = paramSet.iterator().next();
//        assertEquals("Value6", param.getValue());
//        
//        audit = auditDAO.read(Long.valueOf(7));
//        paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        param = paramSet.iterator().next();
//        assertEquals("Value7", param.getValue());
//        
//        audit = auditDAO.read(Long.valueOf(8));
//        paramSet = parameterDAO.findParameterSet(paramFam, audit);
//        assertEquals(1, paramSet.size());
//        param = paramSet.iterator().next();
//        assertEquals("Value8", param.getValue());
    }

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

    public void testFindDefaultParameter() {
        ParameterElement paramElement = parameterElementDAO.read(Long.valueOf(1));
        Parameter param = parameterDAO.findDefaultParameter(paramElement);
        assertEquals(Long.valueOf(1), param.getId());
        paramElement = parameterElementDAO.read(Long.valueOf(2));
        param = parameterDAO.findDefaultParameter(paramElement);
        assertEquals(Long.valueOf(5), param.getId());
    }

}