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
package org.tanaguru.webapp.report.pagination.factory;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.tanaguru.webapp.report.pagination.TgolPaginatedListImpl;
import org.tanaguru.webapp.entity.service.statistics.StatisticsDataService;
import org.tanaguru.webapp.util.HttpStatusCodeFamily;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This private class is an implementation of the PaginatedList interface
 * of the display tag API. This class is needed to realize sorting and
 * pagination on server side.
 *
 * @author jkowalczyk
 */
public final class TgolPaginatedListFactory {

    private static final Logger LOGGER = Logger.getLogger(TgolPaginatedListFactory.class);

    /*
     * Sort criterion values
     */
    private final String default2xxSortCriterion = "rawMark";
    /**
     *
     * @return
     */
    public String getDefault2xxSortCriterion() {
        return default2xxSortCriterion;
    }
    
    private final String default3xxSortCriterion = "httpStatusCode";
    /**
     * 
     * @return
     */
    public String getDefault3xxSortCriterion() {
        return default3xxSortCriterion;
    }

    private final String urlSortCriterion = "url";
    /**
     *
     * @return
     */
    public String getUrlSortCriterion() {
        return urlSortCriterion;
    }
    
    private final String rankCriterion = "rank";
    /**
     *
     * @return
     */
    public String getRankCriterion() {
        return rankCriterion;
    }

    /*
     * Defaults values for page size and page number
     */
    private final int defaultPageSize = 50;
    /**
     *
     * @return
     */
    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    private final int defaultPageNumber = 1;
    /**
     *
     * @return
     */
    public int getDefaultPageNumber() {
        return defaultPageNumber;
    }

    /*
     * Keys for logging.
     */
    private static final String BAD_STR = "Bad ";
    private static final String PARAMETER_STR = " parameter : ";
    private static final String UNBOUNDED_STR = "Unbounded ";
    private static final String USE_DEFAULT_STR = ".Use default value : ";

    /*
     * Parameters List
     */
    /**
     *
     */
    public static final String PAGE_SIZE_PARAM = "pageSize";
    /**
     *
     */
    public static final String SORT_DIRECTION_PARAM = "sortDirection";
    /**
     *
     */
    public static final String SORT_CRITERION_PARAM = "sortCriterion";
    /**
     *
     */
    public static final String SORT_CONTAINING_URL_PARAM = "sortByContainingUrl";
    /**
     *
     */
    public static final String PAGE_PARAM = "page";
    /**
     *
     */
    public static final String INVALID_TEST_PARAM = "test";

    /**
     * Unique instance of TgolPaginatedListFactory
     */
    private static TgolPaginatedListFactory tgolPaginatedListFactory = null;

    private StatisticsDataService statisticsDataService;
    /**
     *
     * @param statisticsDataService
     */
    @Autowired
    public void setStatisticsDataService(StatisticsDataService statisticsDataService) {
        this.statisticsDataService = statisticsDataService;
    }


    /**
     * Private constructor for the singleton class
     */
    private TgolPaginatedListFactory(){}

    /**
     *
     * @return
     */
    public static synchronized TgolPaginatedListFactory getInstance(){
        if (tgolPaginatedListFactory == null) {
            tgolPaginatedListFactory = new TgolPaginatedListFactory();
        }
        return tgolPaginatedListFactory;
    }

    /**
     *
     * @param httpStatusCode
     * @param pageSize
     * @param sortDirection
     * @param sortCriterion
     * @param pageNumber
     * @param containingValue
     * @param invalidTestLabel
     * @param authorizedPageSize
     * @param authorizedSortCriterion
     * @param idAudit
     * @return
     */
    public PaginatedList getPaginatedList(
            HttpStatusCodeFamily httpStatusCode,
            String pageSize,
            String sortDirection,
            String sortCriterion,
            String pageNumber,
            String containingValue,
            String invalidTestLabel,
            Collection<Integer> authorizedPageSize,
            Collection<String> authorizedSortCriterion,
            long idAudit) {

        // get the total number of pages for a given httpStatusCode family and
        // an url filter
        int totalNumberOfElements = statisticsDataService.getWebResourceCountByAuditAndHttpStatusCode(
                idAudit,
                httpStatusCode,
                invalidTestLabel,
                containingValue).intValue();

        // we check the parameters of the request and populate the pageResultList object
        PaginatedList paginatedList = initialisePaginatedListParameters(
                httpStatusCode,
                totalNumberOfElements,
                pageSize,
                sortDirection,
                sortCriterion,
                pageNumber,
                authorizedPageSize,
                authorizedSortCriterion);

        // sql request with the given parameters
        int startElement = (paginatedList.getPageNumber() - 1) * paginatedList.getObjectsPerPage();
        ((TgolPaginatedListImpl)paginatedList).addAllElements(
                statisticsDataService.getPageListByAuditAndHttpStatusCode(
                idAudit,
                httpStatusCode,
                invalidTestLabel,
                startElement,
                paginatedList.getObjectsPerPage(),
                paginatedList.getSortDirection(),
                paginatedList.getSortCriterion(),
                containingValue));
        return paginatedList;
    }

