/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.rules.generator.json;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruJsTestCaseExtractor {

    static final String REF_NAME = "rgaa3.0";
    static final String REF_KEY = StringUtils.remove(REF_NAME, ".");
    static final String RULE_KEY = "Rgaa30Rule101301/"; //Rgaa30Rule030301/
    static final String URL_LIST = "http://172.17.42.1/" + RULE_KEY;
    static final String HUB_URL = "http://172.17.0.23:4444/wd/hub";
    static final String JS_SCRIPT_NAME = "js/colorExtractor_nativeJs.js";
    static final String PATH_TO_TG_SRC = "/home/jkowalczyk/Documents/Sources/Tanaguru/";
    static final String PATH_TO_WRITE
            = PATH_TO_TG_SRC
            + "rules/" + REF_NAME + "/src/test/resources/testcases/" + REF_KEY + "/"
            + RULE_KEY;
    static final String PATH_TO_FF = "/opt/firefox/firefox";

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setJavascriptEnabled(true);
//        WebDriver driver = new PhantomJSDriver(caps);
//        FirefoxDriver driver = 
//                new FirefoxDriver(
//                        new FirefoxBinary(new File(PATH_TO_FF)), 
//                        new FirefoxProfile());

        DesiredCapabilities ff = DesiredCapabilities.firefox();
//        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), ff);
//            driver = new RemoteWebDriver(new URL("http://172.17.0.23:4444/wd/hub"), chrome);
            String script = IOUtils.toString(TanaguruJsTestCaseExtractor.class.getClassLoader()
                    .getResourceAsStream(JS_SCRIPT_NAME));

            for (Element el : Jsoup.parse(new URL(URL_LIST), 5000).select("a")) {
                String url = el.attr("abs:href");
                String resourceName = StringUtils.remove(el.attr("href"), ".html");
                if (StringUtils.contains(url, "html")) {
                    driver.get(url);
                    driver.findElementByTagName("body").sendKeys(Keys.TAB);
                    try {
                        FileUtils.write(new File(PATH_TO_WRITE + resourceName + ".json"),
                                new JSONArray(driver.executeScript(script).toString()).toString(4));
                    } catch (JSONException ex) {}
                }
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

}
