/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.presentation.data;

import java.util.ResourceBundle;
import org.opens.tgol.util.TgolEscapeUrl;
import org.opens.tgol.util.TgolKeyStore;

/**
 * This class handles basics displayable data about a page result
 * @author jkowalczyk
 */
public class PageResultImpl implements PageResult{

    /**
     *
     */
    public static final String REQUEST_PARAMETER =
            "?"+TgolKeyStore.WEBRESOURCE_ID_KEY+"=";
    /**
     *
     */
    public static final String REPRESENTATION_BUNDLE_NAME = "url";
    private final ResourceBundle representationBundle =
            ResourceBundle.getBundle(REPRESENTATION_BUNDLE_NAME);

    /**
     * The page url
     */
    private String url;
    /**
     *
     * @return
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    @Override
    public final void setUrl(String url) {
        this.url = TgolEscapeUrl.escapeUrl(url);
    }

    /**
     * The page result url
     */
    private String pageResultUrl;
    /**
     *
     * @return
     */
    @Override
    public String getPageResultUrl() {
        return pageResultUrl;
    }

    /**
     *
     * @param pageResultUrl
     */
    @Override
    public void setPageResultUrl(String pageResultUrl) {
        this.pageResultUrl = pageResultUrl;
    }

    /**
     * The weighted mark of the page
     */
    private String weightedMark;
    /**
     *
     * @return
     */
    @Override
    public String getWeightedMark() {
        return weightedMark;
    }

    @Override
    public void setWeightedMark(String weightedMark) {
        this.weightedMark= weightedMark;
    }

    /**
     * The returned http status code
     */
    private String httpStatusCode;
    /**
     *
     * @return
     */
    @Override
    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * 
     */
    private Long id;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The raw mark of the page
     */
    private String rawMark;
    @Override
    public String getRawMark() {
        return rawMark;
    }

    @Override
    public void setRawMark(String rawMark) {
        this.rawMark = rawMark;
    }

    private int rank;
    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    /**
     * Default Constructor
     */
    public PageResultImpl (String url, Integer rank,Float weightedMark, Float rawMark, Long webResourceId, String httpStatusCode) {
        pageResultUrl = representationBundle.getString(TgolKeyStore.RESULT_PAGE_NAME_KEY)+
                REQUEST_PARAMETER+
                webResourceId;
        this.id = webResourceId;
        this.url = url;
        if (rawMark < 0) {
            this.weightedMark = "-1";
            this.rawMark = "-1";
        } else {
            this.weightedMark = String.valueOf(Float.valueOf(weightedMark).intValue());
            this.rawMark = String.valueOf(Float.valueOf(rawMark).intValue());
        }
        this.httpStatusCode = String.valueOf(httpStatusCode);
        this.rank = rank;
    }

}