/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.entity.factory.audit;

import java.util.Collection;
import org.asqatasun.entity.audit.IndefiniteResult;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author jkowalczyk
 */
public interface IndefiniteResultFactory extends
        GenericFactory<IndefiniteResult> {

    /**
     *
     * @param test
     *            the test to set
     * @param subject
     *            the subject to set
     * @param value
     *            the value to set
     * @return a new instance of IndefiniteResult
     */
    IndefiniteResult create(Test test, WebResource subject, String value);

    /**
     *
     * @param test
     *            the test to set
     * @param subject
     *            the subject to set
     * @param value
     *            the value to set
     * @param remarkList
     *            the remark list to set
     * @return a new instance of IndefiniteResult
     */
    IndefiniteResult create(Test test, WebResource subject, String value,
            Collection<ProcessRemark> remarkList);
}
