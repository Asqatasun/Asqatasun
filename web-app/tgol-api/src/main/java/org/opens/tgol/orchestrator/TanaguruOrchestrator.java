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
package org.opens.tgol.orchestrator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tgol.entity.contract.Contract;

/**
 *
 * @author jkowalczyk
 */
public interface TanaguruOrchestrator {

    /**
     *
     * @param contract
     * @param pageUrl
     * @param ipClient
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
     * @param ipClient
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
     * @param ipClient
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
     * @param ipClient
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
    
}