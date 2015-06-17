/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.entity.factory.audit;

import java.util.Collection;
import org.tanaguru.entity.audit.IndefiniteResult;
import org.tanaguru.entity.audit.IndefiniteResultImpl;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public class IndefiniteResultFactoryImpl implements IndefiniteResultFactory {

    public IndefiniteResultFactoryImpl() {
        super();
    }

    @Override
    public IndefiniteResult create() {
        return new IndefiniteResultImpl();
    }

    @Override
    public IndefiniteResult create(Test test, WebResource subject, String value) {
        IndefiniteResult instance = this.create();
        instance.setTest(test);
        instance.setSubject(subject);
        instance.setIndefiniteValue(value);
        return instance;
    }

    @Override
    public IndefiniteResult create(Test test, WebResource subject,
            String value, Collection<ProcessRemark> remarkList) {
        IndefiniteResult instance = this.create();
        instance.setTest(test);
        instance.setSubject(subject);
        instance.setIndefiniteValue(value);
        instance.addAllRemark(remarkList);
        return instance;
    }

}