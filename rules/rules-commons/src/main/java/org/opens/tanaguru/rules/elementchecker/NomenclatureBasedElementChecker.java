/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.service.NomenclatureLoaderService;

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
     * @param successSolution
     * @param failureSolution 
     */
    public NomenclatureBasedElementChecker(TestSolution successSolution, 
            TestSolution failureSolution) {
        super(successSolution, failureSolution);
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
     * @param successSolution
     * @param failureSolution
     * @param eeAttributeNameList 
     */
    public NomenclatureBasedElementChecker(TestSolution successSolution, 
            TestSolution failureSolution, String... eeAttributeNameList) {
        super(successSolution, failureSolution,eeAttributeNameList);
    }
    
}