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
package org.opens.tgol.presentation.factory;

import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tgol.presentation.data.PageResult;
import org.opens.tgol.presentation.data.PageResultImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * As a factory, this class return an initialized instance of PageResult
 * @author jkowalczyk
 */
public class PageResultFactory {

    private ProcessResultDataService processResultDataService;
    public ProcessResultDataService getProcessResultDataService() {
        return processResultDataService;
    }

    @Autowired
    public void setProcessResultDataService(ProcessResultDataService processResultDataService) {
        this.processResultDataService = processResultDataService;
    }

    private Scope scope;
    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    /**
     * The unique shared instance of PageResultFactory
     */
    private static PageResultFactory pageResultFactory;

    /**
     * Default private constructor
     */
    protected PageResultFactory() {}

    public static synchronized PageResultFactory getInstance() {
        if (pageResultFactory == null) {
            pageResultFactory = new PageResultFactory();
        }
        return pageResultFactory;
    }

    /**
     * 
     * @param url
     * @param weightedMark
     * @param rawMark
     * @param webResourceId
     * @param httpStatusCode
     * @return
     */
    public PageResult getPageResult(
            String url,
            Integer rank,
            Float weightedMark,
            Float rawMark,
            Long webResourceId,
            String httpStatusCode){
        return new PageResultImpl(url, rank, weightedMark, rawMark, webResourceId, httpStatusCode);
    }

}