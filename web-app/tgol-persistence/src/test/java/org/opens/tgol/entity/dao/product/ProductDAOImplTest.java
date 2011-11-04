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
package org.opens.tgol.entity.dao.product;

import org.opens.tgol.entity.dao.contract.ContractDAO;
import org.opens.tgol.entity.dao.test.AbstractDaoTestCase;
import org.opens.tgol.entity.dao.user.UserDAO;
import org.opens.tgol.entity.factory.contract.ContractFactory;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.product.ScopeEnum;

/**
 *
 * @author jkowalczyk
 */
public class ProductDAOImplTest extends AbstractDaoTestCase {

    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "src/test/resources/dataSets/flatXmlDataSet.xml";

    private ContractDAO contractDAO;
    private UserDAO userDAO;
    private ProductDAO productDAO;
    private ContractFactory contractFactory;

    public ProductDAOImplTest(String testName) {
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

    public void testRead() {
        Product product = productDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(1), product.getId());
        assertEquals(1, product.getContractSet().size());
        assertEquals(ScopeEnum.DOMAIN, product.getScope().getCode());
    }

}