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

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.opens.tanaguru.crawler.util.CrawlUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public class AuditSetUpCommand implements Serializable {

    private static final long serialVersionUID = -5390331731974450559L;
    public static final int DEFAULT_LIST_SIZE = 10;
    /**
     * Local Map to deal with uploaded files with the same name
     */
    private Map<String, Integer> fileNameCounterMap = new HashMap<String, Integer>();
    private Map<String, String> auditParameter = new HashMap<String, String>();

    public Map<String, String> getAuditParameter() {
        return auditParameter;
    }

    public void addAuditParameterEntry(String key, String value) {
        auditParameter.put(key, value);
    }
    private Long contractId;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
    private boolean isDefaultParamSet;

    public boolean isDefaultParamSet() {
        return isDefaultParamSet;
    }

    public void setIsDefaultParamSet(boolean isDefaultParamSet) {
        this.isDefaultParamSet = isDefaultParamSet;
    }
    /**
     * General error message in case of invalid form
     */
    private String generalErrorMsg;

    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }

    /**
     * List of urls to test
     */
    private List<String> urlList = new LinkedList<String>();
    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(final List<String> urlList) {
        this.urlList.addAll(urlList);
        for (int i = urlList.size(); i < DEFAULT_LIST_SIZE; i++) {
            this.urlList.add(new String());
        }
    }

    /**
     * Map of uploaded files
     */
    private Map<String, String> fileMap = new HashMap<String, String>();

    public Map<String, String> getFileMap() {
        return fileMap;
    }

    /**
     * Tab of CommonsMultipartFile filled-in by the user
     */
    private CommonsMultipartFile[] fileInputList = null;

    public CommonsMultipartFile[] getFileInputList() {
        if (fileInputList == null) {
            fileInputList = new CommonsMultipartFile[DEFAULT_LIST_SIZE];
        }
        return fileInputList;
    }

    public void setFileInputList(final CommonsMultipartFile[] fileInputList) {
        this.fileInputList = fileInputList.clone();
    }

    /**
     * boolean that indicates if the audit scope is of site.
     */
    private boolean auditSite = false;

    public boolean isAuditSite() {
        return auditSite;
    }

    public void setAuditSite(boolean auditSite) {
        this.auditSite = auditSite;
    }

    /**
     * boolean that indicates if the audit target is online or uploaded.
     */
    private boolean uploadAudit = false;

    public boolean isUploadAudit() {
        return uploadAudit;
    }

    public void setUploadAudit(boolean uploadAudit) {
        this.uploadAudit = uploadAudit;
    }

    /**
     * Default constructor
     */
    public AuditSetUpCommand() {
    }

    /**
     * This method converts the uploaded files into a map where the key is the
     * file name and the value is the file content.
     */
    public void convertFilesToMap() {
        CommonsMultipartFile tmpMultiFile;
        String tmpCharset;
        for (int i = 0; i < getFileInputList().length; i++) {
            tmpMultiFile = getFileInputList()[i];
            try {
                if (tmpMultiFile != null && !tmpMultiFile.isEmpty() && tmpMultiFile.getInputStream() != null) {
                    tmpCharset = CrawlUtils.extractCharset(tmpMultiFile.getInputStream());
                    fileMap.put(
                            getFileName(tmpMultiFile.getOriginalFilename()),
                            tmpMultiFile.getFileItem().getString(tmpCharset));
                }
            } catch (IOException e) {}
        }
    }

    /**
     * This method formats the file name of the uploaded file (prefix by "/")
     * and suffix files with the same name with an index.
     *
     * @param originalFileName
     * @return
     */
    private String getFileName(String originalFileName){
        StringBuilder fileName = new StringBuilder();
        fileName.append('/');
        if (fileNameCounterMap.containsKey(originalFileName)){
            fileNameCounterMap.put(originalFileName, Integer.valueOf(fileNameCounterMap.get(originalFileName)+1));
            fileName.append(originalFileName);
            fileName.append('_');
            fileName.append(fileNameCounterMap.get(originalFileName));
            return fileName.toString();
        } else {
            fileNameCounterMap.put(originalFileName, Integer.valueOf(1));
            fileName.append(originalFileName);
            return fileName.toString();
        }
    }

}