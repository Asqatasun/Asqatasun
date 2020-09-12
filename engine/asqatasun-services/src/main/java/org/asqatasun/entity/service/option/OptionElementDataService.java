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
package org.asqatasun.entity.service.option;

import java.util.Collection;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.asqatasun.entity.dao.option.OptionElementDAO;
import org.asqatasun.entity.option.Option;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.user.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkowalczyk
 */
@Service("optionElementDataService")
public class OptionElementDataService extends AbstractGenericDataService<OptionElementImpl, Long> {

    public OptionElementImpl getOptionElementFromValueAndOption(String value, Option option) {
        return ((OptionElementDAO)this.entityDao).findOptionElementFromValueAndOption(value, option);
    }

    public Collection<OptionElementImpl> getOptionElementFromUserAndFamilyCode(User user, String optionFamilyCode) {
        return ((OptionElementDAO)this.entityDao).findOptionElementFromUserAndFamilyCode(user, optionFamilyCode);
    }

    public Collection<OptionElementImpl> getOptionElementFromUser(User user) {
        return ((OptionElementDAO)this.entityDao).findOptionElementFromUser(user);
    }

}
