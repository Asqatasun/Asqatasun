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

/**
 *
 * @author jkowalczyk
 */
public interface ActionBuilder {
    
    /**
     * 
     * @return
     *      a set instance of action
     */
    Action build();
    
    /**
     *
     * @return
     *      the contract action code
     */
    String getActionCode();

    /**
     *
     * @param contractActionCode
     */
    void setActionCode(String contractActionCode);

    /**
     *
     * @return
     *      the contract I18n code
     */
    String getActionI81NCode();

    /**
     *
     * @param actionI81NCode
     */

    void setActionI81NCode(String actionI81NCode);
    /**
     *
     * @return
     *      the action title I18n code
     */
    String getActionAltI81NCode();

    /**
     *
     * @param actionAltI81NCode
     */
    void setActionAltI81NCode(String actionAltI81NCode);

    /**
     *
     * @return
     *      the Url called when the action is selected
     */
    String getActionUrl();

    /**
     * 
     * @param contractActionUrl
     */
    void setActionUrl(String contractActionUrl);

    /**
     *
     * @return
     *      the Url called when the action is selected
     */
    String getEnabledActionImageUrl();

    /**
     *
     * @param enabledActionImageUrl
     */
    void setEnabledActionImageUrl(String enabledActionImageUrl);
    
    /**
     *
     * @return
     *      the Url called when the action is selected
     */
    String getDisabledActionImageUrl();

    /**
     *
     * @param disabledActionImageUrl
     */
    void setDisabledActionImageUrl(String disabledActionImageUrl);

    /**
     *
     * @return
     */
    String getCssSelector();

    /**
     * 
     * @param cssSelector
     */
    void setCssSelector(String cssSelector);
    
}