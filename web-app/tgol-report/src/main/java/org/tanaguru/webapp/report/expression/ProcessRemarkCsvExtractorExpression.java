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
package org.tanaguru.webapp.report.expression;

import ar.com.fdvs.dj.domain.CustomExpression;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.webapp.presentation.data.RemarkInfos;

/**
 *
 * @author jkowalczyk
 */
public class ProcessRemarkCsvExtractorExpression implements CustomExpression {

    private static final String REMARKS_INFO_FIELD_KEY = "remarkInfosList";
    
    private static final String GENERAL_SEPARATOR_KEY = "ø";
    private static final String PROCESS_REMARK_OPEN_SEPARATOR_KEY = "{";
    private static final String PROCESS_REMARK_CLOSE_SEPARATOR_KEY = "}";
    private static final String PROCESS_REMARK_SEPARATOR_KEY = ";";
    private static final String PROCESS_REMARK_KEY_KEY = "key";
    private static final String PROCESS_REMARK_RESULT_KEY = "result";
    private static final String PROCESS_REMARK_TARGET_KEY = "target";
    private static final String PROCESS_REMARK_EE_NUMBER_KEY = "evidenceElementCounter";
    private static final String PROCESS_REMARK_EE_STRUCTURE_KEY = "evidenceElementStructure";
    private static final String PROCESS_REMARK_EE_LIST_KEY = "evidenceElementList";
    private static final String EE_OPEN_SEPARATOR_KEY = "[";
    private static final String EE_CLOSE_SEPARATOR_KEY = "]";
    private static final String EE_SEPARATOR_KEY = "|";
    private static final String BACKSLASH_KEY = "\\";

    /**
     * Default constructor
     */
    public ProcessRemarkCsvExtractorExpression(){
        
    }
//    public ProcessRemarkCsvExtractorExpression(
//            List<String> bundleNameList, 
//            Locale locale) {
//        if (bundleNameList != null) {
//            for (String bundleName : bundleNameList) {
//                resourceBundleList.add(ResourceBundle.getBundle(bundleName, locale));
//            }
//        }
//    }

    @Override
    public Object evaluate(Map fields, Map variables, Map parameters) {
        List remarkInfoList = (List) fields.get(REMARKS_INFO_FIELD_KEY);
        return buildGeneralCsvStructure(remarkInfoList);
    }

    @Override
    public String getClassName() {
        return String.class.getName();
    }
    
