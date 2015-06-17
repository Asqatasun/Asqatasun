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
package org.tanaguru.webapp.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author jkowalczyk
 */
public class AbstractTanaguruOnlineTestTest extends TestCase {

    public AbstractTanaguruOnlineTestTest(String testName) {
        super(testName);
    }

    public void testComputeWebappResult() {
        StringBuilder str =new StringBuilder();
        try {
            /*  Sets up a file reader to read the file passed on the command
            line one character at a time */
            FileReader input = new FileReader("/tmp/test-page.txt");

            /* Filter FileReader through a Buffered read to read a line at a
            time */
            BufferedReader bufRead = new BufferedReader(input);

            String line;    // String that holds current file line
            int count = 0;  // Line number of count
            // Read first line
            line = bufRead.readLine();
            count++;

            // Read through file one line at time. Print line # and line
            while (line != null) {
                str.append(line);
                line = bufRead.readLine();
//                count++;
            }

            bufRead.close();

        } catch (ArrayIndexOutOfBoundsException e) {
            /* If no file was passed on the command line, this expception is
            generated. A message indicating how to the class should be
            called is displayed */
            System.out.println("Usage: java ReadFile filename\n");

        } catch (IOException e) {
            // If another exception is generated, print a stack trace
        }
//        System.out.println(AbstractTanaguruOnlineTest.computeWebappResult(str.toString(), "6.1.1"));
    }

}