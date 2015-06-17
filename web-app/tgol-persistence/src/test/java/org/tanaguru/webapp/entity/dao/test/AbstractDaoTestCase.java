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
package org.tanaguru.webapp.entity.dao.test;

import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author jkowalczyk
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
    /**
     * base de données HSQLDB nommée "database" qui fonctionne en mode mémoire
     */
    private static final String DATABASE =
            "jdbc:hsqldb:file:src/test/resources/hsql-db";
    /**
     * utilisateur qui se connecte à la base de données
     */
    private static final String USER = "sa";
    /**
     * getDataSet mot de passe pour se connecter à la base de données
     */
    private static final String PASSWORD = "";


    private static final String SPRING_FILE_PATH =
            "src/test/resources/conf/context/application-context.xml";

    private String inputDataFileName = "";
    public String getInputDataFileName() {
        return inputDataFileName;
    }

    public void setInputDataFileName(String inputDataFileName) {
        this.inputDataFileName = inputDataFileName;
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
     */
    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        super.setUpDatabaseConfig(config);
        config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE, new Integer(97));
    }

    /**
     * Charge le jeu de données à partir d'un fichier XML d'import
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
        FlatXmlDataSet loadedDataSet = flatXmlDataSetBuilder.build(new FileInputStream(
                getInputDataFileName()));
        return loadedDataSet;

    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

}
