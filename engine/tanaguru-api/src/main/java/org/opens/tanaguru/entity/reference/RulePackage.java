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
package org.opens.tanaguru.entity.reference;

import java.util.Collection;

import org.opens.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface RulePackage extends Entity {

    /**
     *
     * @param rule
     *            the rule to add
     */
    public void addRule(Rule rule);

    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the name of the package
     */
    String getPackageName();

    /**
     *
     * @return the rule list
     */
    Collection<? extends Rule> getRuleList();

    /**
     *
     * @param description
     *            the description to set
     */
    void setDescription(String description);

    /**
     *
     * @param packageName
     *            the name of the package to set
     */
    void setPackageName(String packageName);

    /**
     *
     * @param ruleList
     *            the rule list to set
     */
    void setRuleList(Collection<? extends Rule> ruleList);

}