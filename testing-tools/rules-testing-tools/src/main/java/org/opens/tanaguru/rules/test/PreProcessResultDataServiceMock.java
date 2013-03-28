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
package org.opens.tanaguru.rules.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.PreProcessResult;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.service.audit.PreProcessResultDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class PreProcessResultDataServiceMock implements PreProcessResultDataService{

    private int i=0;
    private Map<Long, RelatedContent> relatedContentMap = new HashMap<Long, RelatedContent>();

    @Override
    public String getPreProcessResultByKeyAndWebResource(String key, WebResource webresource) {
        return "[{\"isHidden\"=false, \"color\"=\"rgb(0, 0, 0)\", \"path\"=\"#kba-release\", \"fontSize\"=\"13px\", \"fontWeight\"=\"normal\", \"luminosity\"=\"8.15\", \"bgcolor\"=\"rgb(255, 124, 30)\"}, {\"isHidden\"=false, \"fontWeight\"=\"normal\", \"color\"=\"rgb(0, 0, 0)\", \"path\"=\"#identification > form:nth-of-type(1) > table:nth-of-type(1) > tbody:nth-of-type(1) > tr:nth-of-type(1) > td:nth-of-type(1)\", \"fontSize\"=\"11.05px\", \"luminosity\"=\"20.43\", \"bgcolor\"=\"rgb(255, 255, 204)\"}, {\"isHidden\"=false, \"color\"=\"rgb(0, 0, 238)\", \"path\"=\"#identification > form:nth-of-type(1) > table:nth-of-type(1) > tbody:nth-of-type(1) > tr:nth-of-type(1) > td:nth-of-type(1) > a:nth-of-type(1)\", \"fontSize\"=\"11.05px\", \"luminosity\"=9.14, \"fontWeight\"=\"normal\", \"bgcolor\"=\"rgb(255, 255, 204)\"}, {\"isHidden\"=\"false\", \"color\"=\"rgb(0, 0, 0)\", \"path\"=\"#identification > form:nth-of-type(1) > table:nth-of-type(1) > tbody:nth-of-type(1) > tr:nth-of-type(2) > td:nth-of-type(1) > label:nth-of-type(1)\", \"fontWeight\"=\"normal\", \"fontSize\"=\"11.05px\", \"luminosity\"=\"20.43\", \"bgcolor\"=\"rgb(255, 255, 204)\"}]";
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
    public void delete(Set<PreProcessResult> entitySet) {
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
    public Set<PreProcessResult> saveOrUpdate(Set<PreProcessResult> entitySet) {
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
    
    
}