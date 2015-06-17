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

package org.tanaguru.contentadapter.util;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.service.parameterization.ParameterDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This voter decides whether an action is executable regarding the current 
 * audit.
 * 
 * @author jkowalczyk
 */
public class AdaptationActionVoterImpl implements AdaptationActionVoter {

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
        return authorizedValues.contains(parameterDataService.getReferentialKeyFromAudit(audit));
    }
    
}