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
package org.asqatasun.rules.test;

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.subject.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

/**
 *
 * @author lralambomanana
 */
@ActiveProfiles("test")
public abstract class AbstractSiteRuleWithPageResultImplementationTestCase extends AbstractRuleImplementationTestCase {

    @Autowired
    Environment env;

    public AbstractSiteRuleWithPageResultImplementationTestCase(
            String testName,
            String inputDataFileName,
            String testcasesFilePath, 
            String referential) {
        super(testName,inputDataFileName,testcasesFilePath);
    }
    
    public Collection<ProcessResult> consolidateWithListAsReturn(String webResourceKey) {
        System.out.println(this + "::consolidate(\"" + webResourceKey + "\")");
        WebResource webResource = getWebResourceMap().get(webResourceKey);
        Collection<ProcessResult> netResultList = getConsolidatorService().consolidate(getGrossResultMap().get(webResource), getTestList());
//        netResultMap.put(webResource, netResult);
        return netResultList;
    }

}
