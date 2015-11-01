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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.presentation.data;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 *
 * @author jkowalczyk
 */
public class DetailedContractInfoImpl extends ContractInfoImpl implements DetailedContractInfo{

    private final Collection<ActInfo> lastActInfoSet = new LinkedHashSet();
    private final Collection<ActInfo> siteActInfoSet = new LinkedHashSet();

    private Date contractCreationDate = null;
    @Override
    public Date getContractCreationDate() {
        if (contractCreationDate != null){
            return new Date(contractCreationDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setContractCreationDate(Date contractCreationDate) {
        if (contractCreationDate != null) {
            this.contractCreationDate = new Date(contractCreationDate.getTime());
        }
    }
    
    private Date contractExpirationDate = null;
    @Override
    public Date getContractExpirationDate() {
        if (contractExpirationDate != null){
            return new Date(contractExpirationDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setContractExpirationDate(Date contractExpirationDate) {
        if (contractExpirationDate != null) {
            this.contractExpirationDate = new Date(contractExpirationDate.getTime());
        }
    }

    @Override
    public Collection<ActInfo> getLastActInfoSet() {
        return lastActInfoSet;
    }

    @Override
    public Collection<ActInfo> getSiteActInfoSet() {
        return siteActInfoSet;
    }

    @Override
    public final void setSiteActInfoSet(Collection<ActInfo> siteActInfoSet) {
        this.siteActInfoSet.addAll(siteActInfoSet);
    }

    @Override
    public void addActInfo(ActInfo actInfo) {
        this.lastActInfoSet.add(actInfo);
    }

    private int numberOfAct = 0;
    @Override
    public int getNumberOfAct() {
        return numberOfAct;
    }

    @Override
    public void setNumberOfAct(int numberOfAct) {
        this.numberOfAct = numberOfAct;
    }

    private int numberOfDisplayedAct = 0;

    @Override
    public int getNumberOfDisplayedAct() {
        return numberOfDisplayedAct;
    }

    @Override
    public void setNumberOfDisplayedAct(int numberOfDisplayedAct) {
        this.numberOfDisplayedAct = numberOfDisplayedAct;
    }

    private boolean isManualAuditEnabled;
    @Override
    public boolean getIsManualAuditEnabled() {
        return isManualAuditEnabled;
    }

    @Override
    public void setIsManualAuditEnabled(boolean isManualAuditEnabled) {
        this.isManualAuditEnabled = isManualAuditEnabled;
    }

}