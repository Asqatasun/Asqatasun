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
package org.tanaguru.rules.doc.utils.exportaw22torgaa3ruledesign;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author alingua
 */
public class CopyAw22ToRgaa30RuleDesign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ExtractCsvAndCopy extractor = new ExtractCsvAndCopy();
        try {
            Iterable<CSVRecord> records = extractor.getCsv();
            extractor.copy(records);
        } catch (IOException ex) {
            Logger.getLogger(CopyAw22ToRgaa30RuleDesign.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
}
