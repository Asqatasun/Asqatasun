/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
import org.tanaguru.webapp.action.Action;
import org.tanaguru.webapp.util.TgolEscapeUrl;

/**
 *
 * @author jkowalczyk
 */
public class ContractInfoImpl implements ContractInfo {

    private int id;
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    private String label;
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    private String url;
    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = TgolEscapeUrl.escapeUrl(url);
    }
    
    private ActInfo lastActInfo = null;
    @Override
    public ActInfo getLastActInfo() {
        return lastActInfo;
    }

    @Override
    public void setLastActInfo(ActInfo lastActInfo) {
        this.lastActInfo = lastActInfo;
    }

    private boolean isContractExpired = false;
    @Override
    public boolean getIsContractExpired() {
        return isContractExpired;
    }

    @Override
    public void setContractExpired(boolean isContractExpired) {
        this.isContractExpired = isContractExpired;
    }

    private Date expirationDate;
    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    private AuditProgressionEnum siteAuditProgression = AuditProgressionEnum.NONE;
    @Override
    public String getSiteAuditProgression() {
        return siteAuditProgression.name();
    }

    @Override
    public void setSiteAuditProgression(AuditProgressionEnum siteAuditProgression) {
        this.siteAuditProgression = siteAuditProgression;
    }

    private boolean isActRunning;
    @Override
    public boolean getIsActRunning() {
        return isActRunning;
    }

    @Override
    public void setIsActRunning(boolean isActRunning) {
        this.isActRunning = isActRunning;
    }

    private Collection<Action> actionList;
    @Override
    public Collection<Action> getActionList() {
        return actionList;
    }

    @Override
    public void setActionList(Collection<Action> actionList) {
        this.actionList = actionList;
    }

    private String presetContractKey = "";
    @Override
    public String getPresetContractKey() {
        return presetContractKey;
    }

    @Override
    public void setPresetContractKey(String presetContractKey) {
        this.presetContractKey = presetContractKey;
    }

}