    /**
     * This methods checks the parameters passed to the page that deals with the
     * pagination.
     * The sort value can be set to 1 or 2. If out of bound, set 1 (default)
     * The page size value can be set to 1 or 2. If out of bound, set 1 (default)
     * @param paginatedList
     * @param request
     */
    private PaginatedList initialisePaginatedListParameters(
            HttpStatusCodeFamily httpStatusCode,
            int totalNumberOfElements,
            String pageSize,
            String sortDirection,
            String sortCriterion,
            String pageNumber,
            Collection<Integer> authorizedPageSize,
            Collection<String> authorizedSortCriterion) {

        TgolPaginatedListImpl paginatedList;
        // depending on the httpStatusCodeFamily, the default sort Criterion is
        // different : Mark for the 2xx and HttpStatusCode for the others
        if (httpStatusCode.equals(HttpStatusCodeFamily.f2xx)) {
            paginatedList = new TgolPaginatedListImpl(default2xxSortCriterion);
        } else {
            paginatedList = new TgolPaginatedListImpl(default3xxSortCriterion);
        }
        
        // populate the paginatedList object with default values (the sortCriterion
        // is already populated at this moment.
        paginatedList.setObjectsPerPage(defaultPageSize);
        paginatedList.setPageNumber(defaultPageNumber);
        paginatedList.setSortDirection(SortOrderEnum.ASCENDING);

        setObjectsPerPage(paginatedList, totalNumberOfElements, pageSize, authorizedPageSize);
        setSortDirection(paginatedList, sortDirection);
        setSortCriterion(paginatedList, sortCriterion, authorizedSortCriterion);
        setPageNumber(paginatedList, totalNumberOfElements, pageNumber);
        paginatedList.setFullListSize(totalNumberOfElements);
        return (PaginatedList)paginatedList;
    }

    /**
     * 
     * @param paginatedList
     * @param totalNumberOfElements
     * @param pageSize
     * @param authorizedPageSize
     */
    private void setObjectsPerPage (
            TgolPaginatedListImpl paginatedList,
            int totalNumberOfElements,
            String pageSize,
            Collection<Integer> authorizedPageSize) {
        try {
            if (pageSize != null &&
                    authorizedPageSize.contains(Integer.valueOf(pageSize))) {
                if (Integer.valueOf(pageSize) == -1) {
                    paginatedList.setObjectsPerPage(totalNumberOfElements);
                } else {
                    paginatedList.setObjectsPerPage(Integer.valueOf(pageSize));
                }
            } else {
                LOGGER.warn(UNBOUNDED_STR + PAGE_SIZE_PARAM+PARAMETER_STR
                    + pageSize
                    + USE_DEFAULT_STR + paginatedList.getObjectsPerPage());
            }
        } catch (NumberFormatException nfe) {
            LOGGER.warn(BAD_STR + PAGE_SIZE_PARAM+PARAMETER_STR
                    + pageSize
                    + USE_DEFAULT_STR + paginatedList.getObjectsPerPage());
        }
    }

    private void setSortDirection(
            TgolPaginatedListImpl paginatedList,
            String sortDirection) {
        try {
            if (sortDirection != null &&
                    (Integer.valueOf(sortDirection) == 1 || Integer.valueOf(sortDirection) == 2)) {
                paginatedList.setSortDirection(SortOrderEnum.fromCode(Integer.valueOf(sortDirection)));
            } else {
                LOGGER.warn(UNBOUNDED_STR + SORT_DIRECTION_PARAM+ PARAMETER_STR
                    + sortDirection
                    + USE_DEFAULT_STR + paginatedList.getSortDirection());
            }
        } catch (NumberFormatException nfe) {
            LOGGER.warn(BAD_STR + SORT_DIRECTION_PARAM+PARAMETER_STR
                    + sortDirection
                    + USE_DEFAULT_STR + paginatedList.getSortDirection());
        }
    }

    /**
     * 
     * @param paginatedList
     * @param sortCriterion
     * @param authorizedSortCriterion
     */
    private void setSortCriterion(
            TgolPaginatedListImpl paginatedList,
            String sortCriterion,
            Collection<String> authorizedSortCriterion) {
        try {
            if (authorizedSortCriterion.contains(sortCriterion)) {
                paginatedList.setSortCriterion(sortCriterion);
            } else {
                LOGGER.warn(UNBOUNDED_STR + SORT_CRITERION_PARAM+PARAMETER_STR
                    + sortCriterion
                    + USE_DEFAULT_STR + paginatedList.getSortCriterion());
            }
        } catch (Exception e) {
             LOGGER.warn(BAD_STR + SORT_CRITERION_PARAM+PARAMETER_STR
                    + sortCriterion
                    + USE_DEFAULT_STR + paginatedList.getSortCriterion());
        }
    }

    /**
     * 
     * @param paginatedList
     * @param totalNumberOfElements
     * @param pageNumber
     */
    private void setPageNumber(
            TgolPaginatedListImpl paginatedList,
            int totalNumberOfElements,
            String pageNumber) {
        try {
            int totalNumberOfPages = totalNumberOfElements/paginatedList.getObjectsPerPage();
            if (totalNumberOfElements%paginatedList.getObjectsPerPage() != 0) {
                totalNumberOfPages = totalNumberOfPages+1;
            }
            if (pageNumber != null && Integer.valueOf(pageNumber) >0 &&
                    Integer.valueOf(pageNumber) <= totalNumberOfPages) {
                paginatedList.setPageNumber(Integer.valueOf(pageNumber));
            } else {
                LOGGER.warn(UNBOUNDED_STR + PAGE_PARAM+PARAMETER_STR
                    + pageNumber
                    + USE_DEFAULT_STR + paginatedList.getPageNumber());
            }
        } catch (NumberFormatException nfe) {
            LOGGER.warn(BAD_STR + PAGE_PARAM+PARAMETER_STR
                    + pageNumber
                    + USE_DEFAULT_STR + paginatedList.getPageNumber());
        }
    }
}