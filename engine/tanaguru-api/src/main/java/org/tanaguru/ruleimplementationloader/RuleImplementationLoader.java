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

import org.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author jkowalczyk
 */
public interface RuleImplementationLoader {

    /**
     *
     * @return
     *      the archive that embeds the rule implementation
     */
    String getArchiveName();

    /**
     *
     * @return
     *      the root path of the archive that embeds the rule implementation
     */
    String getArchiveRoot();

    /**
     *
     * @return
     *      the name of the class that realizes the concrete implementation of the
     *      rule
     */
    String getClassName();

    /**
     * 
     * @return
     */
    RuleImplementation getResult();

    /**
     *
     */
    void run();

    /**
     *
     * @param archiveName
     */
    void setArchiveName(String archiveName);

    /**
     *
     * @param ruleImplementationArchveRoot
     */
    void setArchiveRoot(String ruleImplementationArchveRoot);

    /**
     *
     * @param className
     */
    void setClassName(String className);

}