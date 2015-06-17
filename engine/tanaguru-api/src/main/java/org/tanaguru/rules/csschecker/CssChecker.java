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
package org.tanaguru.rules.csschecker;

import com.phloc.css.decl.CascadingStyleSheet;
import java.util.Collection;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.service.NomenclatureLoaderService;

/**
 * This interface combines selections and checks to be done on css rules.
 * 
 * @author jkowalczyk
 */
public interface CssChecker {
    
    /**
     * Set the media type list 
     * 
     * @param mediaList 
     */
    void setMediaList(Collection<String> mediaList);
    
    /**
     * 
     * @param nomenclatureLoaderService
     */
    void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService);
    
    /**
     * 
     * @return 
     */
    NomenclatureLoaderService getNomenclatureLoaderService();
    
    /**
     * 
     * @return the number of css rules parsed by the checker
     */
    int getCssRulesCounter();
    
    
    /**
     * Perform the check operation. The instance of {@link CascadingStyleSheet} 
     * received as a parameter is used to retrieve css rules the test is about
     * and the instance of {@link TestSolutionHandler} received 
     * as a parameter is used to store the results of tests performed 
     * during the operation
     * 
     * @param sspHandler
     * @param styleSheetName
     * @param cascadingStyleSheet
     * @param testSolutionHandler
     *  
     */
    void check (
            SSPHandler sspHandler, 
            String styleSheetName,
            CascadingStyleSheet cascadingStyleSheet, 
            TestSolutionHandler testSolutionHandler);

}
