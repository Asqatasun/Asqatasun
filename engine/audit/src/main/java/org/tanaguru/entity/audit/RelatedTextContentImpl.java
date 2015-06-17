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

/**
 * 
 * @author jkowalczyk
 */
@Entity
public abstract class RelatedTextContentImpl extends RelatedContentImpl implements
        TextContent, RelatedContent, Serializable {

    private static final long serialVersionUID = -8312398273757492821L;
    @Column(name = "Source", length=167772150)
    private String source = null;

    @Column(name = "Adapted_Content",length=167772150)
    private String adaptedContent = null;

    public RelatedTextContentImpl() {
        super();
    }

    public RelatedTextContentImpl(String uri,SSP ssp) {
        super(uri, ssp);
    }

    public RelatedTextContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public RelatedTextContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri, ssp);
    }

    public RelatedTextContentImpl(Date dateOfLoading, String uri, SSP ssp, String source) {
        super(dateOfLoading, uri, ssp);
        this.source = source;
    }

    public RelatedTextContentImpl(Date dateOfLoading, String uri, SSP ssp, String source, int httpStatusCode) {
        super(dateOfLoading, uri, ssp, httpStatusCode);
        this.source = source;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getAdaptedContent() {
        return adaptedContent;
    }

    @Override
    public void setAdaptedContent(String adaptedContent) {
        this.adaptedContent = adaptedContent;
    }

}
