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
package org.asqatasun.entity.factory.option;

import org.asqatasun.entity.GenericFactory;
import org.asqatasun.entity.option.Option;
import org.asqatasun.entity.option.OptionFamilyImpl;
import org.asqatasun.entity.option.OptionImpl;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component("optionFactory")
public class OptionFactory implements GenericFactory <Option> {

    @Override
    public Option create() {
        return new OptionImpl();
    }

    public Option createOption(
            OptionFamilyImpl optionFamily,
            String code, 
            String label, 
            String description, 
            boolean isRestriction) {
        OptionImpl option = new OptionImpl();
        option.setCode(code);
        option.setDescription(description);
        option.setLabel(label);
        option.setOptionFamily(optionFamily);
        option.setIsRestriction(isRestriction);
        return option;
    }

}
