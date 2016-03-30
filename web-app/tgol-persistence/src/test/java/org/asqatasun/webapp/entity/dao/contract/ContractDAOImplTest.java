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
package org.asqatasun.webapp.entity.dao.contract;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.webapp.entity.dao.user.UserDAO;
import org.asqatasun.webapp.entity.factory.contract.ContractFactory;
import org.asqatasun.webapp.entity.functionality.Functionality;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.asqatasun.webapp.entity.referential.Referential;
import org.asqatasun.webapp.entity.scenario.Scenario;
import org.asqatasun.webapp.entity.service.contract.ContractDataService;
import org.asqatasun.webapp.entity.service.contract.ContractDataServiceImpl;
import org.asqatasun.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class ContractDAOImplTest extends AbstractDaoTestCase {

    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";

    private ContractDAO contractDAO;
    private UserDAO userDAO;
    private ContractFactory contractFactory;
    private ContractDataService contractDataService;

    public ContractDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        contractDAO = (ContractDAO)
                springBeanFactory.getBean("contractDAO");
        userDAO = (UserDAO)
                springBeanFactory.getBean("userDAO");
        contractFactory = (ContractFactory)
                springBeanFactory.getBean("contractFactory");
        contractDataService = new ContractDataServiceImpl();
        contractDataService.setEntityDao(contractDAO);
    }
    
    /**
     * Test of findAllContractsByUser method, of class ContractDAOImpl.
     */
    public void testFindAllContractsByUser() {
        System.out.println("findAllContractsByUser");
        User user = userDAO.read(Long.valueOf(1));
        assertEquals(2, contractDAO.findAllContractsByUser(user).size());
        user = userDAO.read(Long.valueOf(2));
        assertEquals(0, contractDAO.findAllContractsByUser(user).size());
    }

    /**
     * Test of read method, of class ContractDAOImpl.
     */
    public void testRead() {
        System.out.println("read");
        
        Contract contract = contractDAO.read(Long.valueOf(1));
        assertNotNull(contract);
        assertEquals("http://www.contract1.com/", contractDataService.getUrlFromContractOption(contract));
        assertEquals(Long.valueOf("1"), contract.getUser().getId());
        Set<String> functionalityCodeSet = new HashSet<>();
        for (Functionality functionality : contract.getFunctionalitySet()) {
            functionalityCodeSet.add(functionality.getCode());
        }
        assertTrue(functionalityCodeSet.contains("PAGES_AUDIT"));
        assertTrue(!functionalityCodeSet.contains("SITE_AUDIT"));
        assertEquals(1, contract.getReferentialSet().size());
        assertTrue(contract.getScenarioSet().isEmpty());
        
        contract = contractDAO.read(Long.valueOf(2));
        assertNotNull(contract);
        assertEquals("http://www.contract2.com/", contractDataService.getUrlFromContractOption(contract));
        assertEquals(Long.valueOf("1"), contract.getUser().getId());
        functionalityCodeSet = new HashSet<>();
        for (Functionality functionality : contract.getFunctionalitySet()) {
            functionalityCodeSet.add(functionality.getCode());
        }
        assertTrue(functionalityCodeSet.contains("PAGES_AUDIT"));
        assertTrue(functionalityCodeSet.contains("SITE_AUDIT"));
        assertEquals(2, contract.getReferentialSet().size());
        assertEquals(2, contract.getScenarioSet().size());
        
        contract = contractDAO.read(Long.valueOf(3));
        assertNull(contract);
    }

    /**
     * Test of saveOrUpdate method, of class ContractDAOImpl.
     */
    public void testSaveOrUpdate() {
        System.out.println("saveOrUpdate");
        Date beginDate = new Date();
        Date endDate = new Date();
        Date renewalDate = new Date();
        int nbOfContract = contractDAO.findAll().size();
        Set<Functionality> functionalitySet = new HashSet<>();
        Set<OptionElement> optionElementSet = new HashSet<>();
        Set<Referential> referenceSet = new HashSet<>();
        Set<Scenario> scenarioSet = new HashSet<>();
        Contract contract = contractFactory.createContract(
                "Contract-test",
                beginDate,
                endDate,
                renewalDate,
                Float.valueOf(200),
                functionalitySet,
                optionElementSet,
                referenceSet,
                scenarioSet,
                null);
        contractDAO.saveOrUpdate(contract);
        assertEquals(nbOfContract+1, contractDAO.findAll().size());
    }

    /**
     * Test of update method, of class ContractDAOImpl.
     */
    public void testUpdate() {
        System.out.println("update");
        Contract contract  = contractDAO.read((Long.valueOf(1)));
        Float contractPrice = contract.getPrice();
        contract.setPrice(contractPrice + 200);
        contractDAO.update(contract);
        assertEquals(contractPrice+200, contractDAO.read((Long.valueOf(1))).getPrice());
    }

}