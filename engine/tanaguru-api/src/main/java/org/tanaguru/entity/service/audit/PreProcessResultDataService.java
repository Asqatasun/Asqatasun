/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.entity.service.audit;

import java.util.Collection;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.PreProcessResult;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface PreProcessResultDataService extends
		GenericDataService<PreProcessResult, Long> {

     /**
     * This method returns all the pre-process result for a given resource and
     * for a given key
     * 
     * @param key
     * @param webresource
     * @return
     */
    String getPreProcessResultByKeyAndWebResource(
            String key, 
            WebResource webresource);
    
    /**
     * This method returns all the pre-process result for a given audit and
     * for a given key
     * 
     * @param key
     * @param audit
     * @return
     */
    String getPreProcessResultByKeyAndAudit(
            String key,
            Audit audit);
    
    /**
     * 
     * @param audit
     * @return all the PreProcessResults for a given audit
     */
    Collection<PreProcessResult> getPreProcessResultFromAudit(Audit audit); 
    
    /**
     * This method clears all the entries for a given audit
     * 
     * @param audit
     */
    void cleanUpAllPreProcessResultByAudit(Audit audit); 

    /**
     * This method clears all the entries for a given webResource
     * 
     * @param webResource
     */
    void cleanUpAllPreProcessResultByWebResource(WebResource webResource); 

    /**
     * 
     * @param key
     * @param value
     * @param audit
     * @param webResource
     * @return 
     */
    PreProcessResult getPreProcessResult(String key, String value, Audit audit, WebResource webResource);
}