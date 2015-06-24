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

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import static org.tanaguru.rules.keystore.AttributeStore.ABSENT_ATTRIBUTE_VALUE;
import static org.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import org.tanaguru.rules.keystore.EvidenceStore;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.textbuilder.LinkTextElementBuilder;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This checker whether identical links (links with the same text and content
 * of the title attribute) have a different target (different value for the href
 * attribute)
 */
public class IdenticalLinkWithDifferentTargetChecker extends ElementCheckerImpl {

    /* The text element builder. By default, it is a simple Text builder */
    private final TextElementBuilder linkTextBuilder = new LinkTextElementBuilder();
    @Override
    public TextElementBuilder getTextElementBuilder() {
        return linkTextBuilder;
    }
    private final TextElementBuilder titleAttrTextBuilder = 
            new TextAttributeOfElementBuilder(TITLE_ATTR);
    private final TextElementBuilder hrefAttrTextBuilder = 
            new TextAttributeOfElementBuilder(HREF_ATTR);
    
    private String identicalLinkDetectedMsg = 
            RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET;
    /**
     * Constructor. 
     * 
     */
    public IdenticalLinkWithDifferentTargetChecker() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param linksWithContext
     */
    public IdenticalLinkWithDifferentTargetChecker(boolean linksWithContext) {
        this();
        // if the links
        if (linksWithContext) {
            setSuccessSolution(TestSolution.NEED_MORE_INFO);
            setFailureSolution(TestSolution.NEED_MORE_INFO);
            identicalLinkDetectedMsg = 
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET;
        }
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        searchIdenticalLinkWithDifferentTarget(
                elements, 
                testSolutionHandler);
    }

    /**
     * 
     * @param elements
     * @param testSolutionHandler
     */
    private void searchIdenticalLinkWithDifferentTarget (
            Elements elements,
            TestSolutionHandler testSolutionHandler){

        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        // We first search the identical links in the node selected set
        Collection<List<Link>> identicalLinks = 
                sortLinksByIdenticalTextTarget(elements);

        // If identical link have been found
        if (identicalLinks != null && !identicalLinks.isEmpty()) {
            // for each list of identical links
            for (List<Link> links : identicalLinks) {
                boolean identicalTarget = true;
                for (int i=1 ; i<links.size() ;i++){
                    // we check whether the href value of each node is different
                    // from the href value of the previous one
                    if (!StringUtils.equalsIgnoreCase(
                            links.get(i-1).href, links.get(i).href)) {
                        identicalTarget = false;
                        break;
                    }
                }
                computeSolution(identicalTarget, links, testSolutionHandler);
            }
        }
    }
    
    /**
     * 
     * @param links
     * @param identicalTarget
     * @param testSolutionHandler 
     */
    private void computeSolution(
            boolean identicalTarget, 
            List<Link> links,
            TestSolutionHandler testSolutionHandler) {
        // if two nodes with a different href value are found, a
        // source code remark is thrown for each link of the list
        if (!identicalTarget) {
            for (Link link : links){
                addSourceCodeRemarkForIdenticalLinks(
                        getFailureSolution(),
                        link,
                        identicalLinkDetectedMsg);
            }
            testSolutionHandler.addTestSolution(getFailureSolution());
        // if the expected solution is failed, the result is decidable
        // so the inverse solution of failed is passed
        } else if (getFailureSolution().equals(TestSolution.FAILED)) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
        // if the expected solution is nmi, the result is not decidable
        // so the inverse solution of failed is still nmi
        } else if (getFailureSolution().equals(TestSolution.NEED_MORE_INFO)) {
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
        }
    }
    
    /**
     * This methods search the identical links (link text + title attribute value
     * if it exists) and return a collection of identical links
     * @param nodeList
     * @param hasTitleAttribute
     * @return
     */
    private Collection<List<Link>> sortLinksByIdenticalTextTarget(Elements elements) {
        Map<String, List<Link>> linksSortedByTextTarget = new HashMap<>();

        // For each node of the selection set
        for (Element el : elements) {
            Link link = new Link(el);
            // if the current map contains this key, two identical links
            // have been found
            if (linksSortedByTextTarget.containsKey(link.getLinkText())) {
                linksSortedByTextTarget.get(link.getLinkText()).add(link);
            } else {
                List<Link> linksByTarget = new ArrayList<>();
                linksByTarget.add(link);
                linksSortedByTextTarget.put(link.getLinkText(), linksByTarget);
            }

        }
        // We finally parse the map to only keep identical links (list of links
        // with more than 1 element)
        Collection<List<Link>> finalList = new ArrayList<>();
        for (List<Link> links : linksSortedByTextTarget.values()){
            if (links.size()>1){
                finalList.add(links);
            }
        }
        return finalList;
    }

    /**
     * This method adds a sourceCodeRemark with the text link, the value of
     * the title attribute and the value of the href attribute
     * @param testSolution
     * @param node
     * @param message
     */
    private void addSourceCodeRemarkForIdenticalLinks(
            TestSolution testSolution,
            Link link,
            String message) {

        Collection<EvidenceElement> eeList = new ArrayList<>();
        
        eeList.add(getEvidenceElement(TEXT_ELEMENT2, link.text));
        if (link.title != null) {
            eeList.add(getEvidenceElement(TITLE_ATTR, link.title));
        } else {
            eeList.add(getEvidenceElement(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        }
        if (link.href != null) {
            eeList.add(getEvidenceElement(HREF_ATTR, link.href));
        } else {
            eeList.add(getEvidenceElement(HREF_ATTR, ABSENT_ATTRIBUTE_VALUE));
        }
        eeList.add(getEvidenceElement(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, 
                                     link.getLinkText()));
        addSourceCodeRemark(
                    testSolution, 
                    link.element, 
                    message,
                    eeList);
    }

    /* 
     * inner class that handles all information about the links (href, title, 
     * text)
     */
    private class Link {
        Element element;
        String href;
        String title;
        String text;
        String getLinkText() {
            if (title == null) {
                return text.toLowerCase();
            } else {
                return text.toLowerCase()+ " "+title.toLowerCase();
            }
        }
        public Link(Element element){
            this.element = element;
            this.href = hrefAttrTextBuilder.buildTextFromElement(element);
            this.title = titleAttrTextBuilder.buildTextFromElement(element);
            this.text = linkTextBuilder.buildTextFromElement(element);
        }
    }

}