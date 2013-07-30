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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.RuleHelper;
import org.opens.tanaguru.rules.seo.handler.link.AbstractPageRuleLinkThemeImplementation;
import org.opens.tanaguru.rules.seo.handler.link.LinkRulesHandler;

/**
 * Is each link text such as a clickable area (content of the alt attribute
 * of an area tag) explicit out of context (except in special cases)?
 * 
 * @author jkowalczyk
 */
public class SeoRule05013 extends AbstractPageRuleLinkThemeImplementation{

    /**
     * xpath expression to get area tags without context and without title
     */
    public static final String XPATH_EXPR =
        LinkRulesHandler.AREA_SELECTOR +
        LinkRulesHandler.CLICKABLE_AREA +
        LinkRulesHandler.END_BRACKET;


    public SeoRule05013() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        resultSet.add(linkRulesHandler.checkContextPertinence(
                XPATH_EXPR,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_LINK_OUT_OF_CONTEXT,
                processRemarkList));

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                processRemarkList);

        cleanUpRule();
        return processResult;
    }

}