/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.webapp.dto.factory;

import org.asqatasun.webapp.dto.PageResult;
import org.springframework.stereotype.Component;

/**
 * As a factory, this class return an initialized instance of PageResult
 * @author jkowalczyk
 */
@Component("pageResultFactory")
public class PageResultFactory {

    /**
     * 
     * @param url
     * @param rank
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
        return new PageResult(url, rank, weightedMark, rawMark, webResourceId, httpStatusCode);
    }

}
