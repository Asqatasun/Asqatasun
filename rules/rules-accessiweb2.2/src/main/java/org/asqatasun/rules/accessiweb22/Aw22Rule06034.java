/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.accessiweb22;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandlerImpl;
import org.asqatasun.ruleimplementation.link.AbstractLinkRuleImplementation;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.link.LinkPertinenceChecker;
import org.asqatasun.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.asqatasun.rules.elementselector.CompositeLinkElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.UNEXPLICIT_LINK_MSG;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;
import org.asqatasun.service.ProcessRemarkService;

/**
 * Implementation of the rule 6.3.4 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/accessiweb2.2/06.Links/Rule-6.3.4.html">the rule 6.3.4 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-3-4"> 6.3.4 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Aw22Rule06034 extends AbstractLinkRuleImplementation {

    /* the link text blacklist nomenclature name*/ 
    private static final String LINK_TEXT_BL_NOM_NAME = "LinkTextBlacklist";
    
    /* the checker of link title pertinence*/
    private final ElementChecker titlePertinenceElementChecker = 
            new TextPertinenceChecker(
                new TextAttributeOfElementBuilder(TITLE_ATTR),
                // no emptiness check
                false, 
                // no comparison with other attribute
                null, 
                // blacklist to compare with
                LINK_TEXT_BL_NOM_NAME, 
                // message when not pertinent
                UNEXPLICIT_LINK_MSG, 
                // message when pertinence needs to be manually checked
                CHECK_LINK_PERTINENCE_MSG, 
                // evidence elements
                TEXT_ELEMENT2,
                TITLE_ATTR);
    /* local collection of process remarks*/
    private final Collection<ProcessRemark> remarks = new ArrayList<>();
    /* local instance of ProcessRemarkService*/
    ProcessRemarkService  prs;
    
    /**
     * Default constructor
     */
    public Aw22Rule06034 () {
        // context is not taken into consideration 
        super(new CompositeLinkElementSelector(false, false), 
              new LinkPertinenceChecker(
                    // not pertinent solution 
                    TestSolution.FAILED,
                    // not pertinent message
                    UNEXPLICIT_LINK_MSG,
                    // manual check message
                    CHECK_LINK_PERTINENCE_MSG,
                    // evidence elements
                    TEXT_ELEMENT2,
                    TITLE_ATTR
              ),
              null);
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (getLinkElementSelector().isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        
        prs = sspHandler.getProcessRemarkService();
        setServicesToChecker(titlePertinenceElementChecker);
        
        if (! getLinkElementSelector().getDecidableElements().isEmpty()) {
            setServicesToChecker(getDecidableElementsChecker());
            for (Element el : getLinkElementSelector().getDecidableElements().get()) {
                testSolutionHandler.addTestSolution(testLink(sspHandler, el));
            }
        }
        // reset service and aggregate all the remarks collected locally
        // for further save
        prs.resetService();
        prs.getRemarkList().addAll(remarks);
    }
    
    /**
     * 
     * @param sspHandler
     * @param el
     * @return 
     */
    private TestSolution testLink(SSPHandler sspHandler, Element el) {
        ElementHandler<Element> elHandler = new ElementHandlerImpl(el);
        TestSolutionHandler tsHandler = new TestSolutionHandlerImpl();
        
        // reset the service for each tested element. We use the collection 
        // handled by the service to feed the local collection of remarks
        prs.resetService();
        
        // check the pertinence of the link
        getDecidableElementsChecker().check(
                    sspHandler, 
                    elHandler, 
                    tsHandler);
        
        // get the processRemark for eventually override it with the result
        // returned by the title pertinence checker
        ProcessRemark remark = prs.getRemarkList().iterator().next();
        
        // we check the pertinence of the title if and only if the result is 
        // failed. This checker may eventually change the result from failed
        // to nmi but the inverse is impossible.
        if (tsHandler.getTestSolution().equals(TestSolution.FAILED)) {
            
            // check the pertinence of the title of the link
            String linkText = getDecidableElementsChecker().
                                  getTextElementBuilder().
                                      buildTextFromElement(el);
            
            if (testTitleAttributeLink(sspHandler, el, linkText).
                        equals(TestSolution.NEED_MORE_INFO)) {
                //override result (evidence element have already been collected
                remark.setIssue(TestSolution.NEED_MORE_INFO);
                remark.setMessageCode(CHECK_LINK_PERTINENCE_MSG);
                remarks.add(remark);
                return TestSolution.NEED_MORE_INFO;
            }
        }

        remarks.add(remark);
        
        return tsHandler.getTestSolution();
    }
    
    /**
     * 
     * @param sspHandler
     * @param el
     * @param linkText
     * @return 
     */
    private TestSolution testTitleAttributeLink(
            SSPHandler sspHandler, 
            Element el, 
            String linkText) {
        // if the current has no title or has an empty title or has a title 
        // content identical to the link text, returns not applicable.
        if (!el.hasAttr(TITLE_ATTR)) {
            return TestSolution.NOT_APPLICABLE;
        }
        String attrValue=el.attr(TITLE_ATTR);
        if (StringUtils.isBlank(attrValue)) {
            return TestSolution.NOT_APPLICABLE;
        }
        if (StringUtils.equalsIgnoreCase(attrValue, linkText)) {
            return TestSolution.NOT_APPLICABLE;
        }
        ElementHandler<Element> elHandler = new ElementHandlerImpl(el);
        TestSolutionHandler tsHandler = new TestSolutionHandlerImpl();
        titlePertinenceElementChecker.check(sspHandler, elHandler, tsHandler);
        return tsHandler.getTestSolution();
    }

}