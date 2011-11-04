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

import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.user.User;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;

/**
 *
 * @author jkowalczyk
 */
public interface TanaguruOrchestrator {

    /**
     *
     * @param contract
     * @param pageUrl
     * @param testCodeList
     * @param ipClient
     * @param paramSet
     * @return
     */
    Audit auditPage(
            Contract contract,
            String pageUrl,
//            String[] testCodeList,
            String clientIp,
            Set<Parameter> paramSet);
    
    /**
     *
     * @param contract
     * @param pageUrl
     * @param testCodeList
     * @param ipClient
     * @param paramSet
     * @return
     */
    Audit auditPageUpload(
            Contract contract,
            Map <String, String> fileMap,
            String clientIp,
            Set<Parameter> paramSet);

    /**
     *
     * @param contract
     * @param siteUrl
     * @param testCodeList
     * @param ipClient
     * @param paramSet
     */
    void auditSite(
            Contract contract,
            String siteUrl,
//            String[] testCodeList,
            String clientIp,
            Set<Parameter> paramSet);

    /**
     *
     * @param contract
     * @param siteUrl
     * @param pageUrlList
     * @param testCodeList
     * @param ipClient
     * @param paramSet
     * @return
     */
    Audit auditSite(
            Contract contract,
            String siteUrl,
            List<String> pageUrlList,
//            String[] testCodeList,
            String clientIp,
            Set<Parameter> paramSet);
    
    /**
     * 
     * @param contract
     * @param siteUrl
     * @param pageUrlList
     * @param testCodeList
     * @param paramSet
     */
    void auditSiteBg(
            Contract contract,
            String siteUrl,
            List<String> pageUrlList,
//            String[] testCodeList,
            String clientIp,
            Set<Parameter> paramSet);

    /**
     *
     * @param contrat
     * @return
     */
    boolean isAuditRunning(Contract contrat);

    /**
     * 
     * @param contrat
     * @return
     */
    boolean isAuditRunning(User contrat);

}