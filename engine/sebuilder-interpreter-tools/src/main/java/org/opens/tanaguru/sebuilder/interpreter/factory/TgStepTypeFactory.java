/*
 * Copyright 2012 Sauce Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opens.tanaguru.sebuilder.interpreter.factory;

import com.sebuilder.interpreter.StepType;
import com.sebuilder.interpreter.factory.StepTypeFactory;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.sebuilder.interpreter.TgStore;

/**
 *
 * @author jkowalczyk
 */
public class TgStepTypeFactory extends StepTypeFactory{

    private static final String PRIMARY_PACKAGE_FOR_STEP = "org.opens.tanaguru.sebuilder.interpreter.steptype.";
    private static final String SECONDARY_PACKAGE_FOR_STEP = "com.sebuilder.interpreter.steptype.";
    
    public TgStepTypeFactory() {
        super();
        setPrimaryPackage(PRIMARY_PACKAGE_FOR_STEP);
        setSecondaryPackage(SECONDARY_PACKAGE_FOR_STEP);
    }

    @Override
    public StepType getStepTypeOfName(String name) {
        StepType stepType = super.getStepTypeOfName(name);
        if (StringUtils.equals(name, "storeCurrentUrl") && stepType instanceof com.sebuilder.interpreter.Store) {
            return new TgStore(((com.sebuilder.interpreter.Store)stepType).getGetter());
        }
        return stepType;
    }

}