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
package org.tanaguru.i18n.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import org.tanaguru.entity.reference.Reference;
import org.tanaguru.entity.reference.ReferenceImpl;
import org.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "REFERENCE_I18N")
public class ReferenceI18nImpl extends AbstractInternationalizedEntity<Reference> implements ReferenceI18n,
        Serializable {

    private static final long serialVersionUID = -5028723795491732173L;

    @Column(name = "Description")
    private String description;
    @Column(name = "Label")
    private String label;
    @ManyToOne
    @JoinColumn(name = "Id_Reference")
    private ReferenceImpl target;

    public ReferenceI18nImpl() {
        super();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Reference getTarget() {
        return target;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setTarget(Reference target) {
        this.target = (ReferenceImpl) target;
    }

}