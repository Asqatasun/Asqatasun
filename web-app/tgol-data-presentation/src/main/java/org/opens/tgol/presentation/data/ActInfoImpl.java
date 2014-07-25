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
package org.opens.tgol.presentation.data;

import java.util.Date;

import org.opens.tgol.util.TgolEscapeUrl;

/**
 *
 * @author jkowalczyk
 */
public class ActInfoImpl implements ActInfo{
	
	private boolean isManual;
	@Override
    public boolean isManual() {
		return isManual;
	}
    @Override
	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}
    private Date dateManual = null;
    
    @Override
    public Date getDateManual() {
        if (dateManual != null) {
            return new Date(dateManual.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setDateManual(Date dateManual) {
        if (dateManual != null) {
            this.dateManual = new Date(dateManual.getTime());
        } else {
            this.dateManual = null;
        }
    }
    
    private int weightedMarkManual = 0;
    @Override
    public int getWeightedMarkManual() {
        return weightedMarkManual;
    }

    @Override
    public void setWeightedMarkManual(int weightedMarkManual) {
        this.weightedMarkManual = weightedMarkManual;
    }

    private int rawMarkManual = 0;
    @Override
    public int getRawMarkManual() {
        return rawMarkManual;
    }

    @Override
    public void setRawMarkManual(int rawMarkManual) {
        this.rawMarkManual = rawMarkManual;
    }
    
    private String statusManual;
    @Override
    public String getStatusManual() {
        return statusManual;
    }
    
    @Override
    public void setStatusManual(String statusManual){
        this.statusManual = statusManual;
    }


	private Date date = null;
	
	
    @Override
    public Date getDate() {
        if (date != null) {
            return new Date(date.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setDate(Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        } else {
            this.date = null;
        }
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

    private String scope;
    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    private int weightedMark = 0;
    @Override
    public int getWeightedMark() {
        return weightedMark;
    }

    @Override
    public void setWeightedMark(int weightedMark) {
        this.weightedMark = weightedMark;
    }

    private int rawMark = 0;
    @Override
    public int getRawMark() {
        return rawMark;
    }

    @Override
    public void setRawMark(int rawMark) {
        this.rawMark = rawMark;
    }

    private int auditId;
    @Override
    public int getAuditId() {
        return auditId;
    }

    @Override
    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    private String status;
    @Override
    public String getStatus() {
        return status;
    }
    
    @Override
    public void setStatus(String status){
        this.status = status;
    }

    private String referential;
    @Override
    public String getReferential() {
        return referential;
    }

    @Override
    public void setReferential(String referential) {
        this.referential = referential;
    }
    
}
