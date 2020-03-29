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
package org.asqatasun.webapp.entity.dao.contract;

import org.asqatasun.webapp.entity.contract.Act;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.dao.test.AbstractDaoTestCase;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ActDAOTest extends AbstractDaoTestCase {

    @Autowired
    private ActDAO actDAO;
    @Autowired
    private ContractDAO contractDAO;

    public ActDAOTest() {
        super();
    }

    /**
     * Test of findAllContractsByUser method, of class ContractDAOImpl.
     */
    @Test
    public void testFindLastActsByContract() {
        System.out.println("findLastActsByContract");
        Contract contract  = contractDAO.read((1L));
        assertEquals(1, actDAO.findActsByContract(contract, 1, 2, null, false).size());
        assertEquals(Long.valueOf(7), actDAO.findActsByContract(contract, 1, 2, null, false).iterator().next().getId());
        Collection<Act> actSet = actDAO.findActsByContract(contract, 3, 2, null, false);
        Iterator<Act> iter = actSet.iterator();
        Date currentDate = null;
        while (iter.hasNext()) {
            Act act= iter.next();
            if (currentDate != null) {
                assertTrue(act.getBeginDate().before(currentDate));
            }
            currentDate = act.getBeginDate();
        }
        contract  = contractDAO.read((2L));
        assertEquals(0, actDAO.findActsByContract(contract, 1, 2, null, false).size());
    }

    /**
     * Test of findAllContractsByUser method, of class ContractDAOImpl.
     */
    @Test
    public void testFindAllActsByContract() {
        System.out.println("findAllActsByContract");
        Contract contract  = contractDAO.read((1L));
        assertEquals(4, actDAO.findAllActsByContract(contract).size());
        contract  = contractDAO.read((2L));
        assertEquals(3, actDAO.findAllActsByContract(contract).size());
    }

    /**
     * Test of findRunningActsByContract method, of class ContractDAOImpl.
     */
    @Test
    public void testFindRunningActsByContract() {
        System.out.println("findRunningActsByContract");
        Contract contract  = contractDAO.read((1L));
        assertEquals(1, actDAO.findRunningActsByContract(contract).size());
        assertEquals(Long.valueOf(3), actDAO.findRunningActsByContract(contract).iterator().next().getId());
        contract  = contractDAO.read((2L));
        assertEquals(3, actDAO.findRunningActsByContract(contract).size());
    }

    @Test
    public void testFindNumberOfActByPeriodAndIp() {
        Contract contract  = contractDAO.read((1L));
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date limitDate = null;
        try {
            limitDate = dfm.parse("2011-01-01 00:00:00");
        } catch (ParseException ex) {
            Logger.getLogger(ActDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(2, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.1"));
        assertEquals(0, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.2"));
        try {
            limitDate = dfm.parse("2011-01-01 01:00:00");
        } catch (ParseException ex) {
            Logger.getLogger(ActDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(1, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.1"));
        assertEquals(0, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.2"));
    }

}
