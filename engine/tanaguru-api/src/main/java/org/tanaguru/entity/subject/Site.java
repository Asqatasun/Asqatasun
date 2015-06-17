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
package org.tanaguru.entity.subject;

import java.util.Collection;

/**
 * 
 * @author jkowalczyk
 */
public interface Site extends WebResource {

    /**
     *
     * @param webResourceList
     *            the Web resource list to add
     */
    void addAllChild(Collection<WebResource> webResourceList);

    /**
     *
     * @param webResource
     *            the Web resource to add
     */
    void addChild(WebResource webResource);

    /**
     *
     * @param url
     *            the URL of the component to find
     * @return true if a component is found
     */
    boolean contains(String url);

    /**
     *
     * @return the components
     */
    Collection<WebResource> getComponentList();

    /**
     *
     * @param components
     *            the components to set
     */
    void setComponentList(Collection<WebResource> components);

}