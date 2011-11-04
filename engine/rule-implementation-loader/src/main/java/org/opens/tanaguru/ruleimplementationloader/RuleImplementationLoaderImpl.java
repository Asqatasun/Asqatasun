/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
package org.opens.tanaguru.ruleimplementationloader;

import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            URL rulesPackagesRootURL = new File(archiveRoot + File.separator + archiveName + ".jar").toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{rulesPackagesRootURL}, this.getClass().getClassLoader());
            return (RuleImplementation) Class.forName(className, true,
                    classLoader).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, "archiveRoot=" + archiveRoot + ", archiveName=" + archiveName + ", className=" + className, ex);
            throw new RuntimeException(ex);
        }
    }

}