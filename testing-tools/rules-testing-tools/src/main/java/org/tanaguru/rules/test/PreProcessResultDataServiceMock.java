/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.rules.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.persistence.NoResultException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.PreProcessResult;
import org.tanaguru.entity.service.audit.PreProcessResultDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import org.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class PreProcessResultDataServiceMock implements PreProcessResultDataService{

    private static final Logger LOGGER = Logger.getLogger(PreProcessResultDataServiceMock.class);
    
    @Override
    public String getPreProcessResultByKeyAndWebResource(String key, WebResource webresource) {
        String path = StringUtils.replace(webresource.getURL(), "html", "json");
        path = StringUtils.replace(path, "file:", "");
        try {
            String json = FileUtils.readFileToString(new File(path));
            LOGGER.debug(json);
            return json;
        } catch (IOException ex) {
            LOGGER.warn(ex);
            throw new NoResultException();
        }
    }

    @Override
    public String getPreProcessResultByKeyAndAudit(String key, Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cleanUpAllPreProcessResultByAudit(Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cleanUpAllPreProcessResultByWebResource(WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PreProcessResult create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(PreProcessResult entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(PreProcessResult entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Collection<PreProcessResult> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<PreProcessResult> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PreProcessResult read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PreProcessResult saveOrUpdate(PreProcessResult entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<PreProcessResult> saveOrUpdate(Collection<PreProcessResult> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<PreProcessResult, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<PreProcessResult> factory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PreProcessResult update(PreProcessResult entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<PreProcessResult> getPreProcessResultFromAudit(Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PreProcessResult getPreProcessResult(String key, String value, Audit audit, WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}