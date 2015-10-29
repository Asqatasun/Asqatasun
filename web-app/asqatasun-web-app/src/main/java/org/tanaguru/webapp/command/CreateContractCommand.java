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
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class CreateContractCommand  implements Serializable {

    /**
     * the label of the contract
     */
    private Collection<User> userList;
    public Collection<User> getUserList() {
        return userList;
    }

    public void setUserList(Collection<User> userList) {
        this.userList = userList;
    }
    
    /**
     * the label of the contract
     */
    private String label;
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * the label of the contract
     */
    private String contractUrl = "";
    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    /**
     * the begin date of the contract
     */
    private Date beginDate;
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * the begin date of the contract
     */
    private Date endDate;
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    private Map<String,Boolean> referentialMap;
    public Map<String,Boolean> getReferentialMap() {
        return referentialMap;
    }

    public void setReferentialMap(Map<String,Boolean> referentialMap) {
        this.referentialMap = referentialMap;
    }
    
    private Map<String,Boolean> functionalityMap;
    public Map<String,Boolean> getFunctionalityMap() {
        return functionalityMap;
    }

    public void setFunctionalityMap(Map<String,Boolean> functionalityMap) {
        this.functionalityMap = functionalityMap;
    }
    
    private Map<String,String> optionMap;
    public Map<String,String> getOptionMap() {
        return optionMap;
    }

    public void setOptionMap(Map<String,String> optionMap) {
        this.optionMap = optionMap;
    }
    
    /**
     * General error message in case of invalid form
     */
    private String generalErrorMsg;
    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }

    /**
     * Default constructor
     */
    public CreateContractCommand(){
        
    }

}