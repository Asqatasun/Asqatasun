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
package org.tanaguru.entity.audit;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class ImageContentImpl extends RelatedBinaryContentImpl implements
        ImageContent, Serializable {

    private static final long serialVersionUID = -8278714163202499868L;
    
    public ImageContentImpl() {
        super();
    }

    public ImageContentImpl(String uri, SSP ssp) {
        super(uri, ssp);
    }

    public ImageContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public ImageContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public ImageContentImpl(Date dateOfLoading, String uri, SSP ssp,
            byte[] binaryContent) {
        super(dateOfLoading, uri, ssp, binaryContent);
    }

    public ImageContentImpl(Date dateOfLoading, String uri, SSP ssp,
            byte[] binaryContent, int httpStatusCode) {
        super(dateOfLoading, uri, ssp, binaryContent, httpStatusCode);
    }
}
