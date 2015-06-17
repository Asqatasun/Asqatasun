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
package org.tanaguru.rules.doc.utils.ruledesign.extractor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This class extract the html Rule Design from drupal tanaguru website. To
 * convert these files to Markdown syntaxe, please use the
 * HtmlToMarkdownConvertor.sh script available in src/main/resources folder.
 *
 * http://tanaguru.org/en/content/rules-design
 *
 * @author alingua
 */
public class ExtractRuleDesignHtmlCode {

    private static final String FOLDER = "$path_to_your_folder";
    private static final String PREFIX_URL_TO_REFERENTIAL = "http://tanaguru.org/en/content/aw22-rule-";
    private static final int MAX_THEME_NUMBER = 14;
    private static final int MAX_CRITERE_NUMBER = 25;
    private static final int MAX_TEST_NUMBER = 10;

    /**
     * Before using it please set the FOLDER variable with the path where you
     * want to create your extract html files.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      first boucle for is for the theme number
        for (int i = 1; i < MAX_THEME_NUMBER; i++) {
            // second boucle for is for the critere number
            for (int j = 1; j < MAX_CRITERE_NUMBER; j++) {
                // third boucle for is for the test number
                for (int k = 1; k < MAX_TEST_NUMBER; k++) {
                    URL url = null;
                    try {
                        Connection connection = Jsoup.connect(PREFIX_URL_TO_REFERENTIAL + i + "-" + j + "-" + k);
                        Connection.Response resp = connection.response();
                        if (resp.statusCode() != 404) {
                            url = new URL(PREFIX_URL_TO_REFERENTIAL + i + "-" + j + "-" + k);
                            Document doc = Jsoup.parse(url, 4000);
                            System.out.println(doc.title());

                            Elements summary = doc.select(".content.clear-block");
                            FileUtils.writeStringToFile(new File(FOLDER + "/RuleDesign/Rule-" + i + "-" + j + "-" + k + ".html"), summary.html());
                        }
                    } catch (MalformedURLException ex) {
                        System.out.println("URL MAL FORMEE");
                    } catch (IOException ex) {
                        if (url != null) {
                            System.out.println("URL 404 : " + url.toString());
                        } else {
                            System.out.println("EMPTY URL");
                        }
                    }
                }
            }
        }
    }

}
