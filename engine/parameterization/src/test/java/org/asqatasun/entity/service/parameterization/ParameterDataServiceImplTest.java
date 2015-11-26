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
package org.asqatasun.entity.service.parameterization;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import junit.framework.TestCase;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterElementImpl;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.parameterization.ParameterFamilyImpl;
import org.asqatasun.entity.parameterization.ParameterImpl;

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