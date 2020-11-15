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
package org.asqatasun.ruleimplementationloader;

import org.asqatasun.ruleimplementation.RuleImplementation;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jkowalczyk
 */
public class RuleImplementationLoaderImpl implements RuleImplementationLoader {

    private String archiveName;
    private String className;
    private RuleImplementation result;

    RuleImplementationLoaderImpl(String ruleArchiveName, String ruleClassName) {
        super();
        this.archiveName = ruleArchiveName;
        this.className = ruleClassName;
    }

    @Override
    public RuleImplementation getRuleImplementation() {
        return result;
    }

    @Override
    public void run() {
        result = loadClass(className, archiveName);
    }

    private RuleImplementation loadClass(String className, String archiveName) {
        try {
            LoggerFactory.getLogger(this.getClass()).debug("Loading " + className + " rule");
            return (RuleImplementation) Class.forName(className).newInstance();
        } catch (Exception ex) {
            LoggerFactory.getLogger(this.getClass()).error(
                    "archiveName=" + archiveName + ", "
                        + "className=" + className, 
                    ex);
            throw new RuntimeException(ex);
        }
    }

}
