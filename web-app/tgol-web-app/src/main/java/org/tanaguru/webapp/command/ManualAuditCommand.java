/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.presentation.data.ManualResult;
import org.tanaguru.webapp.presentation.data.TestResult;

/**
 * 
 * @author OceaneConsulting
 */
public class ManualAuditCommand implements Serializable {

    private static final long serialVersionUID = -5390311731974450559L;

    private String generalErrorMsg;

    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }

    private Collection<User> userList;
    public Collection<User> getUserList() {
        return userList;
    }

    public void setUserList(Collection<User> userList) {
        this.userList = userList;
    }

    private final Map<String, ManualResult> modifiedManualResultMap = new HashMap();
    public Map<String, ManualResult> getModifiedManualResultMap() {
        return modifiedManualResultMap;
    }
    
    public void setModifedManualResultMap(Map<String, TestResult> modifiedManualResultMap) {
        this.modifiedManualResultMap.clear();
        for (Map.Entry<String, TestResult> entry : modifiedManualResultMap.entrySet()) {
            this.modifiedManualResultMap.put(
                    entry.getValue().getTestShortLabel(), 
                    new ManualResult(entry.getValue()));
        }
    }

    private final List<String> statusList = 
            Arrays.asList(
                    TestResult.PASSED_LOWER, 
                    TestResult.FAILED_LOWER, 
                    TestResult.NOT_APPLICABLE_LOWER);

    public List<String> getStatusList() {
        return statusList;
    }

    private List<ProcessResult> processResultList;

    public List<ProcessResult> getProcessResultList() {
        return processResultList;
    }

    public void setProcessResultList(List<ProcessResult> processResultList) {
        this.processResultList = processResultList;
    }

    /**
     * Default constructor
     */
    public ManualAuditCommand() {
    }
    
}