/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
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

package org.opens.rules.doc.utils.rga33.extractor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author jkowalczyk
 */
public class Rgaa3Extractor {

    static final Map<String, String> RGAA3 = new LinkedHashMap<>();
    static final Map<String, String> RGAA3_RAW = new LinkedHashMap<>();
    static final Map<String, String> AW22 = new LinkedHashMap<>();

    static final Map<String, String> NEW_RGAA3 = new LinkedHashMap<>();
    static final Map<String, String> IDENTICAL_RGAA3 = new LinkedHashMap<>();
    static final Map<String, String> DIFFERENT_RGAA3 = new LinkedHashMap<>();

    static final String RGAA3_MAIN_URL = "http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/";
    
    static final String RGAA3_REF_URL = RGAA3_MAIN_URL+"referentiel_technique.htm";
    static final String AW22_URL = "http://www.accessiweb.org/index.php/accessiweb_2.2_liste_deployee.html";

    static final String TEST_SELECTOR = ".tests li";
    static final String CRITERION_SELECTOR = ".tests li";
    
    static final String TEST_ID_PREFIX = "test-";
    
    static final String PATH_TO_WRITE = "";
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Document rgaa3Doc = Jsoup.parse(new URL(RGAA3_REF_URL), 10000);
        Document aw22Doc = Jsoup.parse(new URL(AW22_URL), 10000);

        boolean isFirst112 = false;

        for (Element el : rgaa3Doc.select(TEST_SELECTOR)) {
            if (StringUtils.isNotBlank(el.id())) {
                String test = extractTestFromId(el.id());
                if (el.id().equals("test-1-1-2")) {
                    if (!isFirst112) {
                        isFirst112 = true;
                        RGAA3.put(test, el.text());
                        RGAA3_RAW.put(test, el.html());
                    } else {
                        RGAA3.put("1-1-4", el.text());
                        RGAA3_RAW.put("1-1-4", el.html());
                    }
                } else {
                    RGAA3.put(test, el.text());
                    RGAA3_RAW.put(test, el.html());
                }
            }
        }

        // Extract rules from accessiweb 2.2
        for (Element el : aw22Doc.select(TEST_SELECTOR)) {
            if (StringUtils.isNotBlank(el.text())) {
                AW22.put(extractTestFromId(el.id()), el.text());
            }
        }

        // Check whether the rule is either new, either identical or different
        // regarding the rule text
        for (Map.Entry<String, String> entry : RGAA3.entrySet()) {
            if (!AW22.containsKey(entry.getKey())) {
                NEW_RGAA3.put(entry.getKey(), entry.getValue());
            } else {
                if (AW22.get(entry.getKey()).equals(entry.getValue())) {
                    IDENTICAL_RGAA3.put(entry.getKey(), entry.getValue());
                } else {
                    DIFFERENT_RGAA3.put(entry.getKey(), entry.getValue());
                }
            }
        }

        // Display and write into file new rules
        System.out.println(" ##################### New RGAA3 : " + NEW_RGAA3.size());
        StringBuilder strbnew = new StringBuilder();
        for (Map.Entry<String, String> entry : NEW_RGAA3.entrySet()) {
            strbnew.append(entry.getKey());
            strbnew.append("\n");
            System.out.println(entry.getKey());
        }
        FileUtils.write(new File(PATH_TO_WRITE+"rules/rgaa3.0/new_in_rgaa3.txt"), strbnew.toString());
        System.out.println("");
        
        // Display and write into file identical rules
        System.out.println(" ##################### Identical RGAA3 : " + IDENTICAL_RGAA3.size());
        StringBuilder strbidentical = new StringBuilder();
        for (Map.Entry<String, String> entry : IDENTICAL_RGAA3.entrySet()) {
            strbidentical.append(entry.getKey());
            strbidentical.append("\n");
            System.out.println(entry.getKey());
        }
        FileUtils.write(new File(PATH_TO_WRITE+"rules/rgaa3.0/identical_in_rgaa3.txt"), strbidentical.toString());
        System.out.println("");
        
        // Display and write into file different rules
        System.out.println(" ##################### Different RGAA3 : " + DIFFERENT_RGAA3.size());
        StringBuilder strbdifferent = new StringBuilder();
        for (Map.Entry<String, String> entry : DIFFERENT_RGAA3.entrySet()) {
            strbdifferent.append(entry.getKey());
            strbdifferent.append("\n");
            System.out.println(entry.getKey());
        }
        FileUtils.write(new File(PATH_TO_WRITE+"rules/rgaa3.0/different_in_rgaa3.txt"), strbdifferent.toString());

        
        StringBuilder strb = new StringBuilder();
        for (Map.Entry<String, String> entry : RGAA3_RAW.entrySet()) {
            String ruleContent = extractRuleContent(entry.getValue());
            strb.append("Rgaa30-");
            strb.append(entry.getKey());
            strb.append("=");
            strb.append(ruleContent);
            strb.append("\n");
            strb.append("Rgaa30-");
            strb.append(entry.getKey());
            strb.append("-url=");
            strb.append(RGAA3_MAIN_URL);
            strb.append("referentiel_technique.htm#test-");
            strb.append(entry.getKey());
            strb.append("\n");
        }
        FileUtils.write(new File(PATH_TO_WRITE+"rules/rgaa3.0/src/main/resources/i18n/rule-rgaa30-I18N.properties"), strb.toString());

        StringBuilder crit = new StringBuilder();
        for (Element el : rgaa3Doc.select("h3.crit")) {
            if (StringUtils.isNotBlank(el.id())) {
                crit.append(el.id().replace("crit", "Rgaa30"));
                crit.append("=");
                String content = el.html();
                content = content.substring(content.indexOf("] ") + 1);
//                content = content.replaceAll("href=\"", "href=\""+RGAA3_MAIN_URL);
                content = extractRuleContent(content);
                crit.append(content);
                crit.append("\n");
            }
        }
        FileUtils.write(new File(PATH_TO_WRITE+"rules/rgaa3.0/src/main/resources/i18n/criterion-rgaa30-I18N.properties"), crit.toString());
    }

    private static String extractTestFromId(String id) {
        return id.replace(TEST_ID_PREFIX, "");
    }
    
    private static String extractRuleContent(String rawRuleContent) {
        String ruleContent = rawRuleContent.substring(rawRuleContent.indexOf(":") + 1);
//        ruleContent = ruleContent.replaceAll("href=\"", "href=\""+RGAA3_MAIN_URL);
        ruleContent = ruleContent.replaceAll("</a>", "");
        ruleContent = ruleContent.replaceAll(" class=\"ssTests\"", "").trim();
        while (ruleContent.indexOf("<a") > 0) {
            String linkToReplace = ruleContent.substring(ruleContent.indexOf("<a"), ruleContent.indexOf("\">")+2);
            ruleContent = ruleContent.replaceAll(linkToReplace, "");
        }
        ruleContent = ruleContent.replaceAll("\n", " \\\\\n");
        return ruleContent;
    }
}
