/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
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

package org.opens.tanaguru.contentadapter.util;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This voter decides whether an action is executable regarding the current 
 * audit.
 * 
 * @author jkowalczyk
 */
public class AdaptationActionVoterImpl implements AdaptationActionVoter {

    private static final String LEVEL_PARAMETER_KEY = "LEVEL";
    
    @Autowired
    private ParameterDataService parameterDataService;
    @Override
    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }

    private Collection<String> authorizedValues;
    @Override
    public void setAuthorizedValues(Collection<String> authorizedValues) {
        this.authorizedValues = authorizedValues;
    }
    
    @Override
    public boolean doesExecute(Audit audit) {
        Logger.getLogger(this.getClass()).debug("doesExecute "+getClass() + " for audit n° " +audit.getId());
        Parameter level = parameterDataService.getParameter(audit, LEVEL_PARAMETER_KEY);
        Logger.getLogger(this.getClass()).debug("Level " + level.getValue());
        return authorizedValues.contains(extractReferentialFromParameter(level));
    }
    
    /**
     * Extract the referential from the LEVEL parameter
     * 
     * @param paramSet
     * @return 
     */
    private String extractReferentialFromParameter(Parameter level) {
        if (level != null & level.getParameterElement().
                getParameterElementCode().equals(LEVEL_PARAMETER_KEY)) {
            return level.getValue().split(";")[0];
        }
        return "";
    }

}