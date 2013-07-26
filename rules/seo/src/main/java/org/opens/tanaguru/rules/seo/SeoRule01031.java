/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
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
package org.opens.tanaguru.rules.seo;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.seo.length.AbstractElementLengthControlPageRuleImplementation;

/**
 * Test whether the Url of the page exceeds 255 characters
 * 
 * @author jkowalczyk
 */
public class SeoRule01031 extends AbstractElementLengthControlPageRuleImplementation {

    public static final String MESSAGE_CODE = "UrlLengthExceedLimit";
    public static final int URL_MAX_LENGTH = 255;

    public SeoRule01031() {
        super();
        setMessageCode(MESSAGE_CODE);
        setLength(URL_MAX_LENGTH);
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        setElement(sspHandler.getSSP().getURI());
        return super.processImpl(sspHandler);
    }

}
