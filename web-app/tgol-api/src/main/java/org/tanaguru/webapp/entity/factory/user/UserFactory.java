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
package org.tanaguru.webapp.entity.factory.user;

import org.tanaguru.sdk.entity.factory.GenericFactory;
import org.tanaguru.webapp.entity.user.Role;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public interface UserFactory extends GenericFactory<User> {

    /**
     * 
     * @param email1
     * @param password
     * @param name
     * @param firstName
     * @param address
     * @param phoneNumber
     * @param webUrl1
     * @param webUrl2
     * @param identicaId
     * @param twitterId
     * @param role
     * @param email2
     * @return
     *      an initialized user instance
     */
    User create(
            String email1,
            String password,
            String name,
            String firstName,
            String address,
            String phoneNumber,
            String webUrl1,
            String webUrl2,
            String identicaId,
            String twitterId,
            Role role,
            String email2);

}