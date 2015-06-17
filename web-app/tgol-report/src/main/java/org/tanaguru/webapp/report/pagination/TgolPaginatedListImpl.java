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

import org.tanaguru.webapp.presentation.data.PageResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 * This private class is an implementation of the PaginatedList interface
 * of the display tag API. This class is needed to realize sorting and
 * pagination on server side.
 *
 * @author jkowalczyk
 */
public class TgolPaginatedListImpl implements PaginatedList {

    /**
     *
     * @param defaultSortCriterion
     */
    public TgolPaginatedListImpl(String defaultSortCriterion) {
        this.sortCriterion = defaultSortCriterion;
    }
    private List<PageResult> elementsList = new ArrayList<PageResult>();

    /**
     * 
     * @return
     */
    @Override
    public List getList() {
        return elementsList;
    }

    /**
     *
     * @param elementsList
     */
    public void setElementsList(List elementsList) {
        this.elementsList = elementsList;
    }

    /**
     *
     * @param elementsList
     */
    public void addAllElements(Collection elementsList) {
        this.elementsList.addAll(elementsList);
    }
    private int pageNumber;

    /**
     *
     * @return
     */
    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     *
     * @param pageNumber
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    private int objectsPerPage;

    /**
     *
     * @return
     */
    @Override
    public int getObjectsPerPage() {
        return objectsPerPage;
    }

    /**
     *
     * @param objectsPerPage
     */
    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }
    private int fullListSize;

    /**
     *
     * @return
     */
    @Override
    public int getFullListSize() {
        return fullListSize;
    }

    /**
     *
     * @param fullListSize
     */
    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }
    private String sortCriterion;

    /**
     *
     * @return
     */
    @Override
    public String getSortCriterion() {
        return sortCriterion;
    }

    /**
     *
     * @param sortCriterion
     */
    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }
    private SortOrderEnum sortDirection = SortOrderEnum.ASCENDING;

    /**
     *
     * @return
     */
    @Override
    public SortOrderEnum getSortDirection() {
        return sortDirection;
    }

    /**
     *
     * @param sortDirection
     */
    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }
    private String searchId;

    /**
     *
     * @return
     */
    @Override
    public String getSearchId() {
        return searchId;
    }

    /**
     *
     * @param searchId
     */
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

}