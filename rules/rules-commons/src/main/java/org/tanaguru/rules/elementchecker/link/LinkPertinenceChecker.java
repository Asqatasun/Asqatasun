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

package org.tanaguru.rules.elementchecker.link;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.tanaguru.rules.textbuilder.LinkTextElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether a the text of a link is pertinent by verifying :
 * <ul>
 * <li>it is not only composed of non alphanumerical characters</li>
 * <li>it is not blacklisted</li>
 *</ul>
 */
public class LinkPertinenceChecker extends TextPertinenceChecker {

    private  static final String LINK_TEXT_BL_NOM_NAME = "LinkTextBlacklist";

    /* The element builder needed to build the link text */
    private LinkTextElementBuilder linkTextElementBuilder;
    @Override
    public TextElementBuilder getTextElementBuilder() {
        if (linkTextElementBuilder == null) {
            linkTextElementBuilder = new LinkTextElementBuilder();
        }
        return linkTextElementBuilder;
    }

    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     */
    public LinkPertinenceChecker(
            String notPertinentMessageCode,
            String manualCheckMessage) {
        super(false, 
              null, 
              LINK_TEXT_BL_NOM_NAME,
              notPertinentMessageCode,
              manualCheckMessage);
        setLinkTextElementBuilderToCheckers();
    }
    
    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList
     */
    public LinkPertinenceChecker(
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(false, 
              null, 
              LINK_TEXT_BL_NOM_NAME,
              notPertinentMessageCode,
              manualCheckMessage, 
              eeAttributeNameList);
        setLinkTextElementBuilderToCheckers();
    }
    
    /**
     * Constructor.
     * Enables to override the not pertinent solution.
     * 
     * @param notPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public LinkPertinenceChecker(
            TestSolution notPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(false, 
              null, 
              LINK_TEXT_BL_NOM_NAME,
              notPertinentSolution,
              notPertinentMessageCode,
              manualCheckMessage, 
              eeAttributeNameList);
        setLinkTextElementBuilderToCheckers();
    }

    /**
     * Needed to use the appropriate textBuilder when creating a EvidenceElement
     * of type TEXT.
     */
    private void setLinkTextElementBuilderToCheckers() {
        for (ElementChecker ec : getCheckers()) {
            ec.setTextElementBuilder(getTextElementBuilder());
        }
    }

}