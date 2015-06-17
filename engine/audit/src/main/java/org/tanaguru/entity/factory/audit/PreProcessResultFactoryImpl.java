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
package org.tanaguru.entity.factory.audit;

import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.PreProcessResult;
import org.tanaguru.entity.audit.PreProcessResultImpl;
import org.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public class PreProcessResultFactoryImpl implements PreProcessResultFactory {

    public PreProcessResultFactoryImpl() {
        super();
    }

    @Override
    public PreProcessResult create() {
        return new PreProcessResultImpl();
    }
    
    @Override
    public PreProcessResult create(String key, String value, Audit audit, WebResource webResource) {
        PreProcessResult ppr = this.create();
        ppr.setKey(key);
        ppr.setValue(value);
        ppr.setAudit(audit);
        if (webResource != null) {
            ppr.setSubject(webResource);
        }
        return ppr;
    }

}