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
package org.tanaguru.webapp.entity.dao.user;

import org.tanaguru.webapp.entity.dao.test.AbstractDaoTestCase;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class UserDAOImplTest extends AbstractDaoTestCase {

    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";

    private UserDAO userDAO;
    
    public UserDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        userDAO = (UserDAO)
                springBeanFactory.getBean("userDAO");
    }

    public void testRead(){
        User user = userDAO.read(Long.valueOf(1));
        assertEquals("Test1", user.getName());
        assertEquals("Role1", user.getRole().getRoleName());
        user = userDAO.read(Long.valueOf(2));
        assertEquals("Test2", user.getName());
        assertEquals("Role2", user.getRole().getRoleName());
    }

    public void testFindUserFromEmail(){
        User user = userDAO.findUserFromEmail("test1@test.com");
        assertEquals(Long.valueOf(1), user.getId());
        assertEquals(2, user.getContractSet().size());
        user = userDAO.findUserFromEmail("test@test.com");
        assertNull(user);
    }

   public void testFindUserFromName(){
        User user = userDAO.findUserFromName("Test1");
        assertEquals(Long.valueOf(1), user.getId());
        user = userDAO.findUserFromName("test");
        assertNull(user);
    }
   
   public void testIsAccountActivated(){
        assertFalse(userDAO.isAccountActivated("test1@test.com"));
        assertTrue(userDAO.isAccountActivated("test2@test.com"));
        assertTrue(userDAO.isAccountActivated("test3@test.com"));
        assertFalse(userDAO.isAccountActivated("test4@test.com"));
    }

}