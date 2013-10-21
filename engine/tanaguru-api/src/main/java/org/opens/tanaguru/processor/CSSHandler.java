/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.processor;

import com.phloc.css.decl.CascadingStyleSheet;
import java.util.Collection;
import java.util.Map;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.service.ProcessRemarkService;

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