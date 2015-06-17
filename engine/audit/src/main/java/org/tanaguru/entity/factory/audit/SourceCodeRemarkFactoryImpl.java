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

import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.SourceCodeRemarkImpl;
import org.tanaguru.entity.audit.TestSolution;

/**
 * 
 * @author jkowalczyk
 */
public class SourceCodeRemarkFactoryImpl implements SourceCodeRemarkFactory {

    public SourceCodeRemarkFactoryImpl() {
        super();
    }

    @Override
    public SourceCodeRemark create() {
        return new SourceCodeRemarkImpl();
    }

    @Override
    public SourceCodeRemark create(TestSolution issue, String messageCode) {
        SourceCodeRemark sourceCodeRemark = create();
        sourceCodeRemark.setIssue(issue);
        sourceCodeRemark.setMessageCode(messageCode);

        return sourceCodeRemark;
    }
    
    @Override
    public SourceCodeRemark create(String target, TestSolution issue, String messageCode, int lineNumber) {
        SourceCodeRemark sourceCodeRemark = create(issue, messageCode);
        sourceCodeRemark.setTarget(target);
        sourceCodeRemark.setLineNumber(lineNumber);
        
        return sourceCodeRemark;
    }
}