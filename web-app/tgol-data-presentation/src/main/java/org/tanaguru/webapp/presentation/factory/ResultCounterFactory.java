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

import org.tanaguru.webapp.presentation.data.ResultCounter;
import org.tanaguru.webapp.presentation.data.ResultCounterImpl;

/**
 * 
 * @author jkowalczyk
 */
public final class ResultCounterFactory {

    /**
     * The unique shared instance of ResultCounterFactory
     */
    private static ResultCounterFactory resultInfosFactory;

    /**
     * Default private constructor
     */
    private ResultCounterFactory(){}

    public static synchronized ResultCounterFactory getInstance(){
        if (resultInfosFactory == null) {
            resultInfosFactory = new ResultCounterFactory();
        }
        return resultInfosFactory;
    }

    public ResultCounter getResultCounter(){
        return new ResultCounterImpl();
    }

    public ResultCounter getResultCounter(
            Integer passedCount,
            Integer failedCount,
            Integer nmiCount,
            Integer naCount,
            Integer ntCount){
        return new ResultCounterImpl(
                passedCount, 
                failedCount, 
                nmiCount, 
                naCount, 
                ntCount);
    }

}