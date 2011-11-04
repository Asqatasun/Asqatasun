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
package org.opens.tgol.action;

/**
 *
 * @author jkowalczyk
 */
public interface Action {

   /**
     *
     * @return
     *      whether the action is enabled or not
     */
    boolean getIsActionEnabled();

    /**
     * @param isActionEnabled
     */
    void setIsActionEnabled(boolean isActionEnabled);

    /**
     * do some treatment
     */
    void doProcess();

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
     *      the contract action I18n code
     */
    String getActionI81NCode();

    /**
     *
     * @param contractActionI81NCode
     */
    void setActionI81NCode(String contractActionI81NCode);

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
     * @param contractActionUrl
     */
    void setEnabledActionImageUrl(String contractActionImageUrl);
    
    /**
     *
     * @return
     *      the Url called when the action is selected
     */
    String getDisabledActionImageUrl();

    /**
     *
     * @param contractActionUrl
     */
    void setDisabledActionImageUrl(String contractActionImageUrl);

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