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
public class ChangePasswordCommand  implements Serializable {

    private static final long serialVersionUID = -6986989026291098474L;
    /**
     * General error message in case of invalid form
     */
    private String generalErrorMsg;
    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }

    private String currentPassword = null;
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(final String currentPassword) {
        this.currentPassword = currentPassword;
    }

    private String newPassword = null;
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }

    private String confirmNewPassword = null;
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(final String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    /**
     * Default constructor
     */
    public ChangePasswordCommand(){
        
    }

}