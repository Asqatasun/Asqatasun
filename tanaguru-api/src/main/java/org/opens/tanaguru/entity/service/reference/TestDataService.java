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

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Level;

/**
 * 
 * @author jkowalczyk
 */
public interface TestDataService extends GenericDataService<Test, Long> {

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
     * @param codeReference
     * @param codeLevel
     * @return
     */
    List<Test> getAllByReferenceAndLevel(Reference reference, Level level);

    /**
     *
     * @param paramSet
     * @return
     */
    List<Test> getTestListFromParamSet(Set<Parameter> paramSet);

}