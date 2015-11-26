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
package org.asqatasun.webapp.entity.factory.user;

import org.asqatasun.webapp.entity.user.Role;
import org.asqatasun.webapp.entity.user.User;
import org.asqatasun.webapp.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
public class UserFactoryImpl implements UserFactory {

    @Override
    public User create() {
        return new UserImpl();
    }

    @Override
    public User create(
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
            String email2) {
        User user = create();
        user.setEmail1(email1);
        user.setPassword(password);
        user.setName(name);
        user.setAddress(address);
        user.setFirstName(firstName);
        user.setTwitterId(twitterId);
        user.setIdenticaId(identicaId);
        user.setWebUrl1(webUrl1);
        user.setWebUrl2(webUrl2);
        user.setRole(role);
        user.setPhoneNumber(phoneNumber);
        user.setEmail1(email2);
        return user;
    }

}