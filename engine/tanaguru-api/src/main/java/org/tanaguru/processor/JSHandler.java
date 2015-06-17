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
package org.tanaguru.processor;

import java.util.Collection;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface JSHandler {

    /**
     *
     * @return the current JSHandler
     */
    JSHandler beginSelection();

    /**
     *
     * @return a collection of ProcessRemark
     */
    Collection<ProcessRemark> getRemarkList();

    /**
     *
     * @return a SSP
     */
    SSP getSSP();

    /**
     *
     * @return the current JSHandler with all the JavaScript content
     */
    JSHandler selectAllJS();

    /**
     *
     * @return the current JSHandler with the external JavaScript
     */
    JSHandler selectExternalJS();

    /**
     *
     * @return the current JSHandler with the Inline JavaScript
     */
    JSHandler selectInlineJS();

    /**
     *
     * @return the current JavaScript with the local JavaScript
     */
    JSHandler selectLocalJS();

    /**
     *
     * @param processRemarkDataService
     *            the ProcessRemarkDataService to set
     */
    void setProcessRemarkDataService(
            ProcessRemarkDataService processRemarkDataService);

    /**
     *
     * @param remarkList
     *            the remarkList to set
     */
    void setRemarkList(Collection<ProcessRemark> remarkList);

    /**
     *
     * @param ssp
     *            the SSP to set
     */
    void setSSP(SSP ssp);

}