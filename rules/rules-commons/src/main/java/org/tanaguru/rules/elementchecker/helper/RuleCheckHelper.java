/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.elementchecker.helper;

/**
 *
 * @author jkowalczyk
 */
public final class RuleCheckHelper {

    private static final String MSG_SPACER = "_";

    /**
     * 
     * @param msg
     * @param testCode
     * @return a test-specific message code 
     */
    public static String specifyMessageToRule(String msg, String testCode) {
        StringBuilder strb = new StringBuilder(msg);
        strb.append(MSG_SPACER);
        strb.append(testCode);
        return strb.toString();
    }

}