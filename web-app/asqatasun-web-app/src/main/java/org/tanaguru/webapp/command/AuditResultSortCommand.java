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
package org.tanaguru.webapp.command;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jkowalczyk
 */
public class AuditResultSortCommand implements Serializable {

    private static final long serialVersionUID = -6799971556530681226L;
    
    private Map<String, Object> sortOptionMap=new HashMap();
    public Map<String, Object> getSortOptionMap() {
        return sortOptionMap;
    }

    public void setSortOptionMap(Map<String, Object> sortOptionMap) {
        this.sortOptionMap = sortOptionMap;
    }

    private Long webResourceId;
    public Long getWebResourceId() {
        return webResourceId;
    }

    public void setWebResourceId(Long webResourceId) {
        this.webResourceId = webResourceId;
    }
    
    private String displayScope;
    public String getDisplayScope() {
        return this.displayScope;
    }
    
    public void setDisplayScope(String displayScope) {
        this.displayScope = displayScope;
    }
    
    private boolean displayScopeChoice;
    public boolean getDisplayScopeChoice() {
        return this. displayScopeChoice;
    }
    
    public void setDisplayScopeChoice(boolean displayScopeChoice) {
        this.displayScopeChoice = displayScopeChoice;
    }

    /**
     * Default constructor
     */
    public AuditResultSortCommand() {}    

}