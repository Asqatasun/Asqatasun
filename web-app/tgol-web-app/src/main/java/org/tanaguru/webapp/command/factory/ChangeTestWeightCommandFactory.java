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
package org.tanaguru.webapp.command.factory;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.webapp.command.ChangeTestWeightCommand;
import org.tanaguru.webapp.entity.option.Option;
import org.tanaguru.webapp.entity.option.OptionElement;
import org.tanaguru.webapp.entity.service.option.OptionDataService;
import org.tanaguru.webapp.entity.service.option.OptionElementDataService;
import org.tanaguru.webapp.entity.service.user.UserDataService;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class ChangeTestWeightCommandFactory  implements Serializable {

    private String optionFamilyCodeStr = "TEST_WEIGHT_MANAGEMENT";
    public void setOptionFamilyCodeStr(String optionFamilyCodeStr) {
        this.optionFamilyCodeStr = optionFamilyCodeStr;
    }
    
    private OptionElementDataService optionElementDataService;
    public OptionElementDataService getOptionElementDataService() {
        return optionElementDataService;
    }

    public void setOptionElementDataService(OptionElementDataService optionElementDataService) {
        this.optionElementDataService = optionElementDataService;
    }
    
    private OptionDataService optionDataService;
    public OptionDataService getOptionDataService() {
        return optionDataService;
    }

    public void setOptionDataService(OptionDataService optionDataService) {
        this.optionDataService = optionDataService;
    }
    
    private UserDataService userDataService;
    public UserDataService getUserDataService() {
        return userDataService;
    }

    public void setUserDataService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }
    
    /**
     * The holder that handles the unique instance of ChangeTestWeightCommandFactory
     */
    private static class ChangeTestWeightCommandFactoryHolder {
        private static final ChangeTestWeightCommandFactory INSTANCE = 
                new ChangeTestWeightCommandFactory();
    }
    
    /**
     * Private constructor
     */
    private ChangeTestWeightCommandFactory() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of ChangeTestWeightCommandFactory
     */
    public static ChangeTestWeightCommandFactory getInstance() {
        return ChangeTestWeightCommandFactoryHolder.INSTANCE;
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