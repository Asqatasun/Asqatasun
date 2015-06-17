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
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * @author jkowalczyk
 */
@Entity
public abstract class RelatedBinaryContentImpl extends RelatedContentImpl implements
        BinaryContent, RelatedContent, Serializable {

    private static final long serialVersionUID = -7599830018001646757L;
    @Column(name = "Binary_Content", length=4000000)
    private Byte[] binaryContent;

    public RelatedBinaryContentImpl() {
        super();
    }

    public RelatedBinaryContentImpl(String uri, SSP ssp) {
        super(uri, ssp);
    }

    public RelatedBinaryContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public RelatedBinaryContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public RelatedBinaryContentImpl(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            byte[] binaryContent) {
        super(dateOfLoading, uri, ssp);
        this.binaryContent = ArrayUtils.toObject(binaryContent);
    }

    public RelatedBinaryContentImpl(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            byte[] binaryContent,
            int httpStatusCode) {
        super(dateOfLoading, uri, ssp, httpStatusCode);
        this.binaryContent = ArrayUtils.toObject(binaryContent);
    }

    @Override
    public byte[] getContent() {
        return ArrayUtils.toPrimitive(binaryContent);
    }

    @Override
    public void setContent(byte[] binaryContent) {
        this.binaryContent = ArrayUtils.toObject(binaryContent);
    }
}
