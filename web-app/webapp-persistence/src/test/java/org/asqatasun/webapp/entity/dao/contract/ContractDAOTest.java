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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.webapp.entity.dao.user.UserDAO;
import org.asqatasun.webapp.entity.functionality.Functionality;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.asqatasun.webapp.entity.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ContractDAOTest extends AbstractDaoTestCase {

    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private UserDAO userDAO;

    public ContractDAOTest() {
        super();
    }
    
    /**
     * Test of findAllContractsByUser method, of class ContractDAOImpl.
     */
    @Test
    public void testFindAllContractsByUser() {
        User user = userDAO.read(1L);
        assertEquals(2, contractDAO.findAllContractsByUser(user).size());
        user = userDAO.read(2L);
        assertEquals(0, contractDAO.findAllContractsByUser(user).size());
    }

    /**
     * Test of read method, of class ContractDAOImpl.
     */
    @Test
    public void testRead() {
        Contract contract = contractDAO.read(1L);
        assertNotNull(contract);
        assertEquals("http://www.contract1.com/", getUrlFromContractOption(contract));
        assertEquals(Long.valueOf("1"), contract.getUser().getId());
        Set<String> functionalityCodeSet = new HashSet<>();
        for (Functionality functionality : contract.getFunctionalitySet()) {
            functionalityCodeSet.add(functionality.getCode());
        }
        assertTrue(functionalityCodeSet.contains("PAGES_AUDIT"));
        assertTrue(!functionalityCodeSet.contains("SITE_AUDIT"));
        assertEquals(1, contract.getReferentialSet().size());
        assertTrue(contract.getScenarioSet().isEmpty());
        
        contract = contractDAO.read(2L);
        assertNotNull(contract);
        assertEquals("http://www.contract2.com/", getUrlFromContractOption(contract));
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
    @Test
    public void testSaveOrUpdate() {
        Date beginDate = new Date();
        Date endDate = new Date();
        Date renewalDate = new Date();
        int nbOfContract = contractDAO.findAll().size();
        Contract contract = new Contract();
        contract.setLabel("Contract-test");
        contract.setBeginDate(beginDate);
        contract.setEndDate(endDate);
        contract.setRenewalDate(renewalDate);
        contract.setPrice(200f);
        contract.setPrice(200f);
        contract.setUser(null);
        contractDAO.saveOrUpdate(contract);
        assertEquals(nbOfContract+1, contractDAO.findAll().size());
    }

    /**
     * Test of update method, of class ContractDAOImpl.
     */
    @Test
    public void testUpdate() {
        Contract contract  = contractDAO.read((1L));
        Float contractPrice = contract.getPrice();
        contract.setPrice(contractPrice + 200);
        contractDAO.update(contract);
        assertEquals(contractPrice+200, contractDAO.read((1L)).getPrice());
    }

    private String getUrlFromContractOption(Contract contract) {
        return contractDAO.read(contract.getId())
            .getOptionElementSet()
            .stream()
            .filter(optionElement -> StringUtils.equals("DOMAIN", optionElement.getOption().getCode()))
            .findFirst().map(OptionElement::getValue).orElse("");
    }
}
