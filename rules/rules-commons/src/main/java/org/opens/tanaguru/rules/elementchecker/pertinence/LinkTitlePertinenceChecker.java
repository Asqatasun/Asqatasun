/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker.pertinence;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextBelongsToBlackListChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextNotIdenticalToAttributeChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextOnlyContainsNonAlphanumericalCharactersChecker;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.rules.textbuilder.LinkTextElementBuilder;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * 
 * @author jkowalczyk
 */
public class LinkTitlePertinenceChecker extends PertinenceChecker {

    private  static final String LINK_TEXT_BL_NOM_NAME = "LinkTextBlacklist";
    
    private TextElementBuilder linkTextElementBuilder = new LinkTextElementBuilder();
    @Override 
    public TextElementBuilder getTextElementBuilder() {
        return linkTextElementBuilder;
    }
    
    private TextElementBuilder titleAttrElementBuilder = 
            new TextAttributeOfElementBuilder(TITLE_ATTR);
    /* */
    private boolean isEqualContentAuthorized = false;

    /**
     * Constructor.
     * @param isEqualContentAuthorized 
     */
    public LinkTitlePertinenceChecker(boolean isEqualContentAuthorized) {
        super(null, HtmlElementStore.TEXT_ELEMENT2, TITLE_ATTR);
        this.isEqualContentAuthorized = isEqualContentAuthorized;
        addCheckers();
    }
    
    /**
     * Constructor.
     */
    public LinkTitlePertinenceChecker() {
        super(null, HtmlElementStore.TEXT_ELEMENT2, TITLE_ATTR);
        addCheckers();
    }
 
    /**
     * 
     */
    private void addCheckers() {
        addChecker(new TextEmptinessChecker(
                        titleAttrElementBuilder,
                        RemarkMessageStore.EMPTY_LINK_TITLE_MSG, 
                        null,
                        getEeAttributeNames()));
        
        addChecker(new TextOnlyContainsNonAlphanumericalCharactersChecker(
                        titleAttrElementBuilder,
                        getFailureSolution(),
                        RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG, 
                        getEeAttributeNames()));

        addChecker(new TextBelongsToBlackListChecker(
                            titleAttrElementBuilder,
                            LINK_TEXT_BL_NOM_NAME,
                            getFailureSolution(),
                            RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG, 
                            getEeAttributeNames()));
        
        TestSolution strictCheckerSolution = TestSolution.FAILED;
        String strictCheckerMsg = RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG;
        if(isEqualContentAuthorized) {
            strictCheckerSolution = TestSolution.NEED_MORE_INFO;
            strictCheckerMsg = RemarkMessageStore.SUSPECTED_PERTINENT_LINK_TITLE_MSG;
        }
        
        ElementChecker strictChecker = new TextNotIdenticalToAttributeChecker(
                        getTextElementBuilder(),
                        titleAttrElementBuilder,
                        strictCheckerSolution,
                        TestSolution.PASSED,
                        strictCheckerMsg, 
                        null,
                        getEeAttributeNames());
        ((TextNotIdenticalToAttributeChecker)strictChecker).setStrictEquality(true);
        addChecker(strictChecker);
        
        ElementChecker containChecker = new TextNotIdenticalToAttributeChecker(
                        getTextElementBuilder(),
                        titleAttrElementBuilder,
                        TestSolution.NEED_MORE_INFO,
                        TestSolution.NEED_MORE_INFO,
                        RemarkMessageStore.SUSPECTED_PERTINENT_LINK_TITLE_MSG, 
                        RemarkMessageStore.SUSPECTED_NOT_PERTINENT_LINK_TITLE_MSG, 
                        getEeAttributeNames());
        addChecker(containChecker);
    }
}