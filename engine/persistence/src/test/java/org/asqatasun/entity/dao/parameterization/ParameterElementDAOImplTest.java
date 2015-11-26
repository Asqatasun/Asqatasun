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

import org.asqatasun.entity.dao.test.AbstractDaoTestCase;

/**
 *
 * @author jkowalczyk
 */
public class ParameterElementDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "parameterizationDataSet.xml";
    private ParameterElementDAO parameterElementDAO;

    public ParameterElementDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(getInputDataFilePath()+INPUT_DATA_SET_FILENAME);
        parameterElementDAO = (ParameterElementDAO)
                springBeanFactory.getBean("parameterElementDAO");
    }

    public void testFindParameterElementFromCode() {
        assertEquals(Long.valueOf(1),parameterElementDAO.findParameterElementFromCode("PARAM_EL_1").getId());
        assertEquals(Long.valueOf(2),parameterElementDAO.findParameterElementFromCode("PARAM_EL_2").getId());
        assertNull(parameterElementDAO.findParameterElementFromCode("PARAM_EL_3"));
    }

}