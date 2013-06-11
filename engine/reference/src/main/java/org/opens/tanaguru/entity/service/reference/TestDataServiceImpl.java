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
package org.opens.tanaguru.entity.service.reference;

import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.dao.reference.TestDAO;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public class TestDataServiceImpl extends AbstractGenericDataService<Test, Long>
        implements TestDataService {

    private static final Logger LOGGER = Logger.getLogger(TestDataServiceImpl.class);

    private String levelParameterCode = "LEVEL";
    public void setLevelParameterCode(String levelParameterCode) {
        this.levelParameterCode = levelParameterCode;
    }

    private LevelDataService levelDataService;
    public void setLevelDataService(LevelDataService levelDataService) {
        this.levelDataService = levelDataService;
    }

    private ReferenceDataService referenceDataService;
    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }
    
    public TestDataServiceImpl() {
        super();
    }

    @Override
    public List<Test> findAll(Reference reference) {
        return ((TestDAO) entityDao).retrieveAll(reference);
    }
    
    @Override
    public List<Test> findAllByCriterion(Criterion criterion) {
        return ((TestDAO) entityDao).retrieveAllByCriterion(criterion);
    }

    @Override
    public List<Test> findAllByCode(String[] codeArray) {
        return ((TestDAO) entityDao).retrieveAllByCode(codeArray);
    }

    @Override
    public List<Test> getAllByReferenceAndLevel(Reference reference, Level level) {
        return ((TestDAO) entityDao).retrieveAllByReferenceAndLevel(reference, level);
    }

    /**
     * The parameter set contains a unique parameter that combines the referential
     * and the level parameters.
     * This method extracts these parameters to retrieve the appropriate tests.
     *
     * @param paramSet
     * @return
     */
    @Override
    public List<Test> getTestListFromParamSet(Set<Parameter> paramSet) {
        Reference reference = null;
        Level level = null;
        for (Parameter param : paramSet) {
            String paramElemcode = param.getParameterElement().getParameterElementCode();
            if (paramElemcode.equals(levelParameterCode)) {
                String[] referentialAndLevel = param.getValue().split(";");
                reference = referenceDataService.getByCode(referentialAndLevel[0]);
                level = levelDataService.getByCode(referentialAndLevel[1]);
                break;
            }
        }
        List<Test> testList = getAllByReferenceAndLevel(reference, level);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieved " + testList.size() + " test for the referential "
                + reference.getLabel() + " and the level " + level.getLabel());
        }
        return testList;
    }
    
}