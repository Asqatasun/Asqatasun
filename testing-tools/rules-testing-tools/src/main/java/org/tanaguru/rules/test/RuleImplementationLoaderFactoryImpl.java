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

import org.tanaguru.ruleimplementationloader.RuleImplementationLoader;
import org.tanaguru.ruleimplementationloader.RuleImplementationLoaderFactory;

/**
 *
 * @author enzolalay
 */
public class RuleImplementationLoaderFactoryImpl implements RuleImplementationLoaderFactory {

    @Override
    public RuleImplementationLoader create(String archiveRoot, String ruleArchiveName, String ruleClassName) {
        return new RuleImplementationLoaderImpl(ruleClassName);
    }

}