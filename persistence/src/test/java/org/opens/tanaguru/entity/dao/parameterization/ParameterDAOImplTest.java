/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    private static final String INPUT_DATA_SET_FILENAME = "src/main/resources/dataSets/parameterizationDataSet.xml";
    private ParameterDAO parameterDAO;
//    private ParameterFamilyDAO parameterFamilyDAO;
    private ParameterElementDAO parameterElementDAO;
    private AuditDAO auditDAO;

    public ParameterDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        parameterDAO = (ParameterDAO)
                springBeanFactory.getBean("parameterDAO");
        auditDAO = (AuditDAO)
                springBeanFactory.getBean("auditDAO");
//        parameterFamilyDAO = (ParameterFamilyDAO)
//                springBeanFactory.getBean("parameterFamilyDAO");
        parameterElementDAO = (ParameterElementDAO)
                springBeanFactory.getBean("parameterElementDAO");
    }

    public void testFindParameterSet() {
//        throw new UnsupportedOperationException("Not supported yet.");
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