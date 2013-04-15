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
package org.opens.tanaguru.contentadapter.css;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.util.AbstractContentParser;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Parser;
import org.w3c.flute.parser.TokenMgrError;

/**
 * 
 * @author jkowalczyk
 */
public class CSSParserImpl extends AbstractContentParser implements CSSParser {

    private CSSOMStyleSheet result;
    /**
     * The parser (needed to be injected by spring)// TODO Update javadoc
     */
    private Parser parser = new org.w3c.flute.parser.Parser();

    public CSSParserImpl() {
        super();
    }

    /**
     *
     */
    @Override
    public void run() {
        try {
            result = null;
            String rsrc = getResource().getResource();
            InputSource is = new InputSource(new StringReader(rsrc));

            CSSOMDocumentHandlerImpl handler = new CSSOMDocumentHandlerImpl(
                    (CSSResource) getResource());

            parser.setDocumentHandler(handler);
            parser.parseStyleSheet(is);
            result = handler.getResult();
        } catch (CSSException ex) {
            Logger.getLogger(this.getClass()).warn(ex  + " on " + getResource().getRsrcName());
        } catch (IOException ex) {
            Logger.getLogger(this.getClass()).warn(ex  + " on " + getResource().getRsrcName());
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(this.getClass()).warn(ex  + " on " + getResource().getRsrcName());
        } catch (TokenMgrError err) {
            Logger.getLogger(this.getClass()).warn(err  + " on " + getResource().getRsrcName());
        }
    }

    /**
     * Based on the CSSOMDocumentHandlerImport object, only search
     * imported styleSheet within stylesheet
     * @return
     */
    @Override
    public Set<CSSImportedStyle> searchImportedStyles() {
        try {
            String rsrc = getResource().getResource();
            InputSource is = new InputSource(new StringReader(rsrc));

            CSSOMDocumentHandlerForImport handler =
                    new CSSOMDocumentHandlerForImport((CSSResource) getResource());

            parser.setDocumentHandler(handler);
            parser.parseStyleSheet(is);

            return handler.getResult();
        } catch (CSSException ex) {
            Logger.getLogger(this.getClass()).warn(ex  + " on " + getResource().getRsrcName());
            return null;
        } catch (IOException ex) {
            Logger.getLogger(this.getClass()).warn(ex  + " on " + getResource().getRsrcName());
            return null;
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(this.getClass()).warn(ex  + " on " + getResource().getRsrcName());  
            return null;
        } catch (TokenMgrError err) {
            Logger.getLogger(this.getClass()).warn(err  + " on " + getResource().getRsrcName());
            return null;
        }
    }

    @Override
    public CSSOMStyleSheet getResult() {
        return result;
    }

}