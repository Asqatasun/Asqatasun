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
import org.asqatasun.webapp.action.Action;
import org.asqatasun.webapp.util.TgolEscapeUrl;

/**
 *
 * @author jkowalczyk
 */
public class ContractInfo {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String label;
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    private String url;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = TgolEscapeUrl.escapeUrl(url);
    }
    
    private ActInfo lastActInfo = null;

    public ActInfo getLastActInfo() {
        return lastActInfo;
    }


    public void setLastActInfo(ActInfo lastActInfo) {
        this.lastActInfo = lastActInfo;
    }

    private boolean isContractExpired = false;

    public boolean getIsContractExpired() {
        return isContractExpired;
    }


    public void setContractExpired(boolean isContractExpired) {
        this.isContractExpired = isContractExpired;
    }

    private Date expirationDate;

    public Date getExpirationDate() {
        return expirationDate;
    }


    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    private AuditProgressionEnum siteAuditProgression = AuditProgressionEnum.NONE;

    public String getSiteAuditProgression() {
        return siteAuditProgression.name();
    }


    public void setSiteAuditProgression(AuditProgressionEnum siteAuditProgression) {
        this.siteAuditProgression = siteAuditProgression;
    }

    private boolean isActRunning;

    public boolean getIsActRunning() {
        return isActRunning;
    }


    public void setIsActRunning(boolean isActRunning) {
        this.isActRunning = isActRunning;
    }

    private Collection<Action> actionList;

    public Collection<Action> getActionList() {
        return actionList;
    }


    public void setActionList(Collection<Action> actionList) {
        this.actionList = actionList;
    }

    private String presetContractKey = "";

    public String getPresetContractKey() {
        return presetContractKey;
    }


    public void setPresetContractKey(String presetContractKey) {
        this.presetContractKey = presetContractKey;
    }

}
