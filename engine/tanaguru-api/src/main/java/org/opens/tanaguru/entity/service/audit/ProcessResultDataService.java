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
package org.opens.tanaguru.entity.service.audit;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessResultDataService extends
		GenericDataService<ProcessResult, Long> {

    /**
     * This method returns the number of elements for a given resource,
     * testSolution and theme
     * @param webresource
     * @param testSolution
     * @param theme
     * @return
     */
    int getResultByThemeCount(
            WebResource webresource,
            TestSolution testSolution,
            Theme theme);

    /**
     * This method returns all the process result for a given resource and
     * for a given scope (Site or Page)
     * @param webresource
     * @param scope
     * @return
     */
    Collection<ProcessResult> getResultByScopeList(
            WebResource webresource,
            Scope scope);

    /**
     * 
     * @param audit
     * @return
     */
    Long getNumberOfGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Long getNumberOfNetResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Collection<ProcessResult> getGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Collection<ProcessResult> getGrossResultFromAuditAndTest(Audit audit, Test test);

    /**
     *
     * @param audit
     * @return
     */
    Collection<ProcessResult> getNetResultFromAudit(Audit audit);

    /**
     * 
     * @param audit
     * @param webResource
     * @return
     */
    Collection<ProcessResult> getNetResultFromAuditAndWebResource(Audit audit, WebResource webResource);
    
    /**
     * 
     * @param audit
     * @param webResource
     * @return
     */
    void cleanUpIndefiniteResultFromAudit(Audit audit);
    
    /**
     * 
     * @param audit
     * @return the list of IndefiniteProcessResult for a given audit
     */
    Collection<ProcessResult> getIndefiniteResultFromAudit(Audit audit);

}