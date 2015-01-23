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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.accessiweb21.handler.link;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleLinkThemeImplementation
        extends AbstractPageRuleImplementation {

    protected LinkRulesHandler linkRulesHandler =  new LinkRulesHandler();

    public AbstractPageRuleLinkThemeImplementation() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        ProcessResult processResult = null;
        linkRulesHandler.setNomenclatureLoaderService(nomenclatureLoaderService);
        linkRulesHandler.setSSPHandler(sspHandler);
        linkRulesHandler.setProcessRemarkService(sspHandler.getProcessRemarkService());
        return processResult;
    }

    protected void cleanUpRule(){
        linkRulesHandler.setSSPHandler(null);
        linkRulesHandler.setProcessRemarkService(null);
        linkRulesHandler = null;
    }

}
