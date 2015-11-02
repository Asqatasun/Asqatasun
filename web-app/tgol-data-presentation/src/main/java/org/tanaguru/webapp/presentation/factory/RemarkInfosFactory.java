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

import org.tanaguru.webapp.presentation.data.RemarkInfos;
import org.tanaguru.webapp.presentation.data.RemarkInfosImpl;

/**
 * 
 * @author jkowalczyk
 */
public final class RemarkInfosFactory {

    /**
     * The unique shared instance of RemarkInfosFactory
     */
    private static RemarkInfosFactory remarkInfosFactory;

    /**
     * Default private constructor
     */
    private RemarkInfosFactory(){}

    public static synchronized RemarkInfosFactory getInstance(){
        if (remarkInfosFactory == null) {
            remarkInfosFactory = new RemarkInfosFactory();
        }
        return remarkInfosFactory;
    }

    public RemarkInfos getRemarksInfo(){
        return new RemarkInfosImpl();
    }

    public RemarkInfos getRemarksInfo(String messageCode){
        return new RemarkInfosImpl(messageCode);
    }

    public RemarkInfos getRemarksInfo(String messageCode, String remarkTarget){
        return new RemarkInfosImpl(messageCode, remarkTarget);
    }

}
