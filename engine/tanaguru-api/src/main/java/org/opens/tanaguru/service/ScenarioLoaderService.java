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
package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.scenarioloader.ScenarioLoaderFactory;

/**
 * 
 * @author jkowalczyk
 */
@XmlTransient
public interface ScenarioLoaderService {

    /**
     *
     * @param webResource
     * @param fileMap
     * @return
     */
    List<Content> loadScenario(WebResource webResource, String scenarioFile);

    /**
     *
     * @param contentLoaderFactory
     */
    void setScenarioLoaderFactory(ScenarioLoaderFactory scenarioLoaderFactory);

}
