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
package org.tanaguru.webapp.action.voter;

import org.tanaguru.webapp.action.Action;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This implementation of the ContractVoter interface handles a collection
 * of ContractAction instances
 *
 * @author jkowalczyk
 */
public class ActionVoterImpl implements ActionVoter {

    private Map<String, Boolean> actionEnableMap;

    public void setActionEnableMap(Map<String, Boolean> actionEnableMap) {
        this.actionEnableMap = actionEnableMap;
    }

    private List<Action> actionList = new LinkedList<Action>();

    private String code;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void initialize(List<Action> contractActionList) {
        String actionCode;
        this.actionList = contractActionList;
        for (Action action : this.actionList) {
            actionCode = action.getActionCode();
            if (actionEnableMap.containsKey(actionCode)) {
                action.setActionEnabled(actionEnableMap.get(actionCode));
            }
        }
    }

    @Override
    public List<Action> getActionList() {
        return actionList;
    }

}