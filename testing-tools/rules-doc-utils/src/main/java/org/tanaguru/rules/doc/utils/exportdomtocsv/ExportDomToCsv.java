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
package org.tanaguru.rules.doc.utils.exportdomtocsv;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author alingua
 */
public class ExportDomToCsv {

    private static final String FOLDER = "$path_to_csv_file";

    /**
     * Before using it please set the FOLDER variable with the path where you
     * want to create your csv file.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File ref = FileUtils.getFile(FOLDER);
        JsoupFunc jsf = new JsoupFunc();
        Document doc = jsf.getDocument();
        Elements thematiques = doc.select("div.thematique");
        StringBuilder sb = new StringBuilder();
        String testCode = "";
        String testLabel = "";
        String critere = "";
        for (int i = 2; i < thematiques.size(); i++) {
            String themeIndex = String.valueOf(i - 1) + "ø";
            String theme = (thematiques.get(i).child(0).text() + "ø");
            Elements criteres = thematiques.get(i).select("h3");
            for (int j = 1; j < criteres.size(); j++) {
                Element critereLevel = criteres.get(j);
                String critereH3String = critereLevel.toString();
                String level = critereH3String.substring(critereH3String.indexOf("[") + 1, critereH3String.indexOf("]")) + "ø";
                Elements tests = criteres.get(j).nextElementSibling().select("[id^=test-]");
                try {
                    critere = criteres.get(j).id().substring(5, 10) + "ø";
                } catch (StringIndexOutOfBoundsException sioobe) {
                    try {
                        critere = criteres.get(j).id().substring(5, 9) + "ø";
                    } catch (StringIndexOutOfBoundsException sioobe2) {
                        critere = criteres.get(j).id().substring(5, 8) + "ø";
                    }
                }
                String[] critereArray = criteres.get(j).text().split("] ");
                String critereLabel = critereArray[1].toString() + "ø";
                for (Element el : tests) {
                    Pattern digitPattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\s?\\:?\\s?");
                    Matcher matcher = digitPattern.matcher(el.text());
                    if (matcher.find()) {
                        String testLabelReplace = el.html().replace("index.php", "http://www.accessiweb.org/index.php").replace("\n", "");
                        testLabel = testLabelReplace.substring(matcher.end(), testLabelReplace.length()) + "ø";
                    }
                    try {
                        testCode = el.id().substring(5, 12) + "ø";
                    } catch (StringIndexOutOfBoundsException sioobe) {
                        try {
                            testCode = (el.id().substring(5, 11) + "ø");
                        } catch (StringIndexOutOfBoundsException sioobe3) {
                            testCode = (el.id().substring(5, 10) + "ø");
                        }
                    }
                    sb.append(themeIndex + theme + critere + critereLabel + testCode + testLabel + level + "\n");
                }
            }
        }
        FileUtils.writeStringToFile(ref, sb.toString());
    }
}
