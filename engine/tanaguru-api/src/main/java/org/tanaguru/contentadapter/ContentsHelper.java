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
package org.tanaguru.contentadapter;

import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.audit.SSP;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Utility class that provides utility methods to deal with Content entities.
 * 
 * @author jkowalczyk
 */
public abstract class ContentsHelper {

    /**
     *
     * @param contentList
     *            the content list to extract SSP list from
     * @return the list of SSP from the content list
     */
    public static Collection<SSP> filterSSP(Collection<Content> contentList) {
        Collection<SSP> sspList = new ArrayList<SSP>();
        for (Content content : contentList) {
            if (content instanceof SSP) {
                sspList.add((SSP)content);
            }
        }
        return sspList;
    }

}