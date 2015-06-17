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
import org.tanaguru.webapp.action.Action;

/**
 * The interface handles displayable contractInfo data
 *
 * @author jkowalczyk
 */
public interface ContractInfo {

    /**
     *
     * @return
     *      the id of the contract
     */
    int getId();

    /**
     * Sets the id of the contract
     * @param id
     */
    void setId(int id);

    /**
     *
     * @return
     *      the label of the contract
     */
    String getLabel();

    /**
     * Sets the label of the contract
     *
     * @param label
     */
    void setLabel(String label);

    /**
     *
     * @return
     *      the Url associated with the contract
     */
    String getUrl();

    /**
     * Sets the Url associated with the contract (can be empty)
     * 
     * @param url
     */
    void setUrl(String url);

    /**
     *
     * @return
     *      the last actInfo associated with the contract
     */
    ActInfo getLastActInfo();

    /**
     * Sets the last actInfo associated with the contract
     *
     * @param lastActInfo
     */
    void setLastActInfo(ActInfo lastActInfo);

    /**
     *
     * @return
     *      whether the contract is expired
     */
    boolean getIsContractExpired();

    /**
     * Sets whether a contract is expired or not.
     * @param isContractExpired
     */
    void setContractExpired(boolean isContractExpired);

    /**
     *
     * @return
     *      the expiration date of the contract
     */
    Date getExpirationDate();

    /**
     * Sets the expiration date of the contract
     * 
     * @param expirationDate
     */
    void setExpirationDate(Date expirationDate);

    /**
     *
     * @return
     *      the site audit result progression of the contract
     */
    String getSiteAuditProgression();

    /**
     * Sets the site audit result progression of the contract
     * @param siteAuditProgression
     */
    void setSiteAuditProgression(AuditProgressionEnum siteAuditProgression);

    /**
     *
     * @return
     *      whether an act is currently running on this contract
     */
    boolean getIsActRunning();

    /**
     * @param isActRunning
     */
    void setIsActRunning(boolean isActRunning);

    /**
     * 
     * @return the list of action (types of audit) for the current contract
     */
    Collection<Action> getActionList();
    
    /**
     * 
     * @param actionList 
     */
    void setActionList(Collection<Action> actionList);
    
    /**
     * 
     * @return the preset contract key when it exits
     */
    String getPresetContractKey();
    
    /**
     * 
     * @param presetContractKey 
     */
    void setPresetContractKey(String presetContractKey);
}