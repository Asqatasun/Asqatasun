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
package org.tanaguru.webapp.entity.dao.contract;

import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.dao.test.AbstractDaoTestCase;
import org.tanaguru.webapp.entity.factory.contract.ActFactory;
import org.tanaguru.webapp.entity.factory.contract.ContractFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tanaguru.entity.dao.subject.WebResourceDAO;
import org.tanaguru.entity.factory.subject.WebResourceFactory;

/**
 *
 * @author jkowalczyk
 */
public class ActDAOImplTest extends AbstractDaoTestCase {

    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";

    private final ActDAO actDAO;
    private final ActFactory actFactory;
    private final ContractFactory contractFactory;
    private final ContractDAO contractDAO;
    private final ScopeDAO scopeDAO;
    private final WebResourceFactory wrFactory;
    private final WebResourceDAO wrDAO;

    public ActDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        actDAO = (ActDAO)
                springBeanFactory.getBean("actDAO");
        contractDAO = (ContractDAO)
                springBeanFactory.getBean("contractDAO");
        scopeDAO = (ScopeDAO)
                springBeanFactory.getBean("scopeDAO");
        actFactory = (ActFactory)
                springBeanFactory.getBean("actFactory");
        contractFactory = (ContractFactory)
                springBeanFactory.getBean("contractFactory");
        wrDAO = (WebResourceDAO)
                springBeanFactory.getBean("webResourceDAO");
        wrFactory = (WebResourceFactory)
                springBeanFactory.getBean("webResourceFactory");
    }

    /**
     * Test of read method, of class ActDAOImpl.
     */
    public void testRead() {
//        System.out.println("read");
//        Act act = actDAO.read(Long.valueOf(1));
//        assertNotNull(act);
//        WebResource webresource = act.getWebResource();
//        assertNotNull(webresource);
//        assertEquals(Long.valueOf(1), webresource.getId());
//        assertEquals("http://www.open-s.com/", webresource.getURL());
//        assertEquals(Float.valueOf(50), webresource.getMark());
//        Contract contract = contractFactory.create();
//        contract.setLabel("Contract-test");
//        contractDAO.saveOrUpdate(contract);
//        Date date = new Date();
//        Act act2 = actFactory.createAct(date, contract);
//        Page page = wrFactory.createPage("http://www.url-test.org");
//        wrDAO.saveOrUpdate(page);
//        act2.setWebResource(page);
//        act2.setScope(scopeDAO.read(Long.valueOf(1)));
//        actDAO.saveOrUpdate(act2);
//        Act act3 = actDAO.read(Long.valueOf(7));
//        assertEquals(Long.valueOf(7), act3.getId());
//        assertEquals(Long.valueOf(4), act3.getWebResource().getId());
//        actDAO.delete(Long.valueOf(7));
//        wrDAO.delete(Long.valueOf(4));
    }

    /**
     * Test of findAllContractsByUser method, of class ContractDAOImpl.
     */
    public void testFindLastActsByContract() {
        System.out.println("findLastActsByContract");
        Contract contract  = contractDAO.read((Long.valueOf(1)));
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
        contract  = contractDAO.read((Long.valueOf(2)));
        assertEquals(0, actDAO.findActsByContract(contract, 1, 2, null, false).size());
    }

    /**
     * Test of findAllContractsByUser method, of class ContractDAOImpl.
     */
    public void testFindAllActsByContract() {
        System.out.println("findAllActsByContract");
        Contract contract  = contractDAO.read((Long.valueOf(1)));
        assertEquals(4, actDAO.findAllActsByContract(contract).size());
        contract  = contractDAO.read((Long.valueOf(2)));
        assertEquals(3, actDAO.findAllActsByContract(contract).size());
    }

    /**
     * Test of findRunningActsByContract method, of class ContractDAOImpl.
     */
    public void testFindRunningActsByContract() {
        System.out.println("findRunningActsByContract");
        Contract contract  = contractDAO.read((Long.valueOf(1)));
        assertEquals(1, actDAO.findRunningActsByContract(contract).size());
        assertEquals(Long.valueOf(3), actDAO.findRunningActsByContract(contract).iterator().next().getId());
        contract  = contractDAO.read((Long.valueOf(2)));
        assertEquals(3, actDAO.findRunningActsByContract(contract).size());
    }

    public void testFindNumberOfActByPeriodAndIp() {
        Contract contract  = contractDAO.read((Long.valueOf(1)));
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date limitDate = null;
        try {
            limitDate = dfm.parse("2011-01-01 00:00:00");
        } catch (ParseException ex) {
            Logger.getLogger(ActDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(2, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.1"));
        assertEquals(0, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.2"));
        try {
            limitDate = dfm.parse("2011-01-01 01:00:00");
        } catch (ParseException ex) {
            Logger.getLogger(ActDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(1, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.1"));
        assertEquals(0, actDAO.findNumberOfActByPeriodAndIp(contract, limitDate, "127.0.0.2"));
    }

}