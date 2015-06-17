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
package org.tanaguru.contentadapter.css;

import org.tanaguru.contentadapter.RsrcLocator;
import org.tanaguru.contentadapter.util.AbstractResource;

/**
 * 
 * @author jkowalczyk
 */
public class CSSResourceImpl extends AbstractResource  {

    private final String resourceName = "#StyleSheet";
    private final int resourceType = 1;

    public CSSResourceImpl() {
        super();
    }

    public CSSResourceImpl(
            String resource,
            int lineNumber,
            RsrcLocator location) {

        super(resource, lineNumber, location);
    }

    @Override
    public String getRsrcName() {
        return resourceName;
    }

    @Override
    public short getRsrcType() {
        return resourceType;
    }

}