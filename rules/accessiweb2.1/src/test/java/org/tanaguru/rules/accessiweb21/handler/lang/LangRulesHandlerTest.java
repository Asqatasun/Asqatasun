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
package org.tanaguru.rules.accessiweb21.handler.lang;

import junit.framework.TestCase;

/**
 *
 * @author jkowalczyk
 */
public class LangRulesHandlerTest extends TestCase {
    
    public LangRulesHandlerTest(String testName) {
        super(testName);
    }

    public void testGetNonAlphanumericPattern() {
    }

    public void testGetElementCounter() {
    }

    public void testSetElementCounter() {
    }

    public void testGetXhtmlDoctypesSet() {
    }

    public void testGetValidLanguagesSet() {
    }

    public void testGetSSPHandler() {
    }

    public void testSetSSPHandler() {
    }

    public void testSetNomenclatureLoaderService() {
    }

    public void testSetProcessRemarkService() {
    }

    public void testGetProcessRemarkService() {
    }

    public void testGetInstance() {
    }

    public void testGetAllNodeWithoutLangAttribute() {
    }

    public void testGetNodeWithLangAttribute() {
    }

    public void testExtractTextContentOfThePage() {
    }

    public void testExtractTextContentFromNodeAndChildNode() {
    }

    public void testExtractTextContentFromNode() {
    }

    public void testIsLanguageProvidedByHtmlTag() {
    }

    public void testIsLanguageAbsentOnThePage() {
    }

    public void testGetHtmlTagWithLangAttribute() {
    }

    public void testDoesNodeContainLangAttribute() {
    }

    public void testExtractLanguageFromNode() {
    }

    public void testCheckLanguageDeclarationValidity() {
    }

    public void testIsLangWellDeclared() {
        LangRulesHandler lrh = new LangRulesHandler();
        assertFalse(lrh.isLangWellDeclared(""));
        assertFalse(lrh.isLangWellDeclared("   "));
        assertTrue(lrh.isLangWellDeclared("en-en"));
        assertTrue(lrh.isLangWellDeclared("en"));
        assertTrue(lrh.isLangWellDeclared("en-enenenen"));
        assertFalse(lrh.isLangWellDeclared("en_en"));
        assertFalse(lrh.isLangWellDeclared("ensdfsdqsd"));
        assertFalse(lrh.isLangWellDeclared("bou-"));
        assertTrue(lrh.isLangWellDeclared("bou-en"));
    }

    public void testCheckLanguageRelevancy() {
    }

}
