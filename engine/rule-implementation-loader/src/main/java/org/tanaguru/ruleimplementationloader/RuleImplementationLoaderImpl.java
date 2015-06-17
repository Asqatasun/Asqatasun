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
package org.tanaguru.ruleimplementationloader;

import org.apache.log4j.Logger;
import org.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author jkowalczyk
 */
public class RuleImplementationLoaderImpl implements RuleImplementationLoader {

    private String archiveName;
    private String archiveRoot;
    private String className;
    private RuleImplementation result;

    RuleImplementationLoaderImpl(String archiveRoot, String ruleArchiveName, String ruleClassName) {
        super();
        this.archiveRoot = archiveRoot;
        this.archiveName = ruleArchiveName;
        this.className = ruleClassName;
    }

    @Override
    public String getArchiveName() {
        return archiveName;
    }

    @Override
    public String getArchiveRoot() {
        return archiveRoot;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public RuleImplementation getResult() {
        return result;
    }

    @Override
    public void run() {
        result = loadClass(className, archiveName, archiveRoot);
    }

    @Override
    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    @Override
    public void setArchiveRoot(String archiveRoot) {
        this.archiveRoot = archiveRoot;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    private RuleImplementation loadClass(String className, String archiveName, String archiveRoot) {
        try {
            Logger.getLogger(this.getClass()).debug("Loading " + className + " rule");
            return (RuleImplementation) Class.forName(className).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass()).error(
                    "archiveRoot=" + archiveRoot + ", "
                        + "archiveName=" + archiveName + ", "
                        + "className=" + className, 
                    ex);
            throw new RuntimeException(ex);
        }
    }

}