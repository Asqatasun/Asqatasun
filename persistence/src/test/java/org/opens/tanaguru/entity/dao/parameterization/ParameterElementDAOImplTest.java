/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.dao.parameterization;

import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;

/**
 *
 * @author jkowalczyk
 */
public class ParameterElementDAOImplTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "src/main/resources/dataSets/parameterizationDataSet.xml";
    private ParameterElementDAO parameterElementDAO;

    public ParameterElementDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        parameterElementDAO = (ParameterElementDAO)
                springBeanFactory.getBean("parameterElementDAO");
    }

    public void testFindParameterElementFromCode() {
        assertEquals(Long.valueOf(1),parameterElementDAO.findParameterElementFromCode("PARAM_EL_1").getId());
        assertEquals(Long.valueOf(2),parameterElementDAO.findParameterElementFromCode("PARAM_EL_2").getId());
        assertNull(parameterElementDAO.findParameterElementFromCode("PARAM_EL_3"));
    }

}