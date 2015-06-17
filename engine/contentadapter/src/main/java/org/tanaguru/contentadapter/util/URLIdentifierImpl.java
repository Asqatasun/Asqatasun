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

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 *
 * @author jkowalczyk
 */
public class URLIdentifierImpl implements URLIdentifier {

    private URL absolutePath;
    private URL url;

    public URLIdentifierImpl() {
        super();
    }

    @Override
    public URL getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public URL resolve(String path) {
        try {
            absolutePath = new URL(url, path);
        } catch (MalformedURLException ex) {
            Logger.getLogger(this.getClass()).error("Url : " +url + " Path : " + path + " " +ex);
        }
        return absolutePath;
    }

    @Override
    public void setUrl(URL url) {
        this.url = url;
    }

}