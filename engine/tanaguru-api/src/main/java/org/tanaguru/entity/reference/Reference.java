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
package org.tanaguru.entity.reference;

import java.util.List;
import org.tanaguru.sdk.entity.Entity;
import org.tanaguru.sdk.entity.Reorderable;

/**
 * 
 * @author jkowalczyk
 */
public interface Reference extends Entity, Reorderable {

    /**
     *
     * @param criterion
     *            the criterion to add
     */
    void addCriterion(Criterion criterion);

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the criterion list
     */
    List<Criterion> getCriterionList();

    /**
     *
     * @return the default level for the current referential
     */
    Level getDefaultLevel();
    
    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the label
     */
    String getLabel();

    /**
     *
     * @return the Url
     */
    String getUrl();

    /**
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

    /**
     *
     * @param criterionList
     *            the criterion list to set
     */
    void setCriterionList(List<Criterion> criterionList);
    
    /**
     * 
     * @param level 
     */
    void setDefaultLevel(Level level);

    /**
     *
     * @param description
     *            the description to set
     */
    void setDescription(String description);

    /**
     *
     * @param label
     *            the label to set
     */
    void setLabel(String label);

    /**
     *
     * @param url
     *            the url to set
     */
    void setUrl(String url);

}