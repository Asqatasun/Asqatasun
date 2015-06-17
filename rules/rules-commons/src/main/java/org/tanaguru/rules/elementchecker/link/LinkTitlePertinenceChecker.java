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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.elementchecker.CompositeChecker;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.text.TextBelongsToBlackListChecker;
import org.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.tanaguru.rules.elementchecker.text.TextNotIdenticalToAttributeChecker;
import org.tanaguru.rules.elementchecker.text.TextOnlyContainsNonAlphanumericalCharactersChecker;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.textbuilder.LinkTextElementBuilder;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether the title of a link is pertinent by verifying :
 * <ul>
 * <li>it is not empty</li>
 * <li>it is not only composed of non alphanumerical characters</li>
 * <li>its content is not blacklisted</li>
 * <li>its content is not strictly identical to the link text</li>
 * <li>its content contains at least the link text</li>
 * </ul>
 */
public class LinkTitlePertinenceChecker extends CompositeChecker {

    private  static final String LINK_TEXT_BL_NOM_NAME = "LinkTextBlacklist";
    
    private final TextElementBuilder linkTextElementBuilder = new LinkTextElementBuilder();
    @Override 
    public TextElementBuilder getTextElementBuilder() {
        return linkTextElementBuilder;
    }
    
    private final TextElementBuilder titleAttrElementBuilder = 
            new TextAttributeOfElementBuilder(TITLE_ATTR);
    /* */
    private boolean isEqualContentAuthorized = false;

    /**
     * Constructor.
     * @param isEqualContentAuthorized 
     */
    public LinkTitlePertinenceChecker(boolean isEqualContentAuthorized) {
        super(HtmlElementStore.TEXT_ELEMENT2, TITLE_ATTR);
        this.isEqualContentAuthorized = isEqualContentAuthorized;
        addCheckers();
    }
    
    /**
     * Constructor.
     */
    public LinkTitlePertinenceChecker() {
        super(HtmlElementStore.TEXT_ELEMENT2, TITLE_ATTR);
        addCheckers();
    }
 
    /**
     * 
     */
    private void addCheckers() {
        ElementChecker ec = new TextEmptinessChecker(
                        titleAttrElementBuilder,
                        RemarkMessageStore.EMPTY_LINK_TITLE_MSG, 
                        null,
                        getEeAttributeNames());
        ec.setTextElementBuilder(linkTextElementBuilder);
        addChecker(ec);
        
        ec = new TextOnlyContainsNonAlphanumericalCharactersChecker(
                        titleAttrElementBuilder,
                        getFailureSolution(),
                        RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG, 
                        getEeAttributeNames());
        ec.setTextElementBuilder(linkTextElementBuilder);
        addChecker(ec);

        ec = new TextBelongsToBlackListChecker(
                            titleAttrElementBuilder,
                            LINK_TEXT_BL_NOM_NAME,
                            getFailureSolution(),
                            RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG, 
                            getEeAttributeNames());
        ec.setTextElementBuilder(linkTextElementBuilder);
        addChecker(ec);
        
        TestSolution strictCheckerSolution = TestSolution.FAILED;
        String strictCheckerMsg = RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG;
        if(isEqualContentAuthorized) {
            strictCheckerSolution = TestSolution.NEED_MORE_INFO;
            strictCheckerMsg = RemarkMessageStore.SUSPECTED_PERTINENT_LINK_TITLE_MSG;
        }
        
        ElementChecker strictChecker = new TextNotIdenticalToAttributeChecker(
                        getTextElementBuilder(),
                        titleAttrElementBuilder,
                        new ImmutablePair(strictCheckerSolution,strictCheckerMsg),
                        new ImmutablePair(TestSolution.PASSED,""),
                        getEeAttributeNames());
        ((TextNotIdenticalToAttributeChecker)strictChecker).setStrictEquality(true);
        strictChecker.setTextElementBuilder(linkTextElementBuilder);
        addChecker(strictChecker);
        
        ElementChecker containChecker = new TextNotIdenticalToAttributeChecker(
                        getTextElementBuilder(),
                        titleAttrElementBuilder,
                        new ImmutablePair(TestSolution.NEED_MORE_INFO,RemarkMessageStore.SUSPECTED_PERTINENT_LINK_TITLE_MSG),
                        new ImmutablePair(TestSolution.NEED_MORE_INFO,RemarkMessageStore.SUSPECTED_NOT_PERTINENT_LINK_TITLE_MSG),
                        getEeAttributeNames());
        containChecker.setTextElementBuilder(linkTextElementBuilder);
        addChecker(containChecker);
    }
    
}