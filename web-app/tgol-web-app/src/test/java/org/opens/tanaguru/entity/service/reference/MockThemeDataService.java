/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
package org.opens.tanaguru.entity.service.reference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.CriterionImpl;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.ReferenceImpl;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.reference.ThemeImpl;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class MockThemeDataService implements ThemeDataService{

    @Override
    public Theme create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Theme entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Theme entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<Theme> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Theme> findAll() {
        Reference ref=  new ReferenceImpl();
        ref.setId(Long.valueOf(1));
        ref.setCode("Reference1");
        Criterion criterion = new CriterionImpl();
        criterion.setId(Long.valueOf(1));
        criterion.setCode("Criterion1");
        criterion.setReference(ref);
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(criterion);
        Theme theme = new ThemeImpl();
        theme.setId(Long.valueOf(1));
        theme.setCode("Theme1");
        theme.setCriterionList(criterionList);
        List<Theme> themeList = new ArrayList<Theme>();
        themeList.add(theme);
        return themeList;
    }

    @Override
    public Theme read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Theme saveOrUpdate(Theme entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Theme> saveOrUpdate(Set<Theme> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Theme, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Theme> factory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Theme update(Theme entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
