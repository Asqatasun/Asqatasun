/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.seo;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.opens.tanaguru.rules.elementselector.AreaElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.ALT_MISSING_MSG;

/**
 * This rule tests if an area tag defined in a map tag, associated with the usemap
 * attribute of an img tag, has an alt attribute
 * @author jkowalczyk
 */
public class SeoRule02012 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public SeoRule02012() {
        super(
                new AreaElementSelector(), 
                new AttributePresenceChecker(
                        ALT_ATTR, 
                        // passed when attribute is found
                        TestSolution.PASSED, 
                        // failed when attribute is not found
                        TestSolution.FAILED, 
                        // no message created when attribute is found
                        null, 
                        // message associated with element when attribute is not found
                        ALT_MISSING_MSG,
                        // evidence elements,
                        HREF_ATTR)
            );
    }

//extends AbstractPageRuleImplementation {
//
//    public static final String HREF_EVIDENCE_ELEMENT = "href";
//    public static final String CSS_EXPR = 
//            "map[name] area";
//    public static final String CSS2_EXPR = 
//            "img[usemap]";
//
//    public static final String ALT_ATTRIBUTE = "alt";
//
//    public SeoRule02012(){
//        super();
//    }
//
//    @Override
//    protected ProcessResult processImpl(SSPHandler sspHandler) {
//        Elements imageMapElement = sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(CSS2_EXPR).getSelectedElements();
//        Elements areaElement = sspHandler.domCssLikeSelectNodeSet(CSS_EXPR).getSelectedElements();
//
//        Iterator<Element> iter = areaElement.iterator();
//        while (iter.hasNext()) {
//            Element element = iter.next();
//            String mapParentNameValue = element.parent().attr("name");
//            boolean hasRelativeImage = false;
//            for (Element imgElement : imageMapElement) {
//                if (StringUtils.equals(imgElement.attr("usemap"),"#"+mapParentNameValue) || 
//                        StringUtils.equals(imgElement.attr("usemap"),mapParentNameValue)) {
//                    hasRelativeImage = true;
//                    break;
//                } 
//            }
//            if (!hasRelativeImage) {
//                iter.remove();
//            }
//        }
//
//        TestSolution checkResult = TestSolution.PASSED;
//        if (areaElement.isEmpty()) {
//            checkResult = TestSolution.NOT_APPLICABLE;
//        }
//        for(Element el : areaElement) {
//            if (!el.hasAttr("alt")) {
//                checkResult = TestSolution.FAILED;
//                sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(checkResult, el, "altMissing");
//            }
//        }
//
//        DefiniteResult result = definiteResultFactory.create(
//                test,
//                sspHandler.getSSP().getPage(),
//                checkResult,
//                sspHandler.getRemarkList());
//
//        result.setElementCounter(areaElement.size());
//        
//        return result;
//    }
}
