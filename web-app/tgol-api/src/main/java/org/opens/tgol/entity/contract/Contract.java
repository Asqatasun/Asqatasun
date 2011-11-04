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
package org.opens.tgol.entity.contract;

import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.product.Restriction;
import org.opens.tgol.entity.user.User;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author jkowalczyk
 */
public interface Contract extends Entity {

    /**
     * 
     * @return
     *      the label associated with the contract
     */
    String getLabel();

    /**
     *
     * @param label
     */
    void setLabel(String label);

    /**
     *
     * @return
     *      the begin date of the contract
     */
    Date getBeginDate();

    /**
     *
     * @param beginDate
     */
    void setBeginDate(Date beginDate);

    /**
     *
     * @return
     *      the end date of the contract
     */
    Date getEndDate();

    /**
     *
     * @param endDate
     */
    void setEndDate(Date endDate);

    /**
     *
     * @return
     *      the renewal date of the contract
     */
    Date getRenewalDate();

    /**
     *
     * @param renewalDate
     */
    void setRenewalDate(Date renewalDate);

    /**
     *
     * @return
     *          the Url associated with the contract
     */
    String getUrl();

    /**
     * 
     * @param url
     */
    void setUrl(String url);

    /**
     *
     * @return
     *      the price of the contract
     */
    Float getPrice();

    /**
     *
     * @param price
     */
    void setPrice(Float price);

    /**
     *
     * @return
     *      the user owning the contract
     */
    User getUser();

    /**
     *
     * @param user
     */
    void setUser(User user);

    /**
     *
     * @return
     *      the related product associated with the contract
     */
    Product getProduct();

    /**
     * 
     * @param product
     */
    void setProduct(Product product);

    /**
     *
     * @return
     *      the set of the acts associated with the contract
     */
    Set<? extends Act> getActSet();

    /**
     *
     * @param act
     */
    void addAct (Act act);

    /**
     *
     * @param actSet
     */
    void addAllAct (Set<? extends Act> actSet);

        /**
     *
     * @return
     *      the set of the restrictions associated with the offer
     */
    Set<? extends Restriction> getRestrictionSet();

    /**
     *
     * @param restriction
     */
    void addRestriction (Restriction restriction);

    /**
     *
     * @param restrictionSet
     */
    void addAllRestriction (Set<? extends Restriction> restrictionSet);
}
