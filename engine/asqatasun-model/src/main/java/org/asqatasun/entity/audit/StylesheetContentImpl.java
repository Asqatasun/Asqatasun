/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.audit;

import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.audit.StylesheetContent;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class StylesheetContentImpl extends RelatedTextContentImpl implements
        StylesheetContent, Serializable {

    private static final long serialVersionUID = -5894544378761102815L;
    public StylesheetContentImpl() {
        super();
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public StylesheetContentImpl(String uri,SSP ssp) {
        super(uri, ssp);
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri, SSP ssp,
            String sourceCode) {
        super(dateOfLoading, uri, ssp, sourceCode);
    }

    public StylesheetContentImpl(Date dateOfLoading, String uri, SSP ssp,
            String sourceCode, int httpStatusCode) {
        super(dateOfLoading, uri, ssp, sourceCode, httpStatusCode);
    }
}
