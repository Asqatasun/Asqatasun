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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles displayable remarks data
 * @author jkowalczyk
 */
public class RemarkInfosImpl implements RemarkInfos{

    private String messageCode;
    /**
     *
     * @return
     */
    @Override
    public String getMessageCode() {
        return messageCode;
    }

    /**
     *
     * @param messageCode
     */
    @Override
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    private String remarkTarget;
    /**
     *
     * @return
     */
    @Override
    public String getRemarkTarget() {
        return remarkTarget;
    }

    @Override
    public void setRemarkTarget(String remarkTarget) {
        this.remarkTarget = remarkTarget.toUpperCase();
    }

    private String remarkResult;
    /**
     *
     * @return
     */
    @Override
    public String getRemarkResult() {
        return remarkResult;
    }

    /**
     *
     * @param remarkResult
     */
    @Override
    public void setRemarkResult(String remarkResult) {
        this.remarkResult = remarkResult;
    }

    private List<Map<String, String>> evidenceElementList =
            new ArrayList<Map<String, String>>();
    /**
     *
     * @return
     */
    @Override
    public List<Map<String, String>> getEvidenceElementList() {
        return evidenceElementList;
    }

    /**
     *
     * @param evidenceElementList
     */
    @Override
    public void setEvidenceElementList(List<Map<String, String>> evidenceElementList) {
        this.evidenceElementList = evidenceElementList;
    }

    /**
     *
     * @param evidenceElement
     */
    @Override
    public void addEvidenceElement(Map<String, String> evidenceElement) {
        this.evidenceElementList.add(evidenceElement);
    }

    /**
     * Default constructor
     */
    public RemarkInfosImpl() {

    }

    /**
     * Default constructor
     * @param messageCode
     */
    public RemarkInfosImpl(String messageCode) {
        this.messageCode = messageCode;
    }

    /**
     * Default constructor
     * @param messageCode
     * @param remarkTarget
     */
    public RemarkInfosImpl(String messageCode, String remarkTarget) {
        this.messageCode = messageCode;
        this.remarkTarget = remarkTarget;
    }

}