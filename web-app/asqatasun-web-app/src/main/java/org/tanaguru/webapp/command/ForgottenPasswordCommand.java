/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.command;

import java.io.Serializable;

/**
 *
 * @author jkowalczyk
 */
public class ForgottenPasswordCommand  implements Serializable {

    private static final long serialVersionUID = -6643145905634849329L;
    /**
     * General error message in case of invalid form
     */
    private String generalErrorMsg;
    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }

    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Default constructor
     */
    public ForgottenPasswordCommand(){
        
    }

}