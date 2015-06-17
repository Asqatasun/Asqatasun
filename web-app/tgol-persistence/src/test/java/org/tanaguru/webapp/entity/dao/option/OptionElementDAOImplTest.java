/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.entity.dao.option;

import java.util.Collection;
import org.tanaguru.webapp.entity.dao.test.AbstractDaoTestCase;
import org.tanaguru.webapp.entity.dao.user.UserDAO;
import org.tanaguru.webapp.entity.option.Option;
import org.tanaguru.webapp.entity.option.OptionElement;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class OptionElementDAOImplTest extends AbstractDaoTestCase {
    
    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";
    
    private OptionElementDAO optionElementDAO;
    private UserDAO userDAO;
    
    public OptionElementDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        optionElementDAO = (OptionElementDAO)
                springBeanFactory.getBean("optionElementDAO");
        userDAO = (UserDAO)
                springBeanFactory.getBean("userDAO");
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of findOptionElementFromUserAndFamilyCode method, of class OptionElementDAOImpl.
     */
    public void testFindOptionElementFromUserAndFamilyCode() {
        User user1 = userDAO.findUserFromEmail("test1@test.com");
        User user2 = userDAO.findUserFromEmail("test2@test.com");
        User user3 = userDAO.findUserFromEmail("test3@test.com");
        Collection<OptionElement> oec = optionElementDAO.findOptionElementFromUserAndFamilyCode(user1, "USER_OPTION");
        assertEquals(2, oec.size());
        oec = optionElementDAO.findOptionElementFromUserAndFamilyCode(user1, "ANOTHER_OPTION");
        assertTrue(oec.isEmpty());
        oec = optionElementDAO.findOptionElementFromUserAndFamilyCode(user2, "USER_OPTION");
        assertEquals(1, oec.size());
        oec = optionElementDAO.findOptionElementFromUserAndFamilyCode(user2, "ANOTHER_OPTION");
        assertTrue(oec.isEmpty());
        oec = optionElementDAO.findOptionElementFromUserAndFamilyCode(user3, "USER_OPTION");
        assertTrue(oec.isEmpty());
        oec = optionElementDAO.findOptionElementFromUserAndFamilyCode(user3, "ANOTHER_OPTION");
        assertTrue(oec.isEmpty());
    }
}
