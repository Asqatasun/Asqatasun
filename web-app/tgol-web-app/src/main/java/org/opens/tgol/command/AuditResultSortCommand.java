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
package org.opens.tgol.command;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jkowalczyk
 */
public class AuditResultSortCommand implements Serializable {

    private static final long serialVersionUID = -6799971556530681226L;
    
    /**
     * General error message in case of invalid form
     */
    private String generalErrorMsg;

    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }

    private Map<String, String> sortOptionMap=new HashMap<String, String>();
    public Map<String, String> getSortOptionMap() {
        return sortOptionMap;
    }

    public void setSortOptionMap(Map<String, String> sortOptionMap) {
        this.sortOptionMap = sortOptionMap;
    }

    private Long webResourceId;
    public Long getWebResourceId() {
        return webResourceId;
    }

    public void setWebResourceId(Long webResourceId) {
        this.webResourceId = webResourceId;
    }

    /**
     * Default constructor
     */
    public AuditResultSortCommand() {}    

}