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
package org.opens.tanaguru.rules.test;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.AuditFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.parameterization.ParameterElementFactory;
import org.opens.tanaguru.entity.factory.parameterization.ParameterFactory;
import org.opens.tanaguru.entity.factory.parameterization.ParameterFamilyFactory;
import org.opens.tanaguru.entity.factory.reference.TestFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.ConsolidatorService;
import org.opens.tanaguru.service.ContentAdapterService;
import org.opens.tanaguru.service.ContentLoaderService;
import org.opens.tanaguru.service.ProcessorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lralambomanana
 */
public abstract class AbstractRuleImplementationTestCase extends DBTestCase {

    private static final Logger LOGGER = Logger.getLogger(AbstractRuleImplementationTestCase.class);
    private String applicationContextFilePath = "context/application-context.xml";
    private ApplicationContext applicationContext;
    private TestFactory testFactory;
    private ContentLoaderService contentLoaderService;
    private ContentAdapterService contentAdapterService;
    private ProcessorService processorService;
    private ConsolidatorService consolidatorService;
    public ConsolidatorService getConsolidatorService() {
        return consolidatorService;
    }
    
    private AuditFactory auditFactory;
    private URLIdentifier urlIdentifier;
    private Map<WebResource, List<Content>> contentMap = new HashMap<WebResource, List<Content>>();
    private Map<WebResource, List<String>> relatedContentMap = new HashMap<WebResource, List<String>>();
    public Map<WebResource, List<String>> getRelatedContentMap() {
        return relatedContentMap;
    }
    private List<Test> testList = new ArrayList<Test>();
    public List<Test> getTestList() {
        return testList;
    }
    private Map<WebResource, Collection<ProcessResult>> grossResultMap = new HashMap<WebResource, Collection<ProcessResult>>();
    public Map<WebResource, Collection<ProcessResult>> getGrossResultMap() {
        return grossResultMap;
    }
    
    private Map<WebResource, ProcessResult> netResultMap = new HashMap<WebResource, ProcessResult>();
    private WebResourceFactory webResourceFactory;
    public WebResourceFactory getWebResourceFactory() {
        return webResourceFactory;
    }
    private ContentFactory contentFactory;
    private ParameterFactory parameterFactory;
    private ParameterElementFactory parameterElementFactory;
    private ParameterFamilyFactory parameterFamilyFactory;
    private String ruleImplementationClassName;
    public void setRuleImplementationClassName(String ruleImplementationClassName) {
        this.ruleImplementationClassName = ruleImplementationClassName;
    }
    private Map<String, WebResource> webResourceMap = new HashMap<String, WebResource>();
    public Map<String, WebResource> getWebResourceMap() {
        return webResourceMap;
    }
    private Map<String, Collection<Parameter>> parameterMap = new HashMap<String, Collection<Parameter>>();
    public Map<String, Collection<Parameter>> getParameterMap() {
        return parameterMap;
    }
    private String testcasesFilePath = "";
    public String getTestcasesFilePath() {
        return testcasesFilePath;
    }

    /**
     * driver JDBC
     */
    private static final String JDBC_DRIVER =
            "org.hsqldb.jdbcDriver";
    /**
     * base de données HSQLDB nommée "database" qui fonctionne en mode mémoire
     */
    private static final String DATABASE =
            "jdbc:hsqldb:file:src/main/resources/hsql-db";
    /**
     * utilisateur qui se connecte à la base de données
     */
    private static final String USER = "sa";
    /**
     * getDataSet mot de passe pour se connecter à la base de données
     */
    private static final String PASSWORD = "";
    private String inputDataFileName = "";

    public String getInputDataFileName() {
        return inputDataFileName;
    }

    /**
     * The referential of rules
     */
    private boolean upperCaseTags = false;
    public boolean getUpperCaseTags() {
        return upperCaseTags;
    }

    public void setUpperCaseTags(boolean upperCaseTags) {
        this.upperCaseTags = upperCaseTags;
    }
    
    /**
     * 
     * @param testName
     * @param inputDataFileName
     * @param testcasesFilePath
     * @param upperCaseTags
     */
    public AbstractRuleImplementationTestCase(
            String testName,
            String inputDataFileName,
            String testcasesFilePath, 
            boolean upperCaseTags) {
        super(testName);
        this.testcasesFilePath = testcasesFilePath;
        this.inputDataFileName = inputDataFileName;
        this.upperCaseTags = upperCaseTags;
        initialize();
        setUpRuleImplementationClassName();
        setUpWebResourceMap();
        setUpClass();
        setUpParameterMap();
        setUpDatabase();
    }
    
    /**
     * 
     * @param testName
     * @param inputDataFileName
     * @param testcasesFilePath
     */
    public AbstractRuleImplementationTestCase(
            String testName,
            String inputDataFileName,
            String testcasesFilePath) {
        super(testName);
        this.testcasesFilePath = testcasesFilePath;
        this.inputDataFileName = inputDataFileName;
        initialize();
        setUpRuleImplementationClassName();
        setUpWebResourceMap();
        setUpClass();
        setUpParameterMap();
        setUpDatabase();
    }

