/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.service.command.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.service.command.AuditCommand;

/**
 * This factory returns a AuditCommand object that handles meta-data regarding
 * the audit type.
 * 
 * 
 * @author jkowalczyk
 */
public interface AuditCommandFactory {
    
    /**
     * 
     * @param url
     * @param paramSet
     * @param isSite
     * @return 
     */
    AuditCommand create(String url, Set<Parameter> paramSet, List<Tag> tagList, boolean isSite);
    
    /**
     * 
     * @param fileMap
     * @param paramSet
     * @return 
     */
    AuditCommand create(Map<String, String> fileMap, Set<Parameter> paramSet, List<Tag> tagList);
    
    /**
     * 
     * @param siteUrl
     * @param pageUrlList
     * @param paramSet
     * @return 
     */
    AuditCommand create(String siteUrl, List<String> pageUrlList, Set<Parameter> paramSet, List<Tag> tagList);
    
    /**
     * 
     * @param scenarioName
     * @param scenario
     * @param paramSet
     * @return 
     */
    AuditCommand create(String scenarioName, String scenario, Set<Parameter> paramSet, List<Tag> tagList);
}
