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
package org.tanaguru.webapp.validator;

import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.webapp.command.ChangeTestWeightCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jkowalczyk
 */
public class ChangeTestWeightFormValidator implements Validator {
    
    private static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    private static final String WRONG_VALUES_MSG_BUNDLE_KEY =
            "test-weight.invalidElements";
    private static final String WRONG_VALUE_MSG_BUNDLE_KEY =
            "test-weight.invalidElement";
    private static final String WEIGHT_VALUE_REGEXP ="[1-9]";
    private final Pattern weightCheckerPattern = Pattern.compile(WEIGHT_VALUE_REGEXP);
    
    @Override
    public void validate(Object target, Errors errors) {
        ChangeTestWeightCommand changeTestWeightCommand = (ChangeTestWeightCommand)target;
        for (Map.Entry<String, String> entry : changeTestWeightCommand.getTestWeightMap().entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue()) && ! weightCheckerPattern.matcher(entry.getValue()).matches()) {
                errors.rejectValue("testWeightMap["+entry.getKey()+"]", WRONG_VALUE_MSG_BUNDLE_KEY);
            }
        }
        if (errors.hasFieldErrors()) {
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    WRONG_VALUES_MSG_BUNDLE_KEY);
        }
    }
    
    @Override
    public boolean supports(Class clazz) {
        return ChangeTestWeightFormValidator.class.isAssignableFrom(clazz);
    }

    public boolean match(String value) {
        return weightCheckerPattern.matcher(value).matches();
    }

}