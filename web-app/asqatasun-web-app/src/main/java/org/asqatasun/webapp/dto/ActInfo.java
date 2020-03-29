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

import java.util.Date;

import org.asqatasun.webapp.util.TgolEscapeUrl;

/**
 *
 * @author jkowalczyk
 */
public class ActInfo {
	
	private boolean isManual;
    public boolean isManual() {
		return isManual;
	}
	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}
    private Date dateManual = null;

    public Date getDateManual() {
        if (dateManual != null) {
            return new Date(dateManual.getTime());
        } else {
            return null;
        }
    }

    public void setDateManual(Date dateManual) {
        if (dateManual != null) {
            this.dateManual = new Date(dateManual.getTime());
        } else {
            this.dateManual = null;
        }
    }
    
    private int weightedMarkManual = 0;
    public int getWeightedMarkManual() {
        return weightedMarkManual;
    }
    public void setWeightedMarkManual(int weightedMarkManual) {
        this.weightedMarkManual = weightedMarkManual;
    }

    private int rawMarkManual = 0;
    public int getRawMarkManual() {
        return rawMarkManual;
    }
    public void setRawMarkManual(int rawMarkManual) {
        this.rawMarkManual = rawMarkManual;
    }

    private String statusManual;
    public String getStatusManual() {
        return statusManual;
    }

    public void setStatusManual(String statusManual){
        this.statusManual = statusManual;
    }


	private Date date = null;
	
    public Date getDate() {
        if (date != null) {
            return new Date(date.getTime());
        } else {
            return null;
        }
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        } else {
            this.date = null;
        }
    }

    private String url;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = TgolEscapeUrl.escapeUrl(url);
    }

    private String scope;
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }

    private int weightedMark = 0;
    public int getWeightedMark() {
        return weightedMark;
    }
    public void setWeightedMark(int weightedMark) {
        this.weightedMark = weightedMark;
    }

    private int rawMark = 0;
    public int getRawMark() {
        return rawMark;
    }
    public void setRawMark(int rawMark) {
        this.rawMark = rawMark;
    }

    private int auditId;
    public int getAuditId() {
        return auditId;
    }
    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    private String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    private String referential;
    public String getReferential() {
        return referential;
    }
    public void setReferential(String referential) {
        this.referential = referential;
    }
    
}
