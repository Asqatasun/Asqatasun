/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.elementchecker;

import org.apache.commons.lang3.tuple.Pair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.service.NomenclatureLoaderService;

/**
 * This abstract enables to check elements regarding a nomenclature. The 
 * nomenclature is loaded by the {@link NomenclatureLoaderService}.
 * 
 * {@link NomenclatureLoaderService}
 * 
 */
public abstract class NomenclatureBasedElementChecker extends ElementCheckerImpl {

    /**
     * The locale ref to the nomenclatureLoaderService
     */
    private NomenclatureLoaderService nomenclatureLoaderService;
    public NomenclatureLoaderService getNomenclatureLoaderService() {
        return nomenclatureLoaderService;
    }
    
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }
    
    /**
     * Constructor
     */
    public NomenclatureBasedElementChecker() {
        super();
    }
    
    /**
     * Constructor
     * @param eeAttributeNameList 
     */
    public NomenclatureBasedElementChecker(
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
    }

    /**
     * Constructor
     * @param successSolutionPair
     * @param failureSolutionPair
     * @param eeAttributeNameList 
     */
    public NomenclatureBasedElementChecker(
             Pair<TestSolution, String> successSolutionPair, 
            Pair<TestSolution, String> failureSolutionPair,
            String... eeAttributeNameList) {
        super(successSolutionPair, failureSolutionPair, eeAttributeNameList);
    }
    
}