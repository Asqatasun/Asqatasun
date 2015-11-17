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

import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author lralambomanana
 */
public abstract class AbstractDaoTestCase extends DBTestCase {

    /**
     * Logger.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDaoTestCase.class);
    /**
     * driver JDBC
     */
    private static final String JDBC_DRIVER =
            "org.hsqldb.jdbcDriver";

    private static final String SPRING_FILE_PATH =
            "src/test/resources/conf/context/unit-test-context.xml";

    private final String inputDataFilePath = "src/test/resources/dataSets/";
    public String getInputDataFilePath() {
        return inputDataFilePath;
    }

    private String inputDataFileName = "";
    public String getInputDataFileName() {
        return inputDataFileName;
    }

    public void setInputDataFileName(String inputDataFileName) {
        this.inputDataFileName = inputDataFileName;
    }

    private DatabaseOperation setUpOperationValue = DatabaseOperation.CLEAN_INSERT;
    public DatabaseOperation getSetUpOperationValue() {
        return setUpOperationValue;
    }
    
    public void setSetUpOperationValue(DatabaseOperation setUpOperationValue) {
        this.setUpOperationValue = setUpOperationValue;
    }
    
    private DatabaseOperation teardownOperationValue = DatabaseOperation.NONE;
    public DatabaseOperation getTeardownOperationValue() {
        return teardownOperationValue;
    }

    public void setTeardownOperationValue(DatabaseOperation teardownOperationValue) {
        this.teardownOperationValue = teardownOperationValue;
    }
    
    protected BeanFactory springBeanFactory;

    public AbstractDaoTestCase(String testName) {
        super(testName);
        ApplicationContext springApplicationContext =
                new FileSystemXmlApplicationContext(SPRING_FILE_PATH);
        springBeanFactory = springApplicationContext;
        DriverManagerDataSource dmds =
                (DriverManagerDataSource)springBeanFactory.getBean("dataSource");
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                JDBC_DRIVER);
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                dmds.getUrl());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                dmds.getUsername());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                dmds.getPassword());
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
                getInputDataFileName())));
        dataSet.addReplacementObject("[NULL]", null);
        return dataSet;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return setUpOperationValue;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return teardownOperationValue;
    }

}