    private void initialize() {
        initializePath();
        applicationContext = new ClassPathXmlApplicationContext(applicationContextFilePath);

        webResourceFactory = (WebResourceFactory) applicationContext.getBean("webResourceFactory");
        contentFactory = (ContentFactory) applicationContext.getBean("contentFactory");
        parameterFactory = (ParameterFactory) applicationContext.getBean("parameterFactory");
        parameterElementFactory = (ParameterElementFactory) applicationContext.getBean("parameterElementFactory");
        parameterFamilyFactory = (ParameterFamilyFactory) applicationContext.getBean("parameterFamilyFactory");
        auditFactory = (AuditFactory) applicationContext.getBean("auditFactory");

        urlIdentifier = ((URLIdentifierFactory) applicationContext.getBean("urlIdentifierFactory")).create();
        testFactory = (TestFactory) applicationContext.getBean("testFactory");

        contentLoaderService = (ContentLoaderService) applicationContext.getBean("contentLoaderService");
        contentAdapterService = (ContentAdapterService) applicationContext.getBean("contentAdapterService");

        processorService = (ProcessorService) applicationContext.getBean("processorService");

        consolidatorService = (ConsolidatorService) applicationContext.getBean("consolidatorService");
        if (upperCaseTags) {
            HTMLCleanerFactoryImpl htmlCleanerFactory = 
                    (HTMLCleanerFactoryImpl) applicationContext.getBean("htmlCleanerFactory");
            htmlCleanerFactory.setRemoveLowerCaseTags(upperCaseTags);
        }
    }

