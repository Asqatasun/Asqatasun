/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.rules.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.tanaguru.ruleimplementation.RuleImplementation;
import org.tanaguru.ruleimplementationloader.RuleImplementationLoader;

/**
 *
 * @author lralambomanana
 */
public class RuleImplementationLoaderImpl implements RuleImplementationLoader {

    private String className;
    private RuleImplementation result;

    public RuleImplementationLoaderImpl(String className) {
        super();
        this.className = className;
    }

    @Override
    public String getArchiveName() {
        return null;
    }

    @Override
    public String getArchiveRoot() {
        return null;
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
        result = loadClass(className);
    }

    @Override
    public void setArchiveName(String archiveName) {
    }

    @Override
    public void setArchiveRoot(String ruleImplementationArchveRoot) {
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    public RuleImplementation loadClass(String className) {
        try {
            return (RuleImplementation) Class.forName(className).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RuleImplementationLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}