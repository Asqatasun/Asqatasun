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

import org.tanaguru.contentadapter.HTMLCleaner;
import org.tanaguru.contentadapter.util.DocumentCaseInsensitiveAdapter;

/**
 *
 * @author lralambomanana
 */
public class HTMLCorrectorImpl implements HTMLCleaner {

    private String dirtyHTML;
    private String result;
    private boolean upperCaseTags;

    public HTMLCorrectorImpl(boolean upperCaseTags) {
        super();
        this.upperCaseTags = upperCaseTags;
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
    public void run() {
        if (upperCaseTags) {
            result = DocumentCaseInsensitiveAdapter.removeLowerCaseTags(dirtyHTML);
        } else {
            result = dirtyHTML;
        }
    }

    @Override
    public void setDirtyHTML(String dirtyHTML) {
        this.dirtyHTML = dirtyHTML;
    }

    @Override
    public String getCorrectorName() {
        return "Mock";
    }

}