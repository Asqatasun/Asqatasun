/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.sebuilder.interpreter.factory;

import com.sebuilder.interpreter.StepType;
import com.sebuilder.interpreter.factory.StepTypeFactory;
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.sebuilder.interpreter.TgStore;

/**
 *
 * @author jkowalczyk
 */
public class TgStepTypeFactory extends StepTypeFactory{

    private static final String PRIMARY_PACKAGE_FOR_STEP = "org.asqatasun.sebuilder.interpreter.steptype";
    private static final String SECONDARY_PACKAGE_FOR_STEP = "com.sebuilder.interpreter.steptype";
    
    public TgStepTypeFactory() {
        super();
        setPrimaryPackage(PRIMARY_PACKAGE_FOR_STEP);
        setSecondaryPackage(SECONDARY_PACKAGE_FOR_STEP);
    }

    @Override
    public StepType getStepTypeOfName(String name) {
        StepType stepType = super.getStepTypeOfName(name);
        if (StringUtils.equals(name, "storeCurrentUrl") && stepType instanceof com.sebuilder.interpreter.Store) {
            return new TgStore(((com.sebuilder.interpreter.Store)stepType).getter);
        }
        return stepType;
    }

}