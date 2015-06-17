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
package org.tanaguru.webapp.report.pagination;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.displaytag.decorator.TableDecorator;
import org.tanaguru.webapp.presentation.data.PageResult;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * This wrapper class extends the TableDecorator class of the displayTag API to
 * customise the render of the displayed table regarding tgol specifications.
 *
 * @author jkowalczyk
 */
public class PageListWrapper extends TableDecorator {

    private static final String I18N_FILE_KEY = "i18n/page-list-page-I18N";
    private static final String PAGE_DETAILED_RESULT_KEY = "pageList.pageDetailedResult";
    private static final String NO_DETAILED_RESULT_KEY = "pageList.noDetailedResult";
    private static final String GO_TO_KEY = "pageList.goTo";
    private static final String FOR_KEY = "pageList.for";
    private static final String NOT_TESTED = "pageList.notTested";
    private static final String EXTERNAL_LINK_IMG_URL_ATTR_KEY = "externalLinkImgUrl";
    private static final char PERCENT_CHAR = '%';

    /**
     * Default Constructor
     */
    public PageListWrapper() {
        super();
    }

    /**
     * Decoration of the displayed raw mark to add the appropriate grade css
     * class.
     *
     * @return
     */
    public String getRawMark() {
        Object lObject = this.getCurrentRowObject();
        if (lObject instanceof PageResult && !((PageResult) lObject).getRawMark().equalsIgnoreCase("-1")) {
            return "<div> " + ((PageResult) lObject).getRawMark() + PERCENT_CHAR + " </div>";
        }
        return getResourceBundle(this.getPageContext().getRequest()).getString(NOT_TESTED);
    }

    /**
     * Decoration of the displayed raw mark to add the appropriate grade css
     * class.
     *
     * @return
     */
    public String getWeightedMark() {
        Object lObject = this.getCurrentRowObject();
        if (lObject instanceof PageResult && !((PageResult) lObject).getWeightedMark().equalsIgnoreCase("-1")) {
            return "<div>" + ((PageResult) lObject).getWeightedMark() + PERCENT_CHAR + " </div>";
        }
        return "";
    }

    /**
     * Decoration of the displayed raw mark to add the appropriate grade css
     * class.
     *
     * @return
     */
    public String getUrlWithPageResultLinkAndExternalLink() {
        Object lObject = this.getCurrentRowObject();
        ResourceBundle resourceBundle = getResourceBundle(this.getPageContext().getRequest());

        PageResult pageResult = null;
        if (lObject instanceof PageResult) {
            pageResult = ((PageResult) lObject);
        }
        StringBuilder strb = new StringBuilder();
        strb.append(buildExternalLinkUrl(pageResult.getUrl(), resourceBundle));
        strb.append(getUrlLink(pageResult, resourceBundle));
        return strb.toString();
    }

    /**
     *
     * @return
     */
    public String getUrlWithExternalLink() {
        Object lObject = this.getCurrentRowObject();
        ResourceBundle resourceBundle = getResourceBundle(this.getPageContext().getRequest());

        PageResult pageResult = null;
        if (lObject instanceof PageResult) {
            pageResult = ((PageResult) lObject);
        }

        StringBuilder strb = new StringBuilder();
        strb.append(buildExternalLinkUrl(pageResult.getUrl(), resourceBundle));
        strb.append(pageResult.getUrl());
        return strb.toString();
    }

    private String buildExternalLinkUrl(
            String url,
            ResourceBundle resourceBundle) {
        StringBuilder strb = new StringBuilder();
        strb.append("<span class=\"open-external-url-icon\">");
        strb.append("<a href=\"");
        strb.append(url);
        strb.append("\" title=\"");
        strb.append(getUrlLinkTitle(resourceBundle, url));
        strb.append("\"><img src=\"");
        strb.append(this.getPageContext().getAttribute(EXTERNAL_LINK_IMG_URL_ATTR_KEY));
        strb.append("\" alt=\"");
        strb.append(getUrlLinkTitle(resourceBundle, url));
        strb.append("\" /></a></span>");
        return strb.toString();
    }

    /**
     * Decoration of the detailed result link to add internationalised link with
     * appropriate internationalised title
     *
     * @return
     */
    public String getUrlLink(
            PageResult pageResult,
            ResourceBundle resourceBundle) {

        int lId = pageResult.getId().intValue();
        String url = pageResult.getUrl().replaceAll("&", "&amp;");
        Integer mark = Integer.valueOf(pageResult.getRawMark());
        if (mark > 0) {
            StringBuilder strb = new StringBuilder("<a href=\"page-result.html?wr=");
            strb.append(lId);
            String testParameter = this.getPageContext().getRequest().getParameter("test");
            if (StringUtils.isNotBlank(testParameter)) {
                strb.append("#test-");
                strb.append(testParameter);
                strb.append("\" title=\"");
            } else {
                strb.append("\" title=\"");
            }
            strb.append(getTitle(resourceBundle, url));
            strb.append("\">");
            strb.append(url);
            strb.append("</a>");
            return strb.toString();
        } else {
            return url + " (" + getNoDetailedResult(resourceBundle) + ")";
        }
    }

    /**
     * This method resolves the language through the request.
     *
     * @param request
     * @return
     */
    private ResourceBundle getResourceBundle(ServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(I18N_FILE_KEY);;
        if (request instanceof HttpServletRequest) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver((HttpServletRequest) request);

            if (localeResolver != null) {
                // get current locale
                Locale locale = localeResolver.resolveLocale((HttpServletRequest) request);
                resourceBundle = ResourceBundle.getBundle(I18N_FILE_KEY, locale);
            }
        }
        return resourceBundle;
    }

    /**
     *
     * @param resourceBundle
     * @return
     */
    private String getNoDetailedResult(ResourceBundle resourceBundle) {
        return resourceBundle.getString(NO_DETAILED_RESULT_KEY);
    }

    /**
     *
     * @param resourceBundle
     * @return
     */
    private String getLink(ResourceBundle resourceBundle) {
        return resourceBundle.getString(PAGE_DETAILED_RESULT_KEY);
    }

    /**
     *
     * @param resourceBundle
     * @return
     */
    private String getUrlLinkTitle(ResourceBundle resourceBundle, String url) {
        return resourceBundle.getString(GO_TO_KEY)
                + " "
                + url;
    }

    /**
     *
     * @param resourceBundle
     * @param url
     * @return
     */
    private String getTitle(ResourceBundle resourceBundle, String url) {
        return getLink(resourceBundle)
                + " "
                + resourceBundle.getString(FOR_KEY)
                + " "
                + url;
    }
}