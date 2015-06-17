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
package org.tanaguru.maven.plugins.kbcsv;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Vivien Barousse
 * @goal generate
 */
public class KbCsvMojo extends AbstractMojo {

    private static final String[] KB_TEST_RESULTS = {"Failed",
        "Passed",
        "NA",
        "NMI"};

    private static final String[] TG_TEST_RESULTS = {"FAILED",
        "PASSED",
        "NOT_APPLICABLE",
        "NEED_MORE_INFO"};

    /**
     * @parameter default-value="http://www.kbaccess.org/kba/"
     */
    private String baseUrl;

    /**
     * @parameter
     */
    private File inCsv;

    /**
     * @parameter default-value="target/csv/out.csv"
     */
    private File outCsv;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            FileReader fin = new FileReader(inCsv);
            BufferedReader reader = new BufferedReader(fin);

            if (!outCsv.getParentFile().exists()) {
                outCsv.getParentFile().mkdirs();
            }
            PrintWriter writer = new PrintWriter(outCsv);
            writer.println(formatCsvLine("name", "url", "campain", "test", "result"));

            String readed;
            while ((readed = reader.readLine()) != null) {
                String[] params = readed.split(";"); // CSV params: 0=campain, 1=test

                if (params.length != 2) {
                    getLog().warn("Incorrect line: " + readed);
                    continue;
                }
                
                String ruleUrl = baseUrl + params[0] + "/" + params[1] + "/";
                for (int i = 0; i < KB_TEST_RESULTS.length; i++) {
                    String url = ruleUrl + KB_TEST_RESULTS[i]+"/?lang=en";

                    try {
                        for (String result : getUrls(url)) {
                            String targetName = sanitizeClassName(result.substring(28)); // Substring 28 to remove URL beginning
                            writer.println(formatCsvLine(targetName, result, params[0], params[1], TG_TEST_RESULTS[i]));
                        }
                    } catch (JaxenException ex) {
                        getLog().warn("Unable to select URLs for address " + url, ex);
                    } catch (JDOMException ex) {
                        getLog().warn("Invalid XML at " + url, ex);
                    }
                }
            }

            writer.close();
            reader.close();
            fin.close();
        } catch (IOException ex) {
            throw new MojoExecutionException("Unexpected IO Exception", ex);
        }
    }

    private String formatCsvLine(String... args) {
        boolean first = true;
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            if (!first) {
                builder.append(";");
            } else {
                first = false;
            }

            builder.append(arg);
        }

        return builder.toString();
    }

    private String sanitizeClassName(String unsafe) {
        return unsafe.replaceAll("[^0-9A-Za-z_]+", "_");
    }

    private List<String> getUrls(String url) throws JDOMException, JaxenException, IOException {
        SAXBuilder builder = new SAXBuilder();
        EntityResolver resolver = new XhtmlEntityResolver();
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        builder.setEntityResolver(resolver);
        Document document = builder.build(new URL(url));
        XPath xpath = new JDOMXPath("//*[@id='resultat']//*[@href]/@href");
        List<Attribute> results = xpath.selectNodes(document);
        List<String> urls = new ArrayList<String>();
        for (Attribute attr : results) {
            urls.add(attr.getValue());
        }
        return urls;
    }

    private class XhtmlEntityResolver implements EntityResolver {

        @Override
        public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException, IOException {

                if (systemId.equals("http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd")) {
                    InputSource source = new InputSource(getClass().getResourceAsStream("/dtd/xhtml1-strict.dtd"));
                    source.setPublicId(publicId);
                    source.setSystemId(systemId);
                    return source;
                }

            return null;
        }

    }

}
