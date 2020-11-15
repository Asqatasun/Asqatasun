/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.rules.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.PreProcessResult;
import org.asqatasun.entity.service.audit.PreProcessResultDataService;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.entity.dao.GenericDAO;
import org.asqatasun.entity.GenericFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author jkowalczyk
 */
@Profile("test")
@Component
public class PreProcessResultDataServiceMock implements PreProcessResultDataService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PreProcessResultDataServiceMock.class);
    
    @Override
    public String getPreProcessResultByKeyAndWebResource(String key, WebResource webresource) {
        String path = StringUtils.replace(webresource.getURL(), "html", "json");
        path = StringUtils.replace(path, "file:", "");
        try {
            String json = FileUtils.readFileToString(new File(path));
            LOGGER.debug(json);
            return json;
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage());
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
