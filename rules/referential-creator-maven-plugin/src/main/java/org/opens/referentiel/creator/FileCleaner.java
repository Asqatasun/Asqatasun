/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.referentiel.creator;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author alingua
 */
public class FileCleaner {

    private static final String I18N_FOLDER = "-i18n";
    private static final String RESOURCES_FOLDER = "-resources";
    private static final String TESTCASES_FOLDER = "-testcases";
    private static final String ALLRESOURCES_FOLDER = "-all-resources";

    /**
     * Default constructor
     */
    private FileCleaner() {
    }

    public static void cleanUpContext(String destinationFolder) throws IOException {
        FileUtils.forceDelete(new File(destinationFolder + "/src/main/java/org/opens/App.java"));
        FileUtils.forceDelete(new File(destinationFolder + "/src/test/java/org/opens/AppTest.java"));
        FileUtils.deleteDirectory(new File(destinationFolder + ALLRESOURCES_FOLDER + "/src/main/java"));
        FileUtils.deleteDirectory(new File(destinationFolder + ALLRESOURCES_FOLDER + "/src/test"));
        FileUtils.deleteDirectory(new File(destinationFolder + RESOURCES_FOLDER + "/src/main/java"));
        FileUtils.deleteDirectory(new File(destinationFolder + RESOURCES_FOLDER + "/src/test"));
        FileUtils.deleteDirectory(new File(destinationFolder + TESTCASES_FOLDER + "/src/main/java"));
        FileUtils.deleteDirectory(new File(destinationFolder + TESTCASES_FOLDER + "/src/test"));
        FileUtils.deleteDirectory(new File(destinationFolder + I18N_FOLDER + "/src/main/java"));
        FileUtils.deleteDirectory(new File(destinationFolder + I18N_FOLDER + "/src/test"));
    }
}
