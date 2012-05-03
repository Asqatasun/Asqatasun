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
package org.opens.tanaguru.entity.service.parameterization;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterElementImpl;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 * Parameter elements bank for test. All the parameters defined
 * 
 * @author jkowalczyk
 */
public class MockParameterElementDataService implements ParameterElementDataService {

    Map<String, ParameterElement> paramElemMap = new HashMap<String, ParameterElement>();

    public MockParameterElementDataService(){
        
        ParameterElement numericalFormFieldParameterElement = new ParameterElementImpl();
        numericalFormFieldParameterElement.setParameterElementCode("NUMERICAL_FORMFIELD");
        paramElemMap.put("NUMERICAL_FORMFIELD", numericalFormFieldParameterElement);
        ParameterElement textualFormFieldParameterElement = new ParameterElementImpl();
        textualFormFieldParameterElement.setParameterElementCode("TEXTUAL_FORMFIELD");
        paramElemMap.put("TEXTUAL_FORMFIELD", textualFormFieldParameterElement);
        ParameterElement levelParameterElement = new ParameterElementImpl();
        levelParameterElement.setParameterElementCode("LEVEL");
        paramElemMap.put("LEVEL", levelParameterElement);

    }

    @Override
    public ParameterElement create(ParameterFamily parameterFamily) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParameterElement getParameterElement(String parameterElementCode) {
        return paramElemMap.get(parameterElementCode);
    }

    @Override
    public ParameterElement create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(ParameterElement e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(ParameterElement e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<ParameterElement> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends ParameterElement> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParameterElement read(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParameterElement saveOrUpdate(ParameterElement e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<ParameterElement> saveOrUpdate(Set<ParameterElement> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<ParameterElement, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<ParameterElement> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParameterElement update(ParameterElement e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}