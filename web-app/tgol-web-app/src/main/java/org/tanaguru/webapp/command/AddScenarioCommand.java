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

import java.io.IOException;
import java.io.Serializable;
import org.tanaguru.crawler.util.CrawlUtils;
import org.tanaguru.webapp.entity.contract.ScopeEnum;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public class AddScenarioCommand implements Serializable {

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
     * The label associated with the scenario;
     */
    private String scenarioLabel;

    public String getScenarioLabel() {
        return scenarioLabel;
    }

    public void setScenarioLabel(String scenarioLabel) {
        this.scenarioLabel = scenarioLabel;
    }
    /**
     * Value of the scope;
     */
    private ScopeEnum scope;

    public ScopeEnum getScope() {
        return scope;
    }

    public void setScope(ScopeEnum scope) {
        this.scope = scope;
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
     * CommonsMultipartFile that handles the scenario (only needed when scope =
     * ScopeEnum.SCENARIO)
     */
    private CommonsMultipartFile scenarioFile = null;
    public CommonsMultipartFile getScenarioFile() {
        return scenarioFile;
    }

    public void setScenarioFile(final CommonsMultipartFile scenarioFile) {
        this.scenarioFile = scenarioFile;
    }
    
    private String scenarioContent;
    public String getScenarioContent() {
        if (scenarioContent == null) {
            try {
                if (scenarioFile != null && !scenarioFile.isEmpty() && scenarioFile.getInputStream() != null) {
                    String tmpCharset = CrawlUtils.extractCharset(scenarioFile.getInputStream());
                    scenarioContent = scenarioFile.getFileItem().getString(tmpCharset);
                    // #57 issue quick fix.......
                    scenarioContent = scenarioContent.replace("\"formatVersion\": 2", "\"formatVersion\":1")
                                                     .replace("\"formatVersion\":2", "\"formatVersion\":1");
                }
            } catch (IOException e) {}
        }
        return scenarioContent;
    }
    /**
     * Default constructor
     */
    public AddScenarioCommand() {
    }
}