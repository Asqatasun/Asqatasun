/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.webapp.action.builder;

import org.tanaguru.webapp.action.Action;
import org.tanaguru.webapp.action.ActionImpl;

/**
 * The ActionBuilder interface contains the same methods as the Action interface
 * All these attribute are set by injection. Then the build method returns 
 * a fresh instance of Action.
 * 
 * @author jkowalczyk
 */
public class ActionBuilderImpl implements ActionBuilder{

    /**
     * Default constructor
     */
    public ActionBuilderImpl(){}

    @Override
    public Action build() {
        Action action = new ActionImpl();
        action.setActionCode(this.actionCode);
        action.setActionI81NCode(this.actionI81NCode);
        action.setActionUrl(this.actionUrl);
        action.setDisabledActionImageUrl(this.disabledActionImageUrl);
        action.setEnabledActionImageUrl(this.enabledActionImageUrl);
        action.setCssSelector(cssSelector);
        action.setActionAltI81NCode(this.actionAltI81NCode);
        // By default an action is disabled
        action.setActionEnabled(false);
        return action;
    }

    private String actionCode;
    @Override
    public String getActionCode() {
        return actionCode;
    }

    @Override
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    private String actionI81NCode;
    @Override
    public String getActionI81NCode() {
        return this.actionI81NCode;
    }

    @Override
    public void setActionI81NCode(String actionI81NCode) {
        this.actionI81NCode = actionI81NCode;
    }

    private String actionAltI81NCode;
    @Override
    public String getActionAltI81NCode() {
        return this.actionAltI81NCode;
    }

    @Override
    public void setActionAltI81NCode(String actionAltI81NCode) {
        this.actionAltI81NCode = actionAltI81NCode;
    }

    private String actionUrl;
    @Override
    public String getActionUrl() {
        return actionUrl;
    }

    @Override
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    private String enabledActionImageUrl;
    @Override
    public String getEnabledActionImageUrl() {
        return enabledActionImageUrl;
    }

    @Override
    public void setEnabledActionImageUrl(String enabledActionImageUrl) {
        this.enabledActionImageUrl = enabledActionImageUrl;
    }
    
    private String disabledActionImageUrl;
    @Override
    public String getDisabledActionImageUrl() {
        return disabledActionImageUrl;
    }

    @Override
    public void setDisabledActionImageUrl(String enabledActionImageUrl) {
        this.disabledActionImageUrl = enabledActionImageUrl;
    }

    private String cssSelector;
    @Override
    public String getCssSelector() {
        return cssSelector;
    }

    @Override
    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }
    
}