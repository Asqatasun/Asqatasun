/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.dao.option;

import java.util.Collection;
import org.asqatasun.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.entity.dao.user.UserDAO;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class OptionImplElementDAOTest extends AbstractDaoTestCase {

    private static final String INPUT_DATA_SET_FILENAME = "flatXmlDataSet.xml";
    @Autowired
    private OptionElementDAO optionElementDAO;
    @Autowired
    private UserDAO userDAO;
    
    public OptionImplElementDAOTest() {
        super();
    }

    @Override
    protected String getDataSetFilename() {
        return getInputDataFilePath()+INPUT_DATA_SET_FILENAME;
    }

    /**
     * Test of findOptionElementFromUserAndFamilyCode method, of class OptionElementDAOImpl.
     */
    @Test
    public void testFindOptionElementFromUserAndFamilyCode() {
        User user1 = userDAO.findUserFromEmail("test1@test.com");
        User user2 = userDAO.findUserFromEmail("test2@test.com");
        User user3 = userDAO.findUserFromEmail("test3@test.com");
        Collection<OptionElementImpl> oec = optionElementDAO.findOptionElementFromUserAndFamilyCode(user1, "USER_OPTION");
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
