/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.webapp.presentation.factory;

import org.tanaguru.webapp.presentation.data.FailedPageInfo;
import org.tanaguru.webapp.presentation.data.FailedPageInfoImpl;


/**
 * 
 * @author jkowalczyk
 */
public final class FailedPageInfoFactory {

    /**
     * The unique shared instance of FailedPageInfoFactory
     */
    private static FailedPageInfoFactory failedPageInfoFactory;

    /**
     * Default private constructor
     */
    private FailedPageInfoFactory(){}

    public static synchronized FailedPageInfoFactory getInstance(){
        if (failedPageInfoFactory == null) {
            failedPageInfoFactory = new FailedPageInfoFactory();
        }
        return failedPageInfoFactory;
    }

    /**
     * 
     * @return
     */
    public FailedPageInfo getFailedPageInfo(){
        return new FailedPageInfoImpl();
    }

    /**
     *
     * @param url
     * @param id
     * @param failedTestCounter
     * @param failedOccurrenceCounter
     * @return
     */
    public FailedPageInfo getFailedPageInfo(
            String url,
            Long id,
            Long failedTestCounter,
            Long failedOccurrenceCounter) {
        return new FailedPageInfoImpl(url, id, failedTestCounter, failedOccurrenceCounter);
    }
}