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
package org.tanaguru.rules.doc.utils.updateAw22toRgaa30;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alingua
 */
public class CopyFromAw22ToRgaa30 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CopyFiles cf = new CopyFiles();
        try {
            cf.copyFilesAvailable();
        } catch (IOException ex) {
            Logger.getLogger(CopyFromAw22ToRgaa30.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
