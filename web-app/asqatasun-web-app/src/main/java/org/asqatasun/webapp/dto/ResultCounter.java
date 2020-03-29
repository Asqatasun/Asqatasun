/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.webapp.dto;

/**
 * This class handles the number of each results' type
 * 
 * @author jkowalczyk
 */
public class ResultCounter {

    /**
     * Default constructor
     */
    public ResultCounter(){}

    /**
     * 
     * @param passedCount
     * @param failedCount
     * @param nmiCount
     * @param naCount
     * @param ntCount 
     */
    public ResultCounter(
            Integer passedCount, 
            Integer failedCount, 
            Integer nmiCount, 
            Integer naCount, 
            Integer ntCount){
        this.passedCount = passedCount;
        this.failedCount = failedCount;
        this.nmiCount = nmiCount;
        this.naCount = naCount;
        this.ntCount = ntCount;
    }

    private Integer passedCount = -1;
    public Integer getPassedCount() {
        return passedCount;
    }
    public void setPassedCount(Integer passedCount) {
        this.passedCount = passedCount;
    }

    private Integer failedCount = -1;
    public Integer getFailedCount(){
        return failedCount;
    }
    public void setFailedCount(Integer failedCount){
        this.failedCount = failedCount;
    }

    private Integer nmiCount = -1;
    public Integer getNmiCount() {
        return nmiCount;
    }
    public void setNmiCount(Integer nmiCount) {
        this.nmiCount = nmiCount;
    }

    private Integer ntCount = -1;
    public Integer getNtCount() {
        return ntCount;
    }
    public void setNtCount(Integer ntCount) {
        this.ntCount = ntCount;
    }

    private Integer suspectedPassedCount = -1;
    public Integer getSuspectedPassedCount() {
        return suspectedPassedCount;
    }
    public void setSuspectedPassedCount(Integer suspectedPassedCount) {
        this.suspectedPassedCount = suspectedPassedCount;
    }

    private Integer suspectedFailedCount = -1;
    public Integer getSuspectedFailedCount() {
        return suspectedFailedCount;
    }
    public void setSuspectedFailedCount(Integer suspectedFailedCount) {
        this.suspectedFailedCount = suspectedFailedCount;
    }

    private Integer naCount = -1;
    public Integer getNaCount() {
        return naCount;
    }
    public void setNaCount(Integer naCount) {
        this.naCount = naCount;
    }
    
}
