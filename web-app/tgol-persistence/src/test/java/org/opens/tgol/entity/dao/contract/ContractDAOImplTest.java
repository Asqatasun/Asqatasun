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
package org.opens.tgol.entity.dao.contract;

import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.dao.product.ProductDAO;
import org.opens.tgol.entity.dao.test.AbstractDaoTestCase;
import org.opens.tgol.entity.dao.user.UserDAO;
import org.opens.tgol.entity.factory.contract.ContractFactory;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.user.User;
import java.util.Date;

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
    private ProductDAO productDAO;
    private ContractFactory contractFactory;

    public ContractDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(INPUT_DATA_SET_FILENAME);
        contractDAO = (ContractDAO)
                springBeanFactory.getBean("contractDAO");
        userDAO = (UserDAO)
                springBeanFactory.getBean("userDAO");
        productDAO = (ProductDAO)
                springBeanFactory.getBean("productDAO");
        contractFactory = (ContractFactory)
                springBeanFactory.getBean("contractFactory");
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
        assertEquals("http://www.contract2.com/", contract.getUrl());
        assertEquals("Test1", contract.getUser().getName());
        assertEquals("AuditAxs", contract.getProduct().getLabel());
        contract = contractDAO.read(Long.valueOf(3));
        assertNull(contract);
    }

    /**
     * Test of findAllContractsByUser method, of class ContractDAOImpl.
     */
    public void testFindAllContractsByProduct() {
        System.out.println("findAllContractsByProduct");
        Product product = productDAO.read(Long.valueOf(1));
        assertEquals(1, contractDAO.findAllContractsByProduct(product).size());
        assertEquals("http://www.contract1.com/",
                contractDAO.findAllContractsByProduct(product).iterator().next().getUrl());
        product = productDAO.read(Long.valueOf(2));
        assertEquals(1, contractDAO.findAllContractsByProduct(product).size());
        assertEquals("http://www.contract2.com/",
                contractDAO.findAllContractsByProduct(product).iterator().next().getUrl());
    }

    /**
     * Test of saveOrUpdate method, of class ContractDAOImpl.
     */
    public void testSaveOrUpdate() {
        System.out.println("saveOrUpdate");
        Date beginDate = new Date();
        Date endDate = new Date();
        int nbOfContract = contractDAO.findAll().size();
        Contract contract = contractFactory.createContract(
                "Contract-test",
                beginDate,
                endDate,
                null,
                Float.valueOf(200),
                null,
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