/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.rules.doc.utils.exportaw22torgaa3ruledesign;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author alingua
 */
public class ExtractCsvAndCopy {

    private static final String PATH_TO_OLD_HTML_FILE = "$path_yo_your_old_referential_HTML_files";
    private static final String PATH_TO_CREATE_HTML_FILE = "$path_where_you_want_to_create_your_files";
    private static final String CSV_DATA_FILE = "$path_to_your_CSV_file";
    private static final String TARGET_REFERENTIAL = "targetReference";
    private static final String ORIGINAL_REFERENTIAL = "originalReference";
    private static final String CAN_COPY = "can_copy";
    private static final char DELIMITER = 'ø';
    private static final File DATA_FILE = FileUtils.getFile(CSV_DATA_FILE);

    private String[] csvHeaders;

    public Iterable<CSVRecord> getCsv() throws IOException {
        // we parse the csv file to extract the first line and get the headers 
        LineIterator lineIterator;
        lineIterator = FileUtils.lineIterator(DATA_FILE);
        csvHeaders = lineIterator.next().split(String.valueOf(DELIMITER));

        StringBuilder strb = new StringBuilder();
        while (lineIterator.hasNext()) {
            strb.append(lineIterator.next());
            strb.append("\n");
        }

        Reader in;
        try {
            in = new StringReader(strb.toString());
            CSVFormat csvf = CSVFormat.newFormat(DELIMITER).withHeader(csvHeaders);
            return csvf.parse(in);
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    public void copy(Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            String targetReference = record.get(TARGET_REFERENTIAL);
            String originalReference = record.get(ORIGINAL_REFERENTIAL);
            String canCopy = record.get(CAN_COPY);
            if (canCopy.equals("1")) {
                String testCode = targetReference.split("-")[1].replace(".", "-");
                String oldTestCode = originalReference.split("-")[1].replace(".", "-");
                try {
                    FileUtils.copyFile(
                            getFile(PATH_TO_OLD_HTML_FILE + "Rule-" + oldTestCode +".html"),
                            getFile(PATH_TO_CREATE_HTML_FILE + "Rule-" + testCode + ".html"));
                } catch (FileNotFoundException ex) {
                    System.out.println("Rule missing : " + oldTestCode);
                } catch (IOException ex) {
                }
            }
        }
    }

    private File getFile(String path) {
        return FileUtils.getFile(path);
    }

}
