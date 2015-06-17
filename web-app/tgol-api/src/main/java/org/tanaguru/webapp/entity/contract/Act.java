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
package org.tanaguru.webapp.entity.contract;

import java.util.Date;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.sdk.entity.Entity;

/**
 *
 * @author jkowalczyk
 */
public interface Act extends Entity {

    /**
     *
     * @return
     *      the begin date of the act
     */
    Date getBeginDate();

    /**
     * 
     * @param beginDate
     */
    void setBeginDate(Date beginDate);

    /**
     *
     * @return
     *      the end date of the act
     */
    Date getEndDate();

    /**
     *
     * @param endDate
     */
    void setEndDate(Date endDate);

    /**
     * 
     * @return
     *      the current status of the act
     */
    ActStatus getStatus();

    /**
     * 
     * @param status
     */
    void setStatus (ActStatus status);

    /**
     *
     * @return
     *      the contract associated with the act
     */
    Contract getContract();

    /**
     *
     * @param contract
     */
    void setContract(Contract contract);

    /**
     *
     * @return
     *      the tanaguru's webresource associated with this act
     */
//    WebResource getWebResource();
//
//    /**
//     * 
//     * @param webResource
//     */
//    void setWebResource(WebResource webResource);

    Audit getAudit();
//
//    /**
//     * 
//     * @param audit
//     */
    void setAudit(Audit audit);
    
    /**
     *
     * @return
     *          the scope of the act
     */
    Scope getScope();

    /**
     * 
     * @param scope
     */
    void setScope(Scope scope);

    /**
     * 
     * @return
     *      the ip of the client
     */
    String getClientIp();

    /**
     * 
     * @param clientIp
     */
    void setClientIp(String clientIp);
}
