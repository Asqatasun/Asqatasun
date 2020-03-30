/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.webapp.command.factory;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.webapp.command.ChangeTestWeightCommand;
import org.asqatasun.webapp.entity.option.Option;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.asqatasun.webapp.entity.service.option.OptionDataService;
import org.asqatasun.webapp.entity.service.option.OptionElementDataService;
import org.asqatasun.webapp.entity.service.user.UserDataService;
import org.asqatasun.webapp.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component
public class ChangeTestWeightCommandFactory  implements Serializable {

    private String optionFamilyCodeStr = "TEST_WEIGHT_MANAGEMENT";

    private final OptionElementDataService optionElementDataService;
    private final OptionDataService optionDataService;
    private final UserDataService userDataService;

    /**
     * Private constructor
     */
    private ChangeTestWeightCommandFactory(OptionElementDataService optionElementDataService, OptionDataService optionDataService, UserDataService userDataService) {
        this.optionElementDataService = optionElementDataService;
        this.optionDataService = optionDataService;
        this.userDataService = userDataService;
    }
    
    /**
     * 
     * @param user
     * @param locale
     * @param testList
     * @param referentialKey
     * @return 
     *      an initialised instance of ChangeTestWeightCommand
     */
    public ChangeTestWeightCommand getChangeTestWeightCommand(
            User user, 
            Locale locale,
            Collection<Test> testList, 
            String referentialKey) {
        Map<String, String> userTestWeight = new HashMap<String, String>();
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);
        nf.setMaximumIntegerDigits(1);
        nf.setMinimumIntegerDigits(1);
        for (OptionElement oe : optionElementDataService.getOptionElementFromUserAndFamilyCode(user, referentialKey+"_"+optionFamilyCodeStr)) {
            userTestWeight.put(
                    oe.getOption().getCode(), 
                    nf.format(Double.valueOf(oe.getValue())));
        }
        for (Test test : testList) {
            if (!userTestWeight.containsKey(test.getCode())) {
                userTestWeight.put(test.getCode(), "");
            }
        }
        
        ChangeTestWeightCommand changeTestWeightCommand = 
                new ChangeTestWeightCommand();
        changeTestWeightCommand.setTestWeightMap(userTestWeight);
        return changeTestWeightCommand;
    }

    /**
     * 
     * @param user
     * @param changeTestWeightCommand
     */
    public void updateUserTestWeight(
            User user,
            ChangeTestWeightCommand changeTestWeightCommand) {

        Collection<OptionElement> userOptionElementSet = 
                optionElementDataService.getOptionElementFromUser(user);
        // first we need to remove the option elements associated with the user
        // that are going to be updated
        Iterator<OptionElement> iter = userOptionElementSet.iterator();
        // To preserve the unmodified options, we only remove from the current
        // option element set the one that are overridden by changeTestWeightCommand
        // testWeightMap.
        while (iter.hasNext()) {
            OptionElement oe = iter.next();
            if (changeTestWeightCommand.getTestWeightMap().containsKey(oe.getOption().getCode()) && 
                   !StringUtils.isEmpty(changeTestWeightCommand.getTestWeightMap().get(oe.getOption().getCode()))) {
                iter.remove();
            }
        }
        for (Map.Entry<String, String> entry : changeTestWeightCommand.getTestWeightMap().entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue())) {
                String value = entry.getValue().replaceAll(",", ".");
                Option option = optionDataService.getOption(entry.getKey());
                OptionElement oe = optionElementDataService.getOptionElementFromValueAndOption(
                        value, 
                        option);
                if (oe == null) {
                    oe = optionElementDataService.create();
                    oe.setOption(option);
                    oe.setValue(value);
                    optionElementDataService.saveOrUpdate(oe);
                }
                userOptionElementSet.add(oe);
            }
        }
        user.addAllOptionElement(userOptionElementSet);
        userDataService.saveOrUpdate(user);
    }

}
