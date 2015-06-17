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

import java.util.Date;
import org.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface Content extends Entity {

    /**
     *
     * @return the audit
     */
    Audit getAudit();

    /**
     *
     * @return the date of loading
     */
    Date getDateOfLoading();

    /**
     *
     * @return the URI
     */
    String getURI();

    /**
     *
     * @return the http Status Code
     */
    int getHttpStatusCode();

    /**
     *
     * @param audit
     *            the audit to set
     */
    void setAudit(Audit audit);

    /**
     *
     * @param dateOfLoading
     *            the date of loading to set
     */
    void setDateOfLoading(Date dateOfLoading);

    /**
     *
     * @param uri
     *            the URI to set
     */
    void setURI(String uri);

    /**
     *
     * @param httpStatusCode
     *            the Http Status Code when fetched the content
     */
    void setHttpStatusCode(int httpStatusCode);

}