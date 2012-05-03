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
package org.opens.tgol.entity.service.user;

import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class MockUserDataService implements UserDataService {
    Map<String, User> userMap = new HashMap<String, User>();

    public MockUserDataService() {
        User user = new UserImpl();
        user.setAccountActivation(true);
        user.setEmail1("test1@test.com");
        user.setName("test1");
        user.setId(Long.valueOf(1));
        userMap.put("test1@test.com", user);
        user = new UserImpl();
        user.setAccountActivation(true);
        user.setEmail1("test2@test.com");
        user.setName("test2");
        user.setId(Long.valueOf(2));
        userMap.put("test2@test.com", user);
    }

    @Override
    public User getUserFromEmail(String email) {
        return userMap.get(email);
    }

    @Override
    public User getUserFromName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAccountActivated(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User create() {
        return new UserImpl();
    }

    @Override
    public void create(User e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<User> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends User> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User read(Long k) {
        for (User user : userMap.values()) {
            if (user.getId().equals(k)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User saveOrUpdate(User e) {
        return e;
    }

    @Override
    public Set<User> saveOrUpdate(Set<User> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<User, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<User> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User update(User e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}