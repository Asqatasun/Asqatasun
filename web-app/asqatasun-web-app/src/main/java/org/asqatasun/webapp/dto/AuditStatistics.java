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
import java.util.LinkedHashMap;
import java.util.Map;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.webapp.util.TgolEscapeUrl;

/**
 * This class handles displayable audit statistics data
 * @author jkowalczyk
 */
public class AuditStatistics {

    /**
     * The url of the checked page
     */
    private String url = null;
    /**
     *
     * @return
     */
    public String getUrl() {
        return url;
    }
    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = TgolEscapeUrl.escapeUrl(url);
    }
    /**
     * The url of the checked page
     */
    private String snapshotUrl = null;
    /**
     *
     * @return
     */
    public String getSnapshotUrl() {
        return snapshotUrl;
    }
    /**
     *
     * @param snaphostUrl
     */
    public void setSnapshotUrl(String snaphostUrl) {
        this.snapshotUrl = TgolEscapeUrl.escapeUrl(snaphostUrl);
    }

    /**
     * The final mark of the audit
     */
    private String rawMark;
    /**
     *
     * @return
     */
    public String getRawMark() {
        return rawMark;
    }

    /**
     *
     * @param rawMark
     */
    public void setRawMark(String rawMark) {
        this.rawMark = rawMark;
    }

    /**
     * The mark of the audit
     */
    private String weightedMark;
    /**
     *
     * @return
     */
    public String getWeightedMark() {
        return weightedMark;
    }

    /**
     *
     * @param weightedMark
     */
    public void setWeightedMark(String weightedMark) {
        this.weightedMark = weightedMark;
    }

    /**
     * The date of the audit
     */
    private Date date = null;
    /**
     *
     * @return
     */
    public Date getDate() {
        if (date != null) {
            return new Date(date.getTime());
        } else {
            return null;
        }
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        }
    }

    private ResultCounter resultCounter = new ResultCounter();
    /**
     *
     * @return
     */
    public ResultCounter getResultCounter() {
        return resultCounter;
    }

    /**
     *
     * @param resultCounter
     */
    public void setResultCounter(ResultCounter resultCounter) {
        this.resultCounter = resultCounter;
    }

    /**
     * The map that handles the results count by theme
     */
    private Map<Theme, ResultCounter> counterByThemeMap;
    /**
     *
     * @return
     */
    public Map<Theme, ResultCounter> getCounterByThemeMap() {
        return counterByThemeMap;
    }

    /**
     *
     * @param counterByThemeMap
     */
    public void setCounterByThemeMap(Map<Theme, ResultCounter> counterByThemeMap) {
        this.counterByThemeMap = counterByThemeMap;
    }

    /**
     *
     * @return
     */
    public int getTestCount(){
        return resultCounter.getNmiCount() +
               resultCounter.getNaCount() +
               resultCounter.getPassedCount() +
               resultCounter.getFailedCount();
    }

    private Integer pageCounter;
    /**
     *
     * @return
     */
    public Integer getPageCounter() {
        return pageCounter;
    }

    /**
     *
     * @param pageCounter
     */
    public void setPageCounter(Integer pageCounter) {
        this.pageCounter = pageCounter;
    }

    private Map<String, String> parametersMap = new LinkedHashMap();
    /**
     *
     * @return
     */
    public Map<String, String> getParametersMap() {
        return parametersMap;
    }

    /**
     * 
     * @param parametersMap
     */
    public void setParametersMap(Map<String, String> parametersMap) {
        this.parametersMap = parametersMap;
    }

    private ScopeEnum auditScope;
    /**
     * 
     * @return
     */
    public ScopeEnum getAuditScope() {
        return auditScope;
    }

    /**
     * 
     * @param auditScope
     */
    public void setAuditScope(ScopeEnum auditScope) {
        this.auditScope = auditScope;
    }

    private Integer auditedPageCounter;
    public Integer getAuditedPageCounter() {
        return auditedPageCounter;
    }
    public void setAuditedPageCounter(Integer auditedPageCounter) {
        this.auditedPageCounter = auditedPageCounter;
    }

}
