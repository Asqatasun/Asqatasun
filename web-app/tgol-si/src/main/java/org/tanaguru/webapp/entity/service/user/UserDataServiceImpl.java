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
package org.tanaguru.webapp.entity.service.user;

import org.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.tanaguru.webapp.entity.dao.user.UserDAO;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class UserDataServiceImpl extends AbstractGenericDataService<User, Long>
        implements UserDataService {

    @Override
    public User getUserFromEmail(String email) {
        return ((UserDAO)entityDao).findUserFromEmail(email);
    }

    @Override
    public User getUserFromName(String name) {
        return ((UserDAO)entityDao).findUserFromName(name);
    }

    @Override
    public boolean isAccountActivated(String email) {
        return ((UserDAO)entityDao).isAccountActivated(email);
    }

}
