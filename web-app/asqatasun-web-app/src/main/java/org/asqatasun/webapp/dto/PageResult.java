/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.dto;

import static org.asqatasun.webapp.util.TgolKeyStore.*;

/**
 * This class handles basics displayable data about a page result
 * @author jkowalczyk
 */
public class PageResult {

    public final String url;
    public final String pageResultUrl;
    public final String weightedMark;
    public final String httpStatusCode;
    public final Long id;
    public final String rawMark;
    public final int rank;
    public int getRank(){
        return rank;
    }

    /**
     * Default Constructor
     */
    public PageResult(String url, Integer rank, Float weightedMark, Float rawMark, Long webResourceId, String httpStatusCode) {
        pageResultUrl = RESULT_PAGE_URL_NAME_KEY+"?"+WEBRESOURCE_ID_KEY+"="+webResourceId;
        this.id = webResourceId;
        this.url = url;
        if (rawMark < 0) {
            this.weightedMark = "-1";
            this.rawMark = "-1";
        } else {
            this.weightedMark = String.valueOf(weightedMark.intValue());
            this.rawMark = String.valueOf(rawMark.intValue());
        }
        this.httpStatusCode = String.valueOf(httpStatusCode);
        this.rank = rank;
    }

}
