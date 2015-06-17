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
package org.tanaguru.contentadapter.util;

import java.util.HashSet;
import java.util.Set;
import org.tanaguru.contentadapter.Resource;
import org.tanaguru.contentadapter.RsrcLocator;

/**
 * 
 * @author jkowalczyk
 */
public abstract class AbstractResource implements Resource {

    private int lineNumber;
    private RsrcLocator location;
    private String resource;
    private Set resourceSet;

    public AbstractResource() {
        super();
        this.resourceSet = new HashSet();
    }

    public AbstractResource(String resource, int lineNumber,
            RsrcLocator location) {
        super();
        this.resource = resource;
        this.lineNumber = lineNumber;
        this.location = location;
        this.resourceSet = new HashSet();
    }

    @Override
    public void addAllResource(Set resourceSet) {
        this.resourceSet.addAll(resourceSet);
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String getResource() {
        return resource;
    }

    @Override
    public Set getResourceSet() {
        return resourceSet;
    }

    @Override
    public RsrcLocator getRsrcLocator() {
        return location;
    }

    @Override
    public void setResource(String resource) {
        this.resource = resource;
    }

}