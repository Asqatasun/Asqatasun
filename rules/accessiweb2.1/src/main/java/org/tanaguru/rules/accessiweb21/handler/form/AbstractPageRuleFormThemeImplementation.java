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
package org.tanaguru.rules.accessiweb21.handler.form;

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleFormThemeImplementation
        extends AbstractPageRuleImplementation {

    protected FormRulesHandler formRulesHandler = new FormRulesHandler();

    public AbstractPageRuleFormThemeImplementation() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        ProcessResult processResult = null;
        formRulesHandler.setSSPHandler(sspHandler);
        formRulesHandler.setProcessRemarkService(sspHandler.getProcessRemarkService());
        return processResult;
    }

    protected void cleanUpRule(){
        formRulesHandler.setSSPHandler(null);
        formRulesHandler.setProcessRemarkService(null);
        formRulesHandler = null;
    }

}
