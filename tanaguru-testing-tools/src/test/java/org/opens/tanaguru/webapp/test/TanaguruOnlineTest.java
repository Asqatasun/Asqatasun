package org.opens.tanaguru.webapp.test;

import com.thoughtworks.selenium.SeleneseTestCase;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 * This class uses Selenium to automatically test tanaguru in web 
 * application mode. 
 * The selenium server has to be started and the skip property set to true for
 * the maven-surefire-plugin in the pom.xml file.
 * The test.properties file defines the path of the file describing the list
 * of urls to use for the test
 * @author jkowalczyk
 */
public class TanaguruOnlineTest extends SeleneseTestCase {

    private String propertyFile = "/src/test/resources/test.properties";
    protected String listFilePath;
    protected String resultFilePath ;
    protected String pwdPath ;
    private Map<String, String> urlMap = new HashMap<String, String>();
    protected String url;
    Set<String> errorWhileLoading = new HashSet<String>();
    Set<String> errorWhileTesting = new HashSet<String>();
    private final String filePrefix = "file:";

    public TanaguruOnlineTest(){
        pwdPath = System.getenv("PWD");
        initialize();
    }

    @Override
    public void setUp() throws Exception {
        setUp("http://www.tanaguru-online.com:8180/", "*firefox");
    }

    public void testNew() throws Exception {
        Set<String> pageSet = urlMap.keySet();
        Iterator<String> iter = pageSet.iterator();
        String page = null;
        int compteur=0;
        StringBuilder sb  = new StringBuilder();
        while (iter.hasNext()) {
            page = iter.next();
            selenium.open("/tanaguru-web-app");
            selenium.waitForPageToLoad("3000");
            selenium.type("pageUrl", urlMap.get(page));
            selenium.click("input-submit");
            selenium.waitForPageToLoad("240000");
            if (compteur %10 == 0) {
                System.out.println("Testing page  " + urlMap.get(page) + " n° " + compteur);
            }
            if (!selenium.isTextPresent("Open-S"))
                System.out.println("Crash on page "+ urlMap.get(page));

            if (selenium.isTextPresent("while loading page content")) {
                errorWhileLoading.add(urlMap.get(page));
            }  else if (selenium.isTextPresent("while processing page")) {
                errorWhileTesting.add(urlMap.get(page));
            }
            sb.append(selenium.getBodyText());
            compteur++;
        }

        writeResultInFile("campaign", sb.toString());
        System.out.println(compteur + " pages tested");

        for (String string : errorWhileLoading) {
            System.out.println("Error while Loading page "+ string);
        }

        for (String string : errorWhileTesting) {
            System.out.println("Error while Processing page "+ string);
        }
    }

    private void initialize(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(pwdPath+propertyFile));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(
                    TanaguruOnlineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (prop.getProperty("file_path").startsWith(filePrefix)) {
            listFilePath = prop.getProperty("file_path");
        } else {
            listFilePath = filePrefix+pwdPath+"/"+prop.getProperty("file_path");
        }
        resultFilePath = prop.getProperty("result_path");
        getUrlListFromFile();
    }

    private void writeResultInFile(String fileName, String content){
        try {
            FileWriter fw = new FileWriter(resultFilePath + fileName
                    + '-' + new Date().getTime() + ".txt");
            fw.write(content);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(
                    TanaguruOnlineTest.class.getName()).warn(ex.getMessage());
        }
    }

    private void getUrlListFromFile(){
        BufferedReader in = null;
        try {
            String thisLine;
            URL u = new URL(listFilePath);
            in = new BufferedReader(new InputStreamReader(u.openStream()));
            while ((thisLine = in.readLine()) != null) {
                String[] urls= thisLine.split(";");
                urlMap.put(urls[0], urls[1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(TanaguruOnlineTest.class.getName()).warn(ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(TanaguruOnlineTest.class.getName()).warn(ex);
                throw new RuntimeException(ex);
            }
        }
    }

}
