/*
 * Tanaguru - Automated webpage assessment
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
package org.tanaguru.webapp.command;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.tanaguru.webapp.command.helper.UploadAuditSetUpCommandHelper;
import org.tanaguru.webapp.entity.contract.ScopeEnum;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public class AuditSetUpCommand implements Serializable {

    private static final long serialVersionUID = -5390331731974450559L;
    public static final int DEFAULT_LIST_SIZE = 10;
    
    /**
     * General error message in case of invalid form
     */
    private String generalErrorMsg;

    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }
    
    /**
     * The map that handles the audit parameters (except the level parameter)
     */
    private final Map<String, String> auditParameterMap = new HashMap();
    public Map<String, String> getAuditParameter() {
        return auditParameterMap;
    }

    public void setAuditParameter(String key, String value) {
        auditParameterMap.put(key, value);
    }
    
    public void addAuditParameterEntry(String key, String value) {
        auditParameterMap.put(key, value);
    }

    /**
     * The id of the current contract
     */
    private Long contractId;
    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     * Flag that indicates if this AuditSetUpCommand instance is set-up with 
     * the default parameter set (Usefull to display a specific message).
     */
    private boolean isDefaultParamSet;
    public boolean isDefaultParamSet() {
        return isDefaultParamSet;
    }

    public void setDefaultParamSet(boolean isDefaultParamSet) {
        this.isDefaultParamSet = isDefaultParamSet;
    }

    /**
     * Value of the level;
     */
    private String level;
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level=level;
    }
    
    /**
     * Value of the level;
     */
    private ScopeEnum scope;
    public ScopeEnum getScope() {
        return scope;
    }
    
    public void setScope(ScopeEnum scope) {
        this.scope = scope;
    }
    
    /**
     * List of urls to test (only needed when scope = ScopeEnum.PAGES)
     */
    private final List<String> urlList = new LinkedList();
    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(final List<String> urlList) {
        this.urlList.addAll(urlList);
    }
    
    /**
     * @return Map of uploaded files (only needed when scope = ScopeEnum.UPLOAD)
     */
    public Map<String, String> getFileMap() {
        return UploadAuditSetUpCommandHelper.convertFilesToMap(fileInputList);
    }

    /**
     * Tab of CommonsMultipartFile filled-in by the user
     * (only needed when scope = ScopeEnum.UPLOAD)
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
    
    private String scenarioName;
    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }
    
    private Long scenarioId;
    public Long getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Long scenarioId) {
        this.scenarioId = scenarioId;
    }
    
    private boolean isRelaunch = false;
    public boolean getRelaunch() {
        return isRelaunch;
    }

    public void setRelaunch(boolean isRelaunch) {
        this.isRelaunch = isRelaunch;
    }
    
    /**
     * Default constructor
     */
    public AuditSetUpCommand() {}

}