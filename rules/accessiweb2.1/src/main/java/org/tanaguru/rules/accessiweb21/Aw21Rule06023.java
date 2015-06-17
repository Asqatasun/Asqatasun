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
package org.tanaguru.rules.accessiweb21;

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.accessiweb21.handler.link.AbstractPageRuleLinkThemeImplementation;
import org.tanaguru.rules.accessiweb21.handler.link.LinkRulesHandler;

/**
 * This rule tests whether the title attribute of a clickable area
 * (&lt;area&gt; tag within a &lt;map&gt; tag) is pertinent.
 * To do so, we check whether the content of the title attribute is empty,
 * contains only non-alphanumerical characters, and belongs to the text link
 * blacklist.
 * If one of these conditions is fulfilled, the test returns FAILED.
 * If the page doesn't contain any &lt;a&gt; tag containing a &lt;img&gt;
 * or an &lt;object&gt; tag, the test returns NOT_APPLICABLE.
 * In all the other cases, the test returns NEED_MORE_INFORMATIONS.
 * @author jkowalczyk
 */
public class Aw21Rule06023 extends AbstractPageRuleLinkThemeImplementation {

    private static final String XPATH_EXPR =
            "//AREA[@title and "+LinkRulesHandler.CLICKABLE_AREA+
            LinkRulesHandler.END_BRACKET;
    
    public Aw21Rule06023() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);

        sspHandler.beginSelection().
                 domXPathSelectNodeSet(XPATH_EXPR);
        TestSolution testSolution = linkRulesHandler.
                 checkAttributePertinence(
                     NodeAndAttributeKeyStore.TITLE_ATTR,
                     true);

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());

        cleanUpRule();
        return processResult;
    }
    
}