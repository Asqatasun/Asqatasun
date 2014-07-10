/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.ruleimplementation.link;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.LINK_WITH_HREF_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.FORM_ELEMENT;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_DOWNLOADABLE_DOCUMENT_FROM_FORM_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG;
import org.opens.tanaguru.rules.elementchecker.helper.RuleCheckHelper;

/**
 * This class deals with the tests related with links that point to 
 * downloadable documents. * 
 */
public abstract class AbstractDownloadableLinkRuleImplementation 
            extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    private static final String POINT_CHAR = ".";
    private static final String SLASH_CHAR = "/";

    /* the links with a proper extension */
    private final ElementHandler linkWithSimpleExtension = new ElementHandlerImpl();

    /**
     * Default constructor
     * @param elementChecker
     */
    public AbstractDownloadableLinkRuleImplementation (ElementChecker elementChecker) {
        super(
                new SimpleElementSelector(LINK_WITH_HREF_CSS_LIKE_QUERY),
                elementChecker
            );
    }
    
    @Override
    protected void select(
            SSPHandler sspHandler, 
            ElementHandler elementHandler) {
        super.select(sspHandler, elementHandler);
        Iterator<Element> iter = ((Collection<Element>)elementHandler.get()).iterator();
        Element el;
        while (iter.hasNext()){
            el = iter.next();
            try {
                URI uri = new URI(el.absUrl(HREF_ATTR), true);
                if (isLinkWithProperExtension(uri)) {
                    if (uri.hasFragment()) {
                        iter.remove();
                    } else {
                        linkWithSimpleExtension.add(el);
                    }
                }
            } catch (URIException use){}
        }
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        
        super.check(sspHandler, linkWithSimpleExtension, testSolutionHandler);
        
        TestSolution checkerSolution = testSolutionHandler.getTestSolution();
        
        if (checkerSolution.equals(TestSolution.NOT_APPLICABLE) && 
                !elementHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO, 
                    RuleCheckHelper.specifyMessageToRule(
                        CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG, 
                        this.getTest().getCode())
                    );
            return;
        }
        
        if (checkerSolution.equals(TestSolution.PASSED) ) { 
            // if nothing have been found and some links have a no extension
            // the test result is NMI
            if (elementHandler.get().size() > linkWithSimpleExtension.get().size() ) {
                testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
                sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO, 
                    RuleCheckHelper.specifyMessageToRule(
                        CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG, 
                        this.getTest().getCode())
                    );
                return;
            }
            // we search whether the page contains form, if yes, the result 
            // is nmi, if not, the test is not applicable
            ElementHandler formHandler = new ElementHandlerImpl();
            new SimpleElementSelector(FORM_ELEMENT).selectElements(sspHandler, formHandler);
            if (formHandler.isEmpty()) {
                testSolutionHandler.cleanTestSolutions();
                testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            } else {
                testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
                sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO, 
                    RuleCheckHelper.specifyMessageToRule(
                        CHECK_DOWNLOADABLE_DOCUMENT_FROM_FORM_MSG, 
                        this.getTest().getCode())
                    );
            }
        }
    }
    
    /**
     * 
     * @param uri
     * @return whether the current link has a proper extension (link.html)
     * @throws URIException
     */
    private boolean isLinkWithProperExtension(URI uri) throws URIException{
        if (uri.hasQuery()) {
            return false;
        }
        String path = uri.getPath();
        if (StringUtils.isBlank(path) || 
                StringUtils.equals(path, SLASH_CHAR)) {
            return false;
        }
        int lastSlash = StringUtils.lastIndexOf(path, SLASH_CHAR);
        return StringUtils.substring(path, lastSlash).contains(POINT_CHAR);
    }
    
}