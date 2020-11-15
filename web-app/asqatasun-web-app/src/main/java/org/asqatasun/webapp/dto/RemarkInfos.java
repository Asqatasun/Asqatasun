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
package org.asqatasun.webapp.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles displayable remarks data
 * @author jkowalczyk
 */
public class RemarkInfos {

    private String messageCode;
    /**
     *
     * @return
     */

    public String getMessageCode() {
        return messageCode;
    }

    /**
     *
     * @param messageCode
     */

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    private String remarkTarget;
    /**
     *
     * @return
     */

    public String getRemarkTarget() {
        return remarkTarget;
    }


    public void setRemarkTarget(String remarkTarget) {
        this.remarkTarget = remarkTarget.toUpperCase();
    }

    private String remarkResult;
    /**
     *
     * @return
     */

    public String getRemarkResult() {
        return remarkResult;
    }

    /**
     *
     * @param remarkResult
     */

    public void setRemarkResult(String remarkResult) {
        this.remarkResult = remarkResult;
    }

    private List<Map<String, String>> evidenceElementList =
            new ArrayList<Map<String, String>>();
    /**
     *
     * @return
     */

    public List<Map<String, String>> getEvidenceElementList() {
        return evidenceElementList;
    }

    /**
     *
     * @param evidenceElementList
     */

    public void setEvidenceElementList(List<Map<String, String>> evidenceElementList) {
        this.evidenceElementList = evidenceElementList;
    }

    /**
     *
     * @param evidenceElement
     */

    public void addEvidenceElement(Map<String, String> evidenceElement) {
        this.evidenceElementList.add(evidenceElement);
    }

    /**
     * Default constructor
     */
    public RemarkInfos() {

    }

    /**
     * Default constructor
     * @param messageCode
     */
    public RemarkInfos(String messageCode) {
        this.messageCode = messageCode;
    }

    /**
     * Default constructor
     * @param messageCode
     * @param remarkTarget
     */
    public RemarkInfos(String messageCode, String remarkTarget) {
        this.messageCode = messageCode;
        this.remarkTarget = remarkTarget;
    }

}
