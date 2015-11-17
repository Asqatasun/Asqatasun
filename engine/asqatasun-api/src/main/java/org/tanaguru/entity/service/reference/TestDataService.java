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
package org.asqatasun.entity.service.reference;

import java.util.List;
import java.util.Set;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.reference.Criterion;
import org.asqatasun.entity.reference.Level;
import org.asqatasun.entity.reference.Reference;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface TestDataService extends GenericDataService<Test, Long> {
	
	/**
    *
    * @param label
    *            the label of the test to read
    * @return the matching read test
    */
	Test read(String label);
	
    /**
     *
     * @param reference
     *            the reference of the tests to find
     * @return the tests found
     */
    List<Test> findAll(Reference reference);

    /**
     *
     * @param codeArray
     * @return
     */
    List<Test> findAllByCode(String[] codeArray);
    
    /**
     * 
     * @param criterion
     * @return 
     *      all the tests for the given criterion
     */
    List<Test> findAllByCriterion(Criterion criterion);

    /**
     *
     * @param reference
     * @param level
     * @return
     */
    List<Test> getAllByReferenceAndLevel(Reference reference, Level level);

    /**
     *
     * @param paramSet
     * @return
     */
    List<Test> getTestListFromParamSet(Set<Parameter> paramSet);

    /**
     * 
     * @param audit
     * @param testLabel
     * @return the test
     */
    Test getTestFromAuditAndLabel(Audit audit, String testLabel);
}