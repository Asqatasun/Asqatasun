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
package org.tanaguru.contentadapter.html;

import org.tanaguru.contentadapter.HTMLCleaner;

/**
 * 
 * @author jkowalczyk
 */
public abstract class AbstractHTMLCleaner implements HTMLCleaner {

    protected String dirtyHTML;
    protected String result;

    public AbstractHTMLCleaner() {
        super();
    }

    @Override
    public String getDirtyHTML() {
        return dirtyHTML;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void setDirtyHTML(String dirtyHTML) {
        this.dirtyHTML = dirtyHTML;
        result= null;
    }

}