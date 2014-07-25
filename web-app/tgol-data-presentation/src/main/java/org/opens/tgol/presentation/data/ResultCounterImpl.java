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

/**
 * This class handles the number of each results' type
 * 
 * @author jkowalczyk
 */
public class ResultCounterImpl implements ResultCounter {

    /**
     * Default constructor
     */
    public ResultCounterImpl (){}

    /**
     * 
     * @param passedCount
     * @param failedCount
     * @param nmiCount
     * @param naCount
     * @param ntCount 
     */
    public ResultCounterImpl (
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
    @Override
    public Integer getPassedCount() {
        return passedCount;
    }

    @Override
    public void setPassedCount(Integer passedCount) {
        this.passedCount = passedCount;
    }

    private Integer failedCount = -1;
    @Override
    public Integer getFailedCount(){
        return failedCount;
    }

    @Override
    public void setFailedCount(Integer failedCount){
        this.failedCount = failedCount;
    }

    private Integer nmiCount = -1;
    @Override
    public Integer getNmiCount() {
        return nmiCount;
    }

    @Override
    public void setNmiCount(Integer nmiCount) {
        this.nmiCount = nmiCount;
    }

    private Integer ntCount = -1;
    @Override
    public Integer getNtCount() {
        return ntCount;
    }

    @Override
    public void setNtCount(Integer ntCount) {
        this.ntCount = ntCount;
    }

    private Integer suspectedPassedCount = -1;
    @Override
    public Integer getSuspectedPassedCount() {
        return suspectedPassedCount;
    }

    @Override
    public void setSuspectedPassedCount(Integer suspectedPassedCount) {
        this.suspectedPassedCount = suspectedPassedCount;
    }

    private Integer suspectedFailedCount = -1;
    @Override
    public Integer getSuspectedFailedCount() {
        return suspectedFailedCount;
    }

    @Override
    public void setSuspectedFailedCount(Integer suspectedFailedCount) {
        this.suspectedFailedCount = suspectedFailedCount;
    }

    private Integer naCount = -1;
    @Override
    public Integer getNaCount() {
        return naCount;
    }

    @Override
    public void setNaCount(Integer naCount) {
        this.naCount = naCount;
    }
    
}
