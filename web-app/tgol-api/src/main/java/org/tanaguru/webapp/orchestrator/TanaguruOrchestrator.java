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
package org.tanaguru.webapp.orchestrator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.webapp.entity.contract.Contract;

/**
 *
 * @author jkowalczyk
 */
public interface TanaguruOrchestrator {

    /**
     * 
     * @param contract
     * @param pageUrl
     * @param clientIp
     * @param paramSet
     * @param locale
     * @return 
     */
    Audit auditPage(
            Contract contract,
            String pageUrl,
            String clientIp,
            Set<Parameter> paramSet, 
            Locale locale);
    
    /**
     * 
     * @param contract
     * @param fileMap
     * @param clientIp
     * @param paramSet
     * @param locale
     * @return 
     */
    Audit auditPageUpload(
            Contract contract,
            Map <String, String> fileMap,
            String clientIp,
            Set<Parameter> paramSet, 
            Locale locale);

    /**
     * 
     * @param contract
     * @param siteUrl
     * @param clientIp
     * @param paramSet
     * @param locale 
     */
    void auditSite(
            Contract contract,
            String siteUrl,
            String clientIp,
            Set<Parameter> paramSet, 
            Locale locale);

    /**
     * 
     * @param contract
     * @param siteUrl
     * @param pageUrlList
     * @param clientIp
     * @param paramSet
     * @param locale
     * @return 
     */
    Audit auditSite(
            Contract contract,
            String siteUrl,
            List<String> pageUrlList,
            String clientIp,
            Set<Parameter> paramSet, 
            Locale locale);
    
    /**
     * 
     * @param contract
     * @param idScenario
     * @param clientIp
     * @param parameterSet
     * @param locale 
     */
    void auditScenario(
            Contract contract,
            Long idScenario, 
            String clientIp,
            Set<Parameter> parameterSet, 
            Locale locale);
    
}