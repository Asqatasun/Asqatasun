/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
package org.opens.tanaguru.rules.accessiweb21;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public class Aw21Rule10041 extends AbstractPageRuleImplementation {

    public Aw21Rule10041() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        Nomenclature blacklist = 
                nomenclatureLoaderService.loadByCode("RelativeCssUnits");

        Nomenclature mediaList =
                nomenclatureLoaderService.loadByCode("MediaListNotAcceptingRelativeUnits");

        TestSolution checkResult = sspHandler.beginSelection().
                keepRulesWithMedia(mediaList.getValueList()).
                checkRelativeUnitExists(blacklist.getIntegerValueList());

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                checkResult,
                sspHandler.getRemarkList());
        result.setElementCounter(sspHandler.getCssSelectorNumber());
        return result;
    }

}