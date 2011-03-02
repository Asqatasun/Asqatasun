/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.entity.dao.test;

import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
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
            "com.mysql.jdbc.Driver";

    private static final String SPRING_FILE_PATH =
            "../persistence/src/main/resources/conf/context/web-app/application-context.xml";

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
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        return builder.build(new FileInputStream(
                getInputDataFileName()));
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