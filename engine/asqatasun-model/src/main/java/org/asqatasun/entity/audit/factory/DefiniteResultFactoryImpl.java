/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.entity.audit.factory;

import org.asqatasun.entity.audit.DefiniteResult;
import org.asqatasun.entity.audit.DefiniteResultImpl;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.subject.WebResource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 
 * @author jkowalczyk
 */
@Component("definiteResultFactory")
public class DefiniteResultFactoryImpl implements DefiniteResultFactory {

    @Override
    public DefiniteResult create() {
        return new DefiniteResultImpl();
    }

    @Override
    public DefiniteResult create(Test test, WebResource subject) {
        DefiniteResult definiteResult = this.create();
        definiteResult.setTest(test);
        definiteResult.setSubject(subject);
        return definiteResult;
    }

    @Override
    public DefiniteResult create(Test test, WebResource subject,
            TestSolution value, Collection<ProcessRemark> remarkSet) {
        DefiniteResult definiteResult = this.create();
        definiteResult.setTest(test);
        definiteResult.setSubject(subject);
        definiteResult.setDefiniteValue(value);
        definiteResult.addAllRemark(remarkSet);
        return definiteResult;
    }

    @Override
    public DefiniteResult create(
            Test test, 
            WebResource subject, 
            TestSolution value, 
            int elementCounter) {
        DefiniteResult definiteResult = create(test, subject, value, null);
        definiteResult.setElementCounter(elementCounter);
        return definiteResult;
    }

    @Override
    public DefiniteResult create(
            Test test, 
            WebResource subject, 
            TestSolution value, 
            int elementCounter, 
            Collection<ProcessRemark> remarkSet) {
        DefiniteResult definiteResult = create(test, subject, value, remarkSet);
        definiteResult.setElementCounter(elementCounter);
        return definiteResult;
    }

}
