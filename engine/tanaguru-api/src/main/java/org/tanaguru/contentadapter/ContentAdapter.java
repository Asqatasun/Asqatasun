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
package org.tanaguru.contentadapter;

import java.util.List;
import org.tanaguru.contentadapter.util.URLIdentifier;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.audit.SSP;

/**
 * 
 * @author jkowalczyk
 */
public interface ContentAdapter {

    /**
     *
     * @return
     *      the list of content
     */
    List<Content> getContentList();

    /**
     * 
     * @return
     *      the current ssp
     */
    SSP getSSP();

    /**
     * 
     * @param parser
     */
    void setParser(ContentParser parser);

    /**
     *
     * @param urlIdentifier
     */
    void setURLIdentifier(URLIdentifier urlIdentifier);

    /**
     * 
     * @param ssp
     */
    void setSSP(SSP ssp);

}