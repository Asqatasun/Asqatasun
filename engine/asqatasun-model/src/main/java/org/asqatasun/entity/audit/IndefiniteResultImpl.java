/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.entity.audit;

import org.asqatasun.entity.audit.IndefiniteResult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class IndefiniteResultImpl extends ProcessResultImpl implements
        IndefiniteResult, Serializable {

    private static final long serialVersionUID = -9026725317465914229L;

    @Column(name = "Indefinite_Value", length = 16777215)
    private String indefiniteValue;

    public IndefiniteResultImpl() {
        super();
    }

    /**
     * @return the indefiniteValue
     */
    @Override
    public String getIndefiniteValue() {
        return indefiniteValue;
    }

    @Override
    public Object getValue() {
        return getIndefiniteValue();
    }

    /**
     * @param indefiniteValue the indefiniteValue to set
     */
    @Override
    public void setIndefiniteValue(String indefiniteValue) {
        this.indefiniteValue = indefiniteValue;
    }

    @Override
    public void setValue(Object value) {
        setIndefiniteValue((String) value);
    }

    @Override
    public Object getManualValue() {
        return null;
    }

    @Override
    public void setManualValue(Object manualValue) {
        // DO NOTHING
    }

}
