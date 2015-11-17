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
package org.asqatasun.rules.accessiweb21;

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.rules.accessiweb21.handler.list.AbstractPageRuleListThemeImplementation;


/**
 * On each Web page, does information grouped in ordered lists use the ol and
 * li tags?
 * To do so, we search in the page the following pattern :
 * &lt;p&gt;
 *     1(or A or I) text
 *     &ltbr/&gt;
 *     2(or B or II) text
 *     &ltbr/&gt;
 *     ......
 * &lt;/p&gt;
 * @author jkowalczyk
 */
public class Aw21Rule09022 extends AbstractPageRuleListThemeImplementation {

    public Aw21Rule09022() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);

        listRulesHandler.checkSuspectedList(
                false,
                "SuspectedNotWellFormattedOrdererdList");

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                TestSolution.NEED_MORE_INFO,
                sspHandler.getRemarkList());
        cleanUpRule();
        return processResult;
    }

}