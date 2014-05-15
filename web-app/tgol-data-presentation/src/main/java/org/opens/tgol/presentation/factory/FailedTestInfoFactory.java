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
package org.opens.tgol.presentation.factory;

import org.opens.tgol.presentation.data.FailedTestInfo;
import org.opens.tgol.presentation.data.FailedTestInfoImpl;

/**
 * 
 * @author jkowalczyk
 */
public final class FailedTestInfoFactory {

    /**
     * The unique shared instance of FailedTestInfoFactory
     */
    private static FailedTestInfoFactory failedTestInfoFactory;

    /**
     * Default private constructor
     */
    private FailedTestInfoFactory(){}

    public static synchronized FailedTestInfoFactory getInstance(){
        if (failedTestInfoFactory == null) {
            failedTestInfoFactory = new FailedTestInfoFactory();
        }
        return failedTestInfoFactory;
    }

    /**
     * 
     * @return
     */
    public FailedTestInfo getFailedTestInfo(){
        return new FailedTestInfoImpl();
    }

    /**
     * 
     * @param testCode
     * @param testLabel
     * @param pageCounter
     * @param testLevelCode
     * @return
     */
    public FailedTestInfo getFailedTestInfo(String testCode, String testLabel, Long pageCounter, String testLevelCode) {
        return new FailedTestInfoImpl(testCode, testLabel, pageCounter, testLevelCode);
    }

}