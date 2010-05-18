/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import junit.framework.TestCase;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.DownloaderImpl;
import org.opens.tanaguru.crawler.processor.TanaguruWriterProcessor;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.reference.TestFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.ConsolidatorService;
import org.opens.tanaguru.service.ContentAdapterService;
import org.opens.tanaguru.service.ContentLoaderService;
import org.opens.tanaguru.service.ProcessorService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author lralambomanana
 */
public abstract class AbstractRuleImplementationTestCase extends TestCase {

    private String applicationContextFilePath =
            "../tanaguru-testing-tools/src/main/resources/application-context.xml";
    private BeanFactory springBeanFactory;
    private TestFactory testFactory;
    private ContentLoaderService contentLoaderService;
    private ContentAdapterService contentAdapterService;
    private ProcessorService processorService;
    private ConsolidatorService consolidatorService;
    private URLIdentifier urlIdentifier;
    private Map<WebResource, List<Content>> contentMap = new HashMap<WebResource, List<Content>>();
    protected Map<WebResource, List<String>> relatedContentMap = new HashMap<WebResource, List<String>>();
    private List<Test> testList = new ArrayList<Test>();
    private Map<WebResource, List<ProcessResult>> grossResultMap = new HashMap<WebResource, List<ProcessResult>>();
    private Map<WebResource, ProcessResult> netResultMap = new HashMap<WebResource, ProcessResult>();
    protected WebResourceFactory webResourceFactory;
    protected ContentFactory contentFactory;
    protected String ruleImplementationClassName;
    protected Map<String, WebResource> webResourceMap = new HashMap<String, WebResource>();
    protected String testcasesFilePath = "../tanaguru-testing-tools/resources/testcases/";

    public AbstractRuleImplementationTestCase(String testName) {
        super(testName);
        initialize();
        setUpRuleImplementationClassName();
        setUpWebResourceMap();
        setUpClass();
    }

    private void initialize() {
        initializePath();
        springBeanFactory = new FileSystemXmlApplicationContext(applicationContextFilePath);
        webResourceFactory = (WebResourceFactory) springBeanFactory.getBean("webResourceFactory");
        contentFactory = (ContentFactory) springBeanFactory.getBean("contentFactory");
        urlIdentifier = (URLIdentifier) springBeanFactory.getBean("urlIdentifierComponent");
        testFactory = (TestFactory) springBeanFactory.getBean("testFactory");

        contentLoaderService = (ContentLoaderService) springBeanFactory.getBean("contentLoaderService");
        contentAdapterService = (ContentAdapterService) springBeanFactory.getBean("contentAdapterService");

        processorService = (ProcessorService) springBeanFactory.getBean("processorService");

        consolidatorService = (ConsolidatorService) springBeanFactory.getBean("consolidatorService");

    }

    /**
     * In this method, set the class name of the rule implementation to test.
     */
    protected abstract void setUpRuleImplementationClassName();

    /**
     * In this method, set the web resource list to test.
     */
    protected abstract void setUpWebResourceMap();

    private void setUpClass() {
        Test test = testFactory.create();
        test.setCode(this.getName());
        test.setRuleClassName(ruleImplementationClassName);
        testList.add(test);
        URL src = null;
        for (WebResource webResource : webResourceMap.values()) {
            contentMap.put(webResource, contentLoaderService.loadContent(webResource));
            if (relatedContentMap.get(webResource) !=null) {
                for(String contentUrl : relatedContentMap.get(webResource)) {
                    if (contentMap.get(webResource).get(0) instanceof SSP){
                        SSP ssp = (SSP)contentMap.get(webResource).get(0);
                        try {
                            src = new URL(ssp.getURI());
                            urlIdentifier.setUrl(src);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(AbstractRuleImplementationTestCase.class.getName()).log(Level.SEVERE, null, ex);
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
            contentMap.put(webResource, contentAdapterService.adaptContent(contentMap.get(webResource)));
        }
    }

    protected List<ProcessResult> process(String webResourceKey) {
        System.out.println(this + "::process(\"" + webResourceKey + "\")");
        WebResource webResource = webResourceMap.get(webResourceKey);
        List<ProcessResult> grossResultList = processorService.process(contentMap.get(webResource), testList);
        grossResultMap.put(webResource, grossResultList);
        return grossResultList;
    }

    protected ProcessResult processPageTest(String webResourceKey) {
        return process(webResourceKey).get(0);
    }

    public ProcessResult consolidate(String webResourceKey) {
        System.out.println(this + "::consolidate(\"" + webResourceKey + "\")");
        WebResource webResource = webResourceMap.get(webResourceKey);
        ProcessResult netResult = consolidatorService.consolidate(grossResultMap.get(webResource), testList).get(0);
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
        List<ProcessResult> grossResultList = grossResultMap.get(site);
        Page page = (Page) webResourceMap.get(pageKey);
        for (ProcessResult grossResult : grossResultList) {
            if (grossResult.getSubject().equals(page)) {
                return grossResult;
            }
        }
        return null;
    }

    private void initializePath(){
        testcasesFilePath = 
                "file://"+System.getenv("PWD")+"/"+testcasesFilePath;
        applicationContextFilePath =
                "file://"+System.getenv("PWD")+"/"+applicationContextFilePath;
    }

    private byte[] getBinaryImage(String imgUrl){
       ByteArrayOutputStream baos = new ByteArrayOutputStream( 1000 );
       URL url = null;
        try {
            url = new URL(imgUrl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AbstractRuleImplementationTestCase.class.getName()).log(Level.SEVERE, null, ex);
        }
       BufferedImage image = null;
       byte[] resultImageAsRawBytes = null;
       try {
           image = ImageIO.read(url);
           // W R I T E
           ImageIO.write(image, getImageExtension(imgUrl), baos);
           // C L O S E
           baos.flush();
           resultImageAsRawBytes = baos.toByteArray();
           baos.close();
       } catch (IOException ex) {
           Logger.getLogger(TanaguruWriterProcessor.class.getName()).log(Level.SEVERE, null, ex);
       }
       return resultImageAsRawBytes;
    }

    private String getImageExtension(String imageUrl){
       String ext = imageUrl.substring(imageUrl.lastIndexOf('.')+1);
       java.util.Iterator<ImageWriter> it = ImageIO.getImageWritersBySuffix(ext);
       if (it.next() != null) {
           return ext;
       } else {
           return "jpg";
       }
    }

    private boolean isContentCss (String url){
        String ext = url.substring(url.lastIndexOf('.')+1);
        if (ext.equalsIgnoreCase("css")) {
            return true;
        }
        return false;
    }

    private String getTextContent(String url){
        BufferedReader in = null;
        try {
            StringBuffer urlContent = new StringBuffer();
            String thisLine;
            URL u = new URL(url);
            in = new BufferedReader(new InputStreamReader(u.openStream()));
            while ((thisLine = in.readLine()) != null) {
                //Correction of #34 bug
                urlContent.append(thisLine + "\r");
            }
            return urlContent.toString();
        } catch (IOException ex) {
            Logger.getLogger(DownloaderImpl.class.getName()).log(Level.SEVERE,ex.getMessage());
            return "";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DownloaderImpl.class.getName()).log(Level.SEVERE,ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }
}
