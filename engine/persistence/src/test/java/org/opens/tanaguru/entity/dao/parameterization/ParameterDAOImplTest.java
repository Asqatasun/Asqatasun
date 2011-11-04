/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.dao.parameterization;

import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.dao.audit.AuditDAO;
import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "parameterizationDataSet.xml";
    private ParameterDAO parameterDAO;
    private ParameterElementDAO parameterElementDAO;
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
    }

    public void testFindParameterSet() {
//        ParameterFamily paramFam = parameterFamilyDAO.read(Long.valueOf(1));
//        Audit audit;
//        for (int i=0;i<5;i++) {
//            audit = auditDAO.read(Long.valueOf(i+1));
//            Set<Parameter> paramSet = parameterDAO.findParameterSet(paramFam, audit);
//            assertEquals(1, paramSet.size());
//            Parameter param = paramSet.iterator().next();
//            assertEquals(Integer.valueOf(param.getId().intValue()), Integer.valueOf(param.getValue()));
//        }
//        paramFam = parameterFamilyDAO.read(Long.valueOf(2));
//        for (int i=0;i<5;i++) {
//            audit = auditDAO.read(Long.valueOf(i+1));
//            Set<Parameter> paramSet = parameterDAO.findParameterSet(paramFam, audit);
//            assertEquals(1, paramSet.size());
//            Parameter param = paramSet.iterator().next();
//            int value=Integer.valueOf(param.getValue()).intValue()+4;
//            assertEquals(Integer.valueOf(param.getId().intValue()), Integer.valueOf(value));
//        }
    }

    public void testFindDefaultParameterSet() {
        Set<Parameter> paramSet = parameterDAO.findDefaultParameterSet();
        assertEquals(2, paramSet.size());
        for (Parameter param : paramSet) {
            assertEquals(Integer.valueOf(param.getId().intValue()), Integer.valueOf(param.getValue()));
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
            param = parameterDAO.findParameter(paramElement, String.valueOf(i));
            assertEquals(Integer.valueOf(param.getId().intValue()), Integer.valueOf(param.getValue()));
        }
        paramElement = parameterElementDAO.read(Long.valueOf(2));
        param = parameterDAO.findParameter(paramElement, "4");
        assertNull(param);
        for (int i=5;i<9;i++) {
            param = parameterDAO.findParameter(paramElement, String.valueOf(i));
            assertEquals(Integer.valueOf(param.getId().intValue()), Integer.valueOf(param.getValue()));
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