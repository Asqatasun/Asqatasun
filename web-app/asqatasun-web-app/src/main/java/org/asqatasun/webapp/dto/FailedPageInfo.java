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

import org.asqatasun.webapp.util.TgolEscapeUrl;

/**
 * @author jkowalczyk
 */
public class FailedPageInfo {

    /**
     * Default constructor
     */
    public FailedPageInfo(){}

    /**
     * Constructor
     * @param url
     * @param id
     * @param failedTestCounter
     * @param failedOccurrenceCounter
     */
    public FailedPageInfo(String url, Long id, Long failedTestCounter, Long failedOccurrenceCounter) {
        setWebResourceUrl(url);
        this.webResourceId = id;
        if (failedTestCounter != -1) {
            this.failedTestCounter = failedTestCounter;
        } else {
            this.failedTestCounter = 0L;
        }
        this.failedOccurrenceCounter = failedOccurrenceCounter;
    }

    private String webResourceUrl;

    public String getWebResourceUrl() {
        return webResourceUrl;
    }

    public final void setWebResourceUrl(String webResourceUrl) {
        this.webResourceUrl = TgolEscapeUrl.escapeUrl(webResourceUrl);
    }

    private Long webResourceId;
    public Long getWebResourceId() {
        return webResourceId;
    }
    public void setWebResourceId(Long webResourceId) {
        this.webResourceId = webResourceId;
    }

    private Long failedTestCounter;
    public Long getFailedTestCounter() {
        return failedTestCounter;
    }
    public void setFailedTestCounter(Long failedTestCounter) {
        this.failedTestCounter = failedTestCounter;
    }

    private Long failedOccurrenceCounter;
    public Long getFailedOccurrenceCounter() {
        return failedOccurrenceCounter;
    }
    public void setFailedOccurrenceCounter(Long failedOccurrenceCounter) {
        this.failedOccurrenceCounter = failedOccurrenceCounter;
    }

}
