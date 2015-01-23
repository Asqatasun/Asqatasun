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
package org.opens.tgol.presentation.data;

import org.opens.tgol.util.TgolEscapeUrl;

/**
 * @author jkowalczyk
 */
public class FailedPageInfoImpl implements FailedPageInfo{

    /**
     * Default constructor
     */
    public FailedPageInfoImpl(){}

    /**
     * Constructor
     * @param url
     * @param id
     * @param failedTestCounter
     * @param failedOccurrenceCounter
     */
    public FailedPageInfoImpl(String url, Long id, Long failedTestCounter, Long failedOccurrenceCounter) {
        setWebResourceUrl(url);
        this.webResourceId = id;
        if (failedTestCounter != -1) {
            this.failedTestCounter = failedTestCounter;
        } else {
            this.failedTestCounter = Long.valueOf(0);
        }
        this.failedOccurrenceCounter = failedOccurrenceCounter;
    }

    private String webResourceUrl;

    @Override
    public String getWebResourceUrl() {
        return webResourceUrl;
    }

    @Override
    public final void setWebResourceUrl(String webResourceUrl) {
        this.webResourceUrl = TgolEscapeUrl.escapeUrl(webResourceUrl);
    }

    private Long webResourceId;

    @Override
    public Long getWebResourceId() {
        return webResourceId;
    }

    @Override
    public void setWebResourceId(Long webResourceId) {
        this.webResourceId = webResourceId;
    }

    private Long failedTestCounter;

    @Override
    public Long getFailedTestCounter() {
        return failedTestCounter;
    }

    @Override
    public void setFailedTestCounter(Long failedTestCounter) {
        this.failedTestCounter = failedTestCounter;
    }

    private Long failedOccurrenceCounter;

    @Override
    public Long getFailedOccurrenceCounter() {
        return failedOccurrenceCounter;
    }

    @Override
    public void setFailedOccurrenceCounter(Long failedOccurrenceCounter) {
        this.failedOccurrenceCounter = failedOccurrenceCounter;
    }

}