/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.entity.service.parameterization;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import junit.framework.TestCase;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterElementImpl;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.parameterization.ParameterFamilyImpl;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDataServiceImplTest extends TestCase {

    private static final String PARAM_FAMILY = "TEST";
    private static final String PARAM_ELEMENT = "ELEMENT";

    public ParameterDataServiceImplTest(String testName) {
        super(testName);
    }

    public void testUpdateParameterSet() {
        ParameterDataService pds = new ParameterDataServiceImpl();
        Set<Parameter> paramSet = new LinkedHashSet<Parameter>();
        for (int i=0;i<5;i++) {
            paramSet.add(getParameter(PARAM_FAMILY, PARAM_ELEMENT+String.valueOf(i), String.valueOf(i)));
        }
        assertEquals(5, paramSet.size());
        Iterator<Parameter> iter=paramSet.iterator();
        int val=0;
        while (iter.hasNext()) {
            assertEquals(val, Integer.valueOf(iter.next().getValue()).intValue());
            val++;
        }
        Set<Parameter> paramToUpdate = new HashSet<Parameter>();
        for (int i=2;i<5;i++) {
            paramToUpdate.add(getParameter(PARAM_FAMILY, PARAM_ELEMENT+String.valueOf(i), String.valueOf(i+10)));
        }
        assertEquals(3, paramToUpdate.size());
        paramSet = pds.updateParameterSet(paramSet, paramToUpdate);
        for (Parameter param : paramSet) {
            int length = param.getParameterElement().getParameterElementCode().length();
            int index = Integer.valueOf(param.getParameterElement().getParameterElementCode().substring(length-1, length)).intValue();
            if (index < 2 ) {
                assertEquals(index, Integer.valueOf(param.getValue()).intValue());
            }  else {
                assertEquals(index+10, Integer.valueOf(param.getValue()).intValue());
            }
        }
        
    }

    public void testUpdateParameter() {
        ParameterDataService pds = new ParameterDataServiceImpl();
        Set<Parameter> paramSet = new LinkedHashSet<Parameter>();
        for (int i=0;i<5;i++) {
            paramSet.add(getParameter(PARAM_FAMILY, PARAM_ELEMENT+String.valueOf(i), String.valueOf(i)));
        }
        assertEquals(5, paramSet.size());
        paramSet = pds.updateParameter(paramSet, getParameter(PARAM_FAMILY, PARAM_ELEMENT+String.valueOf(3), String.valueOf(13)));
        for (Parameter param : paramSet) {
            if (param.getParameterElement().getParameterElementCode().equalsIgnoreCase(PARAM_ELEMENT+String.valueOf(3))) {
                assertEquals(13, Integer.valueOf(param.getValue()).intValue());
            }  else {
                int length = param.getParameterElement().getParameterElementCode().length();
                assertEquals(Integer.valueOf(param.getParameterElement().getParameterElementCode().substring(length-1, length)).intValue(), Integer.valueOf(param.getValue()).intValue());
            }
        }
    }

    private Parameter getParameter(String family, String element, String value) {
        ParameterFamily pf = new ParameterFamilyImpl();
        pf.setParameterFamilyCode(family);
        //ELEMENT1
        ParameterElement pe = new ParameterElementImpl();
        pe.setParameterElementCode(element);

        Parameter p = new ParameterImpl();
        p.setParameterElement(pe);
        p.setValue(value);
        return p;
    }
    
}