    private void setUpDatabase() {
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                JDBC_DRIVER);
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                DATABASE);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                USER);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                PASSWORD);
    }

    /**
     * In this method, set the class name of the rule implementation to test.
     */
    protected abstract void setUpRuleImplementationClassName();

    /**
     * In this method, set the web resource list to test.
     */
    protected abstract void setUpWebResourceMap();

    /**
     * In this method, set the parameters associated with the each webresource.
     */
    protected void setUpParameterMap() {
        associateParameterToSSP();
    }

    /**
     * 
     */
    private void setUpClass() {
        Test test = testFactory.create();
        test.setCode(this.getName());
        test.setRuleClassName(ruleImplementationClassName);
        test.setRuleArchiveName("MockArchiveName");
        testList.add(test);
        URL src = null;
        LOGGER.info("setUpClass()");
        for (WebResource webResource : webResourceMap.values()) {
            LOGGER.info("webResource.getURL() " + webResource.getURL());
            contentMap.put(webResource, contentLoaderService.loadContent(webResource));
            
            if (relatedContentMap.get(webResource) != null) {
                for (String contentUrl : relatedContentMap.get(webResource)) {
                    if (contentMap.get(webResource).get(0) instanceof SSP) {
                        SSP ssp = (SSP) contentMap.get(webResource).get(0);
                        try {
                            src = new URL(ssp.getURI());
                            urlIdentifier.setUrl(src);
                        } catch (MalformedURLException ex) {
                            LOGGER.error(ex);
                        }
                        urlIdentifier.setUrl(src);
                        String relatedContentUrl =
                                urlIdentifier.resolve(contentUrl).toExternalForm();
                        if (isContentCss(relatedContentUrl)) {
                            ssp.addRelatedContent(
                                    contentFactory.createStylesheetContent(
                                    new Date(),
                                    relatedContentUrl,
                                    ssp,
                                    getTextContent(relatedContentUrl),
                                    200));
                        } else {
                            ssp.addRelatedContent(
                                    contentFactory.createImageContent(
                                    new Date(),
                                    relatedContentUrl,
                                    ssp,
                                    getBinaryImage(relatedContentUrl),
                                    200));
                        }
                    }
                }
            }
            contentMap.put(webResource, (List<Content>)contentAdapterService.adaptContent((contentMap.get(webResource))));
        }
    }

    protected Collection<ProcessResult> process(String webResourceKey) {
        System.out.println(this + "::process(\"" + webResourceKey + "\")");
        WebResource webResource = webResourceMap.get(webResourceKey);
        Collection<ProcessResult> grossResultList = processorService.process(contentMap.get(webResource), testList);
        grossResultMap.put(webResource, grossResultList);
        return grossResultList;
    }

    protected ProcessResult processPageTest(String webResourceKey) {
        return process(webResourceKey).iterator().next();
    }

    public ProcessResult consolidate(String webResourceKey) {
        System.out.println(this + "::consolidate(\"" + webResourceKey + "\")");
        WebResource webResource = webResourceMap.get(webResourceKey);
        ProcessResult netResult = consolidatorService.consolidate(grossResultMap.get(webResource), testList).iterator().next();
        netResultMap.put(webResource, netResult);
        return netResult;
    }

    public void testRuleImplementation() {
        setProcess();
        setConsolidate();
    }

    protected abstract void setProcess();

    protected abstract void setConsolidate();

    protected ProcessResult getGrossResult(String pageKey, String siteKey) {
        Site site = (Site) webResourceMap.get(siteKey);
        Collection<ProcessResult> grossResultList = grossResultMap.get(site);
        Page page = (Page) webResourceMap.get(pageKey);
        for (ProcessResult grossResult : grossResultList) {
            if (grossResult.getSubject().equals(page)) {
                return grossResult;
            }
        }
        return null;
    }
    
    private void initializePath() {
        testcasesFilePath =
                "file://" + System.getenv("PWD") + "/" + testcasesFilePath;
        testcasesFilePath = FilenameUtils.normalize(testcasesFilePath);
    }

    private byte[] getBinaryImage(String imgUrl) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        URL url = null;
        try {
            url = new URL(imgUrl);
        } catch (MalformedURLException ex) {
            LOGGER.error(ex);
        }
        byte[] resultImageAsRawBytes = null;
        try {
            BufferedImage image = ImageIO.read(url);
            // W R I T E
            ImageIO.write(image, getImageExtension(imgUrl), baos);
            // C L O S E
            baos.flush();
            resultImageAsRawBytes = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        return resultImageAsRawBytes;
    }

    private String getImageExtension(String imageUrl) {
        String ext = imageUrl.substring(imageUrl.lastIndexOf('.') + 1);
        java.util.Iterator<ImageWriter> it = ImageIO.getImageWritersBySuffix(ext);
        if (it.next() != null) {
            return ext;
        } else {
            return "jpg";
        }
    }

    private boolean isContentCss(String url) {
        String ext = url.substring(url.lastIndexOf('.') + 1);
        if (ext.equalsIgnoreCase("css")) {
            return true;
        }
        return false;
    }

    private String getTextContent(String url) {
        BufferedReader in = null;
        try {
            StringBuilder urlContent = new StringBuilder();
            String thisLine;
            URL u = new URL(url);
            in = new BufferedReader(new InputStreamReader(u.openStream()));
            while ((thisLine = in.readLine()) != null) {
                //Correction of #34 bug
                urlContent.append(thisLine);
                urlContent.append("\r");
            }
            return urlContent.toString();
        } catch (IOException ex) {
            LOGGER.error(ex);
            return "";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                LOGGER.error(ex);
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Override method to set custom properties/features {@inheritDoc}
     */
    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        super.setUpDatabaseConfig(config);
        config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE, Integer.valueOf(97));
    }

    /**
     * Charge le jeu de données à partir d'un fichier XML d'import
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        if (!getInputDataFileName().isEmpty()) {
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            IDataSet dataSet = builder.build(new File(
                    getInputDataFileName()));
            return dataSet;
        } else {
            return null;
        }
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    /**
     * 
     * @param familyValue
     * @param elementValue
     * @param value
     * @return 
     */
    protected Parameter createParameter(String familyValue, String elementValue, String value) {
        ParameterFamily parameterFamily = parameterFamilyFactory.create();
        parameterFamily.setParameterFamilyCode(familyValue);
        ParameterElement parameterElement = parameterElementFactory.create();
        parameterElement.setParameterFamily(parameterFamily);
        parameterElement.setParameterElementCode(elementValue);
        Parameter parameter = parameterFactory.create();
        parameter.setValue(value);
        parameter.setParameterElement(parameterElement);
        return parameter;
    }
    
    /**
     * 
     * @param sspIdentifier
     * @param parameter
     * @return 
     */
    protected void addParameterToParameterMap(String sspIdentifier, Parameter parameter) {
        if (parameterMap.containsKey(sspIdentifier)) {
            parameterMap.get(sspIdentifier).add(parameter);
        } else {
            Collection<Parameter> params = new ArrayList<Parameter>();
            params.add(parameter);
            parameterMap.put(sspIdentifier, params);
        }
    }

    /**
     * 
     */
    private void associateParameterToSSP() {
        for (Map.Entry<String, WebResource> entry : webResourceMap.entrySet()) {
            Audit audit = auditFactory.create();
            if (parameterMap.containsKey(entry.getKey())) {
                for (Parameter param : parameterMap.get(entry.getKey())) {
                    audit.addParameter(param);
                }
            }
            if (contentMap.containsKey(entry.getValue()) && !contentMap.get(entry.getValue()).isEmpty()) {
                Content content = contentMap.get(entry.getValue()).iterator().next();
                if (content instanceof SSP) {
                    ((SSP)content).setAudit(audit);
                }
            }
        }
    }
    
    @Override
    public String getName(){
        int lastPointIndex = StringUtils.lastIndexOf(ruleImplementationClassName, ".")+1;
        String className = StringUtils.substring(ruleImplementationClassName, lastPointIndex);
        return StringUtils.replace(className, "Rule", "-");
    }

}