    /**
     * Build the Csv structure that handles ProcessRemarks infos and
     * evidence elements
     * 
     * @param remarkInfoList
     * @return 
     */
    private String buildGeneralCsvStructure(List remarkInfoList) {
        StringBuilder csvExpression = new StringBuilder();
        int remarkInfoListSize = remarkInfoList.size();
        csvExpression.append(remarkInfoListSize);
        csvExpression.append(GENERAL_SEPARATOR_KEY);
        if (remarkInfoListSize > 0) {
        csvExpression.append(buildProcessRemarkHeaderStructure());
        }
        csvExpression.append(GENERAL_SEPARATOR_KEY);
        Iterator iter = remarkInfoList.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof RemarkInfos) {
                RemarkInfos ri = (RemarkInfos)obj;
                csvExpression.append(buildProcessRemark(ri));
                if (iter.hasNext()) {
                    csvExpression.append(GENERAL_SEPARATOR_KEY);
                }
            }
        }
        return csvExpression.toString();
    }

    /**
     * The header structure respects the following format : 
     *  {key;result;target;evidenceElementCounter;evidenceElementStructure;evidenceElementList}
     * where evidenceElementList corresponds to the evidenceElementStructure 
     * with effective data, repeated evidenceElementCounter times.
     * 
     * @return 
     */
    public String buildProcessRemarkHeaderStructure() {
        StringBuilder strb = new StringBuilder();
        strb.append(PROCESS_REMARK_OPEN_SEPARATOR_KEY);
        
        strb.append(PROCESS_REMARK_KEY_KEY);
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        strb.append(PROCESS_REMARK_RESULT_KEY);
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        strb.append(PROCESS_REMARK_TARGET_KEY);
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        strb.append(PROCESS_REMARK_EE_NUMBER_KEY);
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        strb.append(PROCESS_REMARK_EE_STRUCTURE_KEY);
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        strb.append(PROCESS_REMARK_EE_LIST_KEY);
        
        strb.append(PROCESS_REMARK_CLOSE_SEPARATOR_KEY);
        
        return strb.toString();
    }
    
    /**
     * 
     * @param ri
     * @return 
     */
    private String buildProcessRemark(RemarkInfos ri) {
        StringBuilder strb = new StringBuilder();
        strb.append(PROCESS_REMARK_OPEN_SEPARATOR_KEY);
        strb.append(ri.getMessageCode());
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        strb.append(ri.getRemarkResult());
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        if (ri.getRemarkTarget() != null) {
            strb.append(ri.getRemarkTarget());
        }
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        int eeSize = ri.getEvidenceElementList().size();
        strb.append(eeSize);
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        if (eeSize > 0) {
            strb.append(buildEvidenceElementHeaderStructure(ri.getEvidenceElementList()));
        }
        strb.append(PROCESS_REMARK_SEPARATOR_KEY);
        if (eeSize > 0) {
            strb.append(buildEvidenceElementList(ri.getEvidenceElementList()));
        }
        strb.append(PROCESS_REMARK_CLOSE_SEPARATOR_KEY);
        return strb.toString();
    }
    
    /**
     * The evidenceElement depends on the type of the test. To determine it, 
     * we parse the first element of the list, to retrieve the number 
     * of expected elements, and their key.
     * 
     * @param eeList
     * @return 
     */
    private String buildEvidenceElementHeaderStructure(List<Map<String, String>> eeList) {
        StringBuilder strb = new StringBuilder();
        strb.append(EE_OPEN_SEPARATOR_KEY);
        Map<String, String> firstEeEntry = eeList.iterator().next();
        if (firstEeEntry != null) {
            Iterator<String> iter = firstEeEntry.keySet().iterator();
            while (iter.hasNext()) {
                strb.append(iter.next());
                if (iter.hasNext()) {
                    strb.append(EE_SEPARATOR_KEY);
                }
            }
        }
        strb.append(EE_CLOSE_SEPARATOR_KEY);
        return strb.toString();
    }
    
    /**
     * The evidenceElement depends on the type of the test. To determine it, 
     * we parse one the list, to retrieve the number of expected elements, and 
     * their key.
     * 
     * @param eeList
     * @return 
     */
    private String buildEvidenceElementList(List<Map<String, String>> eeList) {
        StringBuilder strb = new StringBuilder();
        
        Iterator<Map<String, String>> eeListIterator = eeList.iterator();
        while (eeListIterator.hasNext()) {
            strb.append(EE_OPEN_SEPARATOR_KEY);
            Iterator<String> iter = eeListIterator.next().values().iterator();
            while (iter.hasNext()) {
                strb.append(escapeCsv(iter.next()));
                if (iter.hasNext()) {
                    strb.append(EE_SEPARATOR_KEY);
                }
            }
            strb.append(EE_CLOSE_SEPARATOR_KEY);
            if (eeListIterator.hasNext()) {
                strb.append(PROCESS_REMARK_SEPARATOR_KEY);
            }
        }
        return strb.toString();
    }

    /**
     * Escape all nested separator { } [ ] ø ; |
     * 
     * @param strToEscape
     * @return 
     */
    private String escapeCsv(String strToEscape) {
        String escapedCsv = escapeCsvSeparator(strToEscape, PROCESS_REMARK_OPEN_SEPARATOR_KEY);
        escapedCsv = escapeCsvSeparator(escapedCsv, PROCESS_REMARK_CLOSE_SEPARATOR_KEY);
        escapedCsv = escapeCsvSeparator(escapedCsv, PROCESS_REMARK_SEPARATOR_KEY);
        escapedCsv = escapeCsvSeparator(escapedCsv, EE_OPEN_SEPARATOR_KEY);
        escapedCsv = escapeCsvSeparator(escapedCsv, EE_CLOSE_SEPARATOR_KEY);
        escapedCsv = escapeCsvSeparator(escapedCsv, EE_SEPARATOR_KEY);
        escapedCsv = escapeCsvSeparator(escapedCsv, PROCESS_REMARK_OPEN_SEPARATOR_KEY);
        escapedCsv = escapeCsvSeparator(escapedCsv, GENERAL_SEPARATOR_KEY);
        return escapedCsv;
    }
    
    private String escapeCsvSeparator(String strToEscape, String elementToEscape) {
        return StringUtils.replace(
                        strToEscape, 
                        elementToEscape, 
                        BACKSLASH_KEY+elementToEscape);
    }
    
}