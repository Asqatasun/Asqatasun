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
package org.opens.tanaguru.processor;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;

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
     * @param processRemarkFactory
     *            the ProcessRemarkFactory to set
     */
    void setProcessRemarkFactory(
            ProcessRemarkFactory processRemarkFactory);

    /**
     *
     * @param remarkList
     *            the remarkList to set
     */
    void setRemarkList(Collection<ProcessRemark> remarkList);

    /**
     *
     * @param sourceCodeRemarkFactory
     *            the SourceCodeRemarkFactory to set
     */
    void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory);

    /**
     *
     * @param ssp
     *            the SSP to set
     */
    void setSSP(SSP ssp);

}