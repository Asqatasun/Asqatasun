package com.oceaneconsulting.tanaguru;

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

public abstract class AbstractDbUnitTest extends DBTestCase  {
	

    /**
     * Logger.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDbUnitTest.class);
    /**
     * driver JDBC
     */
    private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    /**
     * base de données HSQLDB nommée "database" qui fonctionne en mode mémoire
     */
    private static final String DATABASE = "jdbc:hsqldb:file:src/test/resources/hsql-db";
    /**
     * utilisateur qui se connecte à la base de données
     */
    private static final String USER = "sa";
    /**
     * getDataSet mot de passe pour se connecter à la base de données
     */
    private static final String PASSWORD = "";
    
    /**
     * Context d'application spring
     */
    private static final String SPRING_FILE_PATH = "classpath*:test-beans-ws.xml";
    
    private String inputDataFileName = "";
    
    /**
     * Récupération du fichier de données pour le test
     * @return Le chemin du fichier de données du test
     */
    public String getInputDataFileName() {
        return inputDataFileName;
    }

    /**
     * Setter de fichier de données pour le test
     * @param inputDataFileName Le chemin du fichier de données pour le test
     */
    public void setInputDataFileName(String inputDataFileName) {
        this.inputDataFileName = inputDataFileName;
    }

    /**
     * La fabrique de spring
     */
    protected BeanFactory springBeanFactory;

    /**
     * Constructeur 
     * @param testName Le nom du test
     */
    public AbstractDbUnitTest(String testName) {
        super(testName);
        ApplicationContext springApplicationContext = new FileSystemXmlApplicationContext(SPRING_FILE_PATH);
        springBeanFactory = springApplicationContext;
        DriverManagerDataSource dmds =(DriverManagerDataSource)springBeanFactory.getBean("dataSource");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,JDBC_DRIVER);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,dmds.getUrl());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,dmds.getUsername());
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,dmds.getPassword());
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
        FlatXmlDataSet loadedDataSet = flatXmlDataSetBuilder.build(new FileInputStream(getInputDataFileName()));
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
