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
package org.opens.tgol.entity.factory.contract;

import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.user.User;
import java.util.Date;

/**
 *
 * @author jkowalczyk
 */
public interface ContractFactory extends GenericFactory<Contract> {

    /**
     *
     * @param beginDatedate
     * @param endDate
     * @param renewalDate
     * @param price
     * @param offer
     * @param user
     * @return
     *      an initialized instance of Contract
     */
    Contract createContract(
            String label,
            Date beginDate,
            Date endDate,
            Date renewalDate,
            Float price,
            Product offer,
            User user);

}
