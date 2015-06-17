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
package org.tanaguru.i18n.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import org.tanaguru.entity.reference.StandardMessage;
import org.tanaguru.entity.reference.StandardMessageImpl;
import org.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "STANDARD_MESSAGE_I18N")
public class StandardMessageI18nImpl extends AbstractInternationalizedEntity<StandardMessage> implements
        StandardMessageI18n, Serializable {

    private static final long serialVersionUID = -4487266636916438749L;

    @Column(name = "Label")
    private String label;
    @ManyToOne
    @JoinColumn(name = "Id_Standard_Message")
    private StandardMessageImpl target;
    @Column(name = "Text")
    private String text;

    public StandardMessageI18nImpl() {
        super();
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public StandardMessage getTarget() {
        return target;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setTarget(StandardMessage target) {
        this.target = (StandardMessageImpl) target;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

}