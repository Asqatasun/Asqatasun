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
package org.opens.tanaguru.entity.audit;

import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface PreProcessResult extends Entity {

    /**
     *
     * @return the audit a the gross result
     */
    Audit getAudit();

    /**
     *
     * @return the subject
     */
    WebResource getSubject();

    /**
     *
     * @return the value
     */
    String getValue();
    
    /**
     *
     * @return the pre-process result key
     */
    String getKey();

    /**
     *
     * @param audit
     *            the audit associated with the pre-process
     */
    void setAudit(Audit audit);

    /**
     *
     * @param subject
     *            the subject to set
     */
    void setSubject(WebResource subject);

    /**
     *
     * @param value
     *            the value to set
     */
    void setValue(String value);
    
    /**
     *
     * @param key
     *            the key to set
     */
    void setKey(String key);
    

}