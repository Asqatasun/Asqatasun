/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.tanaguru.webapp.presentation.data;

/**
 * POJO that handles manual result filled-in by user
 * 
 * @author jkowalczyk
 */
public class ManualResult {
   
    /** the result set by user*/
    private String result;
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /** the comment set by user*/
    public String comment;
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /**
     * no args constructor
     */
    public ManualResult(){}
    
    /**
     * 
     * @param result
     * @param comment 
     */
    public ManualResult(String result, String comment){
        this.result = result;
        this.comment = comment;
    }
    
    /**
     * 
     * @param testResult 
     */
    public ManualResult(TestResult testResult){
        this.result = testResult.getManualResult();
        this.comment = testResult.getComment();
    }

}