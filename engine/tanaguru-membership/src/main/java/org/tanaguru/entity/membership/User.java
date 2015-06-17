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
package org.tanaguru.entity.membership;

import java.util.Collection;
import org.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface User extends Entity {

    /**
     *
     * @return the password
     */
    String getPassword();

    /**
     *
     * @return the roles
     */
    Collection<? extends Role> getRoleList();

    /**
     *
     * @return the username
     */
    String getUsername();

    /**
     *
     * @param password
     *            the password to set
     */
    void setPassword(String password);

    /**
     *
     * @param roles
     *            the roles to set
     */
    void setRoleList(Collection<? extends Role> roles);

    /**
     *
     * @param username
     *            the username to set
     */
    void setUsername(String username);

}