/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.entity.dao.user;

import org.asqatasun.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.entity.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class UserDAOTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "flatXmlDataSet.xml";
    @Autowired
    private UserDAO userDAO;
    
    public UserDAOTest() {
        super();
    }

    @Override
    protected String getDataSetFilename() {
        return getInputDataFilePath()+INPUT_DATA_SET_FILENAME;
    }

    @Test
    public void testRead(){
        User user = userDAO.read(1L);
        assertEquals("Test1", user.getName());
        assertEquals("Role1", user.getRole().getRoleName());
        user = userDAO.read(2L);
        assertEquals("Test2", user.getName());
        assertEquals("Role2", user.getRole().getRoleName());
    }

    @Test
    public void testFindUserFromEmail(){
        User user = userDAO.findUserFromEmail("test1@test.com");
        assertEquals(Long.valueOf(1), user.getId());
        assertEquals(2, user.getContractSet().size());
        user = userDAO.findUserFromEmail("test@test.com");
        assertNull(user);
    }

    @Test
    public void testFindUserFromName(){
        User user = userDAO.findUserFromName("Test1");
        assertEquals(Long.valueOf(1), user.getId());
        user = userDAO.findUserFromName("test");
        assertNull(user);
    }

    @Test
    public void testIsAccountActivated(){
        assertFalse(userDAO.isAccountActivated("test1@test.com"));
        assertTrue(userDAO.isAccountActivated("test2@test.com"));
        assertTrue(userDAO.isAccountActivated("test3@test.com"));
        assertFalse(userDAO.isAccountActivated("test4@test.com"));
    }

}
