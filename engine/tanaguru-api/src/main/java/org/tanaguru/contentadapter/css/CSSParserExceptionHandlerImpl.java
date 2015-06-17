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
package org.tanaguru.contentadapter.css;

import com.phloc.css.handler.ICSSParseExceptionHandler;
import com.phloc.css.parser.ParseException;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.StylesheetContent;

/**
 *
 * Class that handles exceptions encountered while parse the css. 
 * The adapted content is set to "ON ERROR" and extra info are added (line 
 * and column position where the problem occured.
 * 
 * @author jkowalczyk
 */
public class CSSParserExceptionHandlerImpl implements ICSSParseExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(CSSParserExceptionHandlerImpl.class);
    private StylesheetContent css;

    public CSSParserExceptionHandlerImpl(StylesheetContent css) {
        this.css = css;
    }

    @Override
    public void onException(ParseException extype) {
        int line = extype.currentToken.next.beginLine;
        int column = extype.currentToken.next.beginColumn;
        this.css.setAdaptedContent(CSSContentAdapter.CSS_ON_ERROR + 'l' + line + 'c' + column);
        LOGGER.warn("Error on adaptation " + css.getURI() + " " + css.getSource());
        LOGGER.warn(extype.currentToken.getValue());
        LOGGER.warn(extype.getMessage());
    }

}