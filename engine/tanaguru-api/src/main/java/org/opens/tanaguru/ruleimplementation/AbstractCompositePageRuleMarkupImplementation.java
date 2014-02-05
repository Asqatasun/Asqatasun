/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
package org.opens.tanaguru.ruleimplementation;

import java.util.Collection;
import org.opens.tanaguru.processor.SSPHandler;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with page scope. It aggregates {@link AbstractPageRuleMarkupImplementation}
 * instances and call their selectAndCheck method recursively.
 * 
 * @author jkowalczyk
 */
public abstract class AbstractCompositePageRuleMarkupImplementation 
            extends AbstractPageRuleDefaultImplementation  {

    private Collection<AbstractPageRuleMarkupImplementation> innerRuleCheckers;
    public void setInnerRuleCheckers(Collection<AbstractPageRuleMarkupImplementation> innerRuleCheckers) {
        this.innerRuleCheckers = innerRuleCheckers;
    }
    
    /**
     * Default constructor
     * @param innerRuleCheckers 
     */
    public AbstractCompositePageRuleMarkupImplementation(){
        super();
    }
    
    @Override
    protected void selectAndCheck(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
        assert innerRuleCheckers != null;
        for (AbstractPageRuleMarkupImplementation checker : innerRuleCheckers) {
            setServicesToRuleChecker(checker);
            checker.selectAndCheck(sspHandler, testSolutionHandler);
        }
    }
    
    @Override
    protected int getSelectionSize() {
        int selectionSize = 0;
        for (AbstractPageRuleMarkupImplementation innerRule : innerRuleCheckers) {
            selectionSize += innerRule.getSelectionSize();
        }
        return selectionSize;
    }

    /**
     * 
     * @param checker 
     */
    private void setServicesToRuleChecker(AbstractPageRuleMarkupImplementation checker) {
        checker.setTest(test);
        checker.setNomenclatureLoaderService(nomenclatureLoaderService);
        checker.setDefiniteResultFactory(definiteResultFactory);
        checker.setIndefiniteResultFactory(indefiniteResultFactory);
    }

}