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
package org.opens.tgol.entity.decorator.tanaguru.reference;

import java.util.List;
import java.util.Set;

import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tgol.entity.dao.tanaguru.reference.TgolTestDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class TestDataServiceDecoratorImpl extends AbstractGenericDataService<Test, Long>
        implements TestDataServiceDecorator{

    private TestDataService decoratedTestDataService; // the TestDAO instance being decorated

    @Autowired
    public TestDataServiceDecoratorImpl (TestDataService decoratedTestDataService) {
        this.decoratedTestDataService = decoratedTestDataService;
    }

    @Override
    public List<Test> findAll(Reference reference) {
        return decoratedTestDataService.findAll(reference);
    }

    @Override
    public List<Test> findAllByCode(String[] codeArray) {
        return decoratedTestDataService.findAllByCode(codeArray);
    }

    @Override
    public List<Test> findAllTestByLevel(Level level) {
        return ((TgolTestDAO)entityDao).retrieveAllByLevel(level);
    }

    @Override
    public List<Test> getAllByReferenceAndLevel(Reference reference, Level level) {
        return decoratedTestDataService.getAllByReferenceAndLevel(reference, level);
    }

    @Override
    public List<Test> getTestListFromParamSet(Set<Parameter> paramSet) {
        return decoratedTestDataService.getTestListFromParamSet(paramSet);
    }

    @Override
    public List<Test> findAllByCriterion(Criterion criterion) {
        return decoratedTestDataService.findAllByCriterion(criterion);
    }

	@Override
	public Test read(String label) {
		return decoratedTestDataService.read(label);
	}

}