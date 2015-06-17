/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.elementchecker.pertinence;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.textbuilder.SimpleTextElementBuilder;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 *
 * @author jkowalczyk
 */
public class TextPertinenceCheckerTest extends TestCase {
    
    private static final Logger LOGGER = 
            Logger.getLogger(TextPertinenceCheckerTest.class);
     
    public TextPertinenceCheckerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getTextElementBuilder method, of class TextPertinenceChecker.
     */
    public void testGetTextElementBuilder() {
        LOGGER.debug("getTextElementBuilder");
        TextPertinenceChecker instance = new TextPertinenceChecker( 
                true,
                new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR), 
                "blacklistNomenclature", 
                TestSolution.NEED_MORE_INFO,
                "notPertinent", 
                "manualCheck");
        TextElementBuilder result = instance.getTextElementBuilder();
        assertTrue(result instanceof SimpleTextElementBuilder);
    }

}