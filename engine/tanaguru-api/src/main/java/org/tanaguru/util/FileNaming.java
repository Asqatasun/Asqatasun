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
package org.tanaguru.util;

/**
 *
 * @author jkowalczyk
 */
public class FileNaming {

    private static final String FILE_PROTOCOL_PREFIX="file://";
    private static final String HTTP_PROTOCOL_PREFIX = "http://";
    private static final String HTTPS_PROTOCOL_PREFIX = "https://";
    private static final String FILE_PREFIX = "/";

    /**
     * Default private constructor
     */
    private FileNaming() {}

    /**
     * This method add the protocol to the url if the protocol is missing
     * @param url
     * @return
     */
    public static String addProtocolToUrl(String url) {
        if (!url.startsWith(HTTP_PROTOCOL_PREFIX)
                && !url.startsWith(HTTPS_PROTOCOL_PREFIX)
                && !url.startsWith(FILE_PROTOCOL_PREFIX)) {

            if (url.startsWith(FILE_PREFIX)) {
                url = FILE_PROTOCOL_PREFIX + url;
            } else {
                url = HTTP_PROTOCOL_PREFIX + url;
            }
        }
        return url;
    }

    /**
     * Removes the prefix for an url of file type (i.e. file:///file.txt)
     * @param url
     * @return
     */
    public static String removeFilePrefix(String url){
        if (url.startsWith(FILE_PROTOCOL_PREFIX)) {
            return url.substring(FILE_PROTOCOL_PREFIX.length());
        }
        return url;
    }

}