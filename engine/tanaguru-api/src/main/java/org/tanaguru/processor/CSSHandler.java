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

import com.phloc.css.decl.CascadingStyleSheet;
import java.util.Collection;
import java.util.Map;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.audit.StylesheetContent;
import org.tanaguru.service.ProcessRemarkService;

/**
 * 
 * @author jkowalczyk
 */
public interface CSSHandler {

    /**
     *
     * @return the current CSSHandler instance
     */
    CSSHandler beginSelection();

    /**
     *
     * @return a collection of ProcessRemark
     */
    Collection<ProcessRemark> getRemarkList();

    /**
     *
     * @return the selected CSS rules list
     */
    Map<String,CascadingStyleSheet> getStyleSheetMap();
    
    /**
     *
     * @return the CSS generating an error when adapting and that can't be tested
     */
    Collection<StylesheetContent> getStyleSheetOnError();
    
    /**
     *
     * @param ssp
     *            the SSP so set
     */
    void setSSP(SSP ssp);

    /**
     *
     * @param processRemarkService
     */
    void setProcessRemarkService(ProcessRemarkService processRemarkService);

}