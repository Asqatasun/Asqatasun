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
package org.tanaguru.referentiel.creator;

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
