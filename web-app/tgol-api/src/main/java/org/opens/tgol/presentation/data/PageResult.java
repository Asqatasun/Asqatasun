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
package org.opens.tgol.presentation.data;

/**
 *
 * @author jkowalczyk
 */
public interface PageResult {

    /**
     *
     * @return
     *      the page URL
     */
    String getUrl();

    /**
     * 
     * @param url
     */
    void setUrl(String url);

    /**
     *
     * @return
     *      the page result URL
     */
    String getPageResultUrl();

    /**
     *
     * @param pageResultUrl
     */
    void setPageResultUrl(String pageResultUrl);

    /**
     *
     * @return
     *      the page weighted mark
     */
    String getWeightedMark();

    /**
     *
     * @param mark
     */
    void setWeightedMark(String mark);

    /**
     *
     * @return
     *      the page raw mark
     */
    String getRawMark();

    /**
     *
     * @param rawMark
     */
    void setRawMark(String rawMark);

    /**
     *
     * @return
     *      the page http status code
     */
    String getHttpStatusCode();

    /**
     *
     * @param httpStatusCode
     */
    void setHttpStatusCode(String httpStatusCode);

    /**
     *
     * @return
     *      the page id
     */
    Long getId();

    /**
     *
     * @param id
     */
    void setId(Long id);

    /**
     * 
     * @return 
     */
    int getRank();
    
    /**
     * 
     * @param rank 
     */
    void setRank(int rank);
}