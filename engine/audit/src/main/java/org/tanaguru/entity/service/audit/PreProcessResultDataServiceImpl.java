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
package org.tanaguru.entity.service.audit;

import java.util.Collection;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.PreProcessResult;
import org.tanaguru.entity.dao.audit.PreProcessResultDAO;
import org.tanaguru.entity.factory.audit.PreProcessResultFactory;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public class PreProcessResultDataServiceImpl extends AbstractGenericDataService<PreProcessResult, Long> implements
        PreProcessResultDataService {

    @Override
    public String getPreProcessResultByKeyAndWebResource(String key, WebResource webresource) {
        return ((PreProcessResultDAO) entityDao).findPreProcessResultByKeyAndWebResource(key, webresource);
    }

    @Override
    public String getPreProcessResultByKeyAndAudit(String key, Audit audit) {
        return ((PreProcessResultDAO) entityDao).findPreProcessResultByKeyAndAudit(key, audit);
    }

    @Override
    public void cleanUpAllPreProcessResultByAudit(Audit audit) {
        ((PreProcessResultDAO) entityDao).deleteAllPreProcessResultByAudit(audit);
    }

    @Override
    public void cleanUpAllPreProcessResultByWebResource(WebResource webResource) {
        ((PreProcessResultDAO) entityDao).deleteAllPreProcessResultByWebResource(webResource);
    }

    @Override
    public Collection<PreProcessResult> getPreProcessResultFromAudit(Audit audit) {
        return ((PreProcessResultDAO) entityDao).findPreProcessResultFromAudit(audit);
    }

    @Override
    public PreProcessResult getPreProcessResult(String key, String value, Audit audit, WebResource webResource) {
        PreProcessResult preProcessResult = ((PreProcessResultFactory) entityFactory)
                .create(key, value, audit, webResource);
        return preProcessResult;
    }
    
}