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
package org.tanaguru.webapp.presentation.data;

import java.util.Collection;
import java.util.Date;

/**
 * The interface handles displayable and detailed contractInfo data
 *
 * @author jkowalczyk
 */
public interface DetailedContractInfo extends ContractInfo{

    /**
     *
     * @return
     *      the date of creation of the contract
     */
    Date getContractCreationDate();

    /**
     * Sets the date of creation of the contract
     * 
     * @param contractCreationDate
     */
    void setContractCreationDate(Date contractCreationDate);
    
    /**
     *
     * @return
     *      the date of expiration of the contract
     */
    Date getContractExpirationDate();

    /**
     * Sets the date of expiration of the contract
     * 
     * @param contractExpirationDate
     */
    void setContractExpirationDate(Date contractExpirationDate);

    /**
     *
     * @return
     *      the last actInfo set
     */
    Collection<ActInfo> getLastActInfoSet();

    /**
     *
     * @return
     */
    Collection<ActInfo> getSiteActInfoSet();

    /**
     *
     * @param siteActInfoSet
     */
    void setSiteActInfoSet(Collection<ActInfo> siteActInfoSet);

    /**
     *
     * @param actInfo
     */
    void addActInfo(ActInfo actInfo);

    /**
     *
     * @return
     */
    int getNumberOfAct();

    /**
     *
     * @param numberOfAct
     */
    void setNumberOfAct(int numberOfAct);

    /**
     *
     * @return
     */
    int getNumberOfDisplayedAct();

    /**
     * 
     * @param numberOfDisplayedAct
     */
    void setNumberOfDisplayedAct(int numberOfDisplayedAct);
    
    /**
     * 
     * @return whether the current contract handles the manual audit 
     * functionality
     */
    boolean getIsManualAuditEnabled();
    
    /**
     * 
     * @param isManualAuditEnabled
     */
    void setIsManualAuditEnabled(boolean isManualAuditEnabled);

}