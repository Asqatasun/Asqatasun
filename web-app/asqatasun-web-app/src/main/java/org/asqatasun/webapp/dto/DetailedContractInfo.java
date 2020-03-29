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
package org.asqatasun.webapp.dto;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 *
 * @author jkowalczyk
 */
public class DetailedContractInfo extends ContractInfo {

    private final Collection<ActInfo> lastActInfoSet = new LinkedHashSet();
    private final Collection<ActInfo> siteActInfoSet = new LinkedHashSet();

    private Date contractCreationDate = null;

    public Date getContractCreationDate() {
        if (contractCreationDate != null){
            return new Date(contractCreationDate.getTime());
        } else {
            return null;
        }
    }


    public void setContractCreationDate(Date contractCreationDate) {
        if (contractCreationDate != null) {
            this.contractCreationDate = new Date(contractCreationDate.getTime());
        }
    }
    
    private Date contractExpirationDate = null;

    public Date getContractExpirationDate() {
        if (contractExpirationDate != null){
            return new Date(contractExpirationDate.getTime());
        } else {
            return null;
        }
    }


    public void setContractExpirationDate(Date contractExpirationDate) {
        if (contractExpirationDate != null) {
            this.contractExpirationDate = new Date(contractExpirationDate.getTime());
        }
    }


    public Collection<ActInfo> getLastActInfoSet() {
        return lastActInfoSet;
    }


    public Collection<ActInfo> getSiteActInfoSet() {
        return siteActInfoSet;
    }


    public final void setSiteActInfoSet(Collection<ActInfo> siteActInfoSet) {
        this.siteActInfoSet.addAll(siteActInfoSet);
    }


    public void addActInfo(ActInfo actInfo) {
        this.lastActInfoSet.add(actInfo);
    }

    private int numberOfAct = 0;

    public int getNumberOfAct() {
        return numberOfAct;
    }


    public void setNumberOfAct(int numberOfAct) {
        this.numberOfAct = numberOfAct;
    }

    private int numberOfDisplayedAct = 0;


    public int getNumberOfDisplayedAct() {
        return numberOfDisplayedAct;
    }


    public void setNumberOfDisplayedAct(int numberOfDisplayedAct) {
        this.numberOfDisplayedAct = numberOfDisplayedAct;
    }

    private boolean isManualAuditEnabled;

    public boolean getIsManualAuditEnabled() {
        return isManualAuditEnabled;
    }


    public void setIsManualAuditEnabled(boolean isManualAuditEnabled) {
        this.isManualAuditEnabled = isManualAuditEnabled;
    }

}
