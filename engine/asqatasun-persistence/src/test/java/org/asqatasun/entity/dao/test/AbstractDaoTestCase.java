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
package org.asqatasun.entity.dao.test;

import org.asqatasun.entity.dao.config.PersistenceConfigTest;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileInputStream;

/**
 *
 * @author koj
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = {PersistenceConfigTest.class})
public abstract class AbstractDaoTestCase extends DataSourceBasedDBTestCase {

    /**
     * Logger.
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractDaoTestCase.class);


    private final String inputDataFilePath = "src/test/resources/dataSets/";
    public String getInputDataFilePath() {
        return inputDataFilePath;
    }

    @Autowired
    private DataSource dataSource;

    DatabaseOperation teardownOperationValue = DatabaseOperation.DELETE;
    public void setTeardownOperationValue(DatabaseOperation teardownOperationValue) {
        this.teardownOperationValue = teardownOperationValue;
    }

    @Override
    @BeforeTransaction
    public void setUp() throws Exception {
        LOGGER.info("Loading dataset {} " +  getDataSetFilename());
        super.setUp();
    }

    @Override
    @AfterTransaction
    public void tearDown() throws Exception {
        super.tearDown();
    }

    protected abstract String getDataSetFilename() throws Exception;

    public AbstractDaoTestCase() {
        super();
    }

    @Override
    protected DataSource getDataSource() {
        return this.dataSource;
    }

    /**
     * Override method to set custom properties/features {@inheritDoc}
     * @param config
     */
    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        super.setUpDatabaseConfig(config);
        config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE, new Integer(97));
    }

    /**
     * Charge le jeu de données à partir d'un fichier XML d'import
     * @return 
     * @throws java.lang.Exception 
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        ReplacementDataSet dataSet = new ReplacementDataSet(builder.build(new FileInputStream(
                getDataSetFilename())));
        dataSet.addReplacementObject("[NULL]", null);
        return dataSet;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return teardownOperationValue;
    }

}
