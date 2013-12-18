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
package org.opens.tanaguru.entity.dao.audit;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.PreProcessResult;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 */
public interface PreProcessResultDAO extends GenericDAO<PreProcessResult, Long> {

    /**
     * This method returns all the pre-process result for a given resource and
     * for a given key
     * 
     * @param webresource
     * @param key
     * @return
     */
    String findPreProcessResultByKeyAndWebResource(
            String key,
            WebResource webresource
            );
    
    /**
     * This method returns all the pre-process result for a given audit and
     * for a given key
     * 
     * @param key
     * @param audit
     * @return
     */
    String findPreProcessResultByKeyAndAudit(
            String key,
            Audit audit);
    
    /**
     * This method clears all the entries for a given audit
     * 
     * @param audit
     * @return
     */
    void deleteAllPreProcessResultByAudit(Audit audit); 
    
    /**
     * This method clears all the entries for a given webResource
     * 
     * @param webresource
     * @return
     */
    void deleteAllPreProcessResultByWebResource(WebResource webResource); 
    
    /**
     * 
     * @param audit
     * @return all the PreProcessResults for a given audit
     */
    Collection<PreProcessResult> findPreProcessResultFromAudit(Audit audit);

}