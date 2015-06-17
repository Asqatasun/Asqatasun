/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.entity.option;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_OPTION")
@XmlRootElement
public class OptionImpl implements Option, Serializable {

    private static final long serialVersionUID = 866337625495716065L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Option")
    private Long id;

    @Column(name = "Code")
    private String code;
    
    @Column(name = "Label")
    private String label;
    
    @Column(name = "Description")
    private String description;

    @Column(name = "Is_Restriction")
    private boolean isRestriction;
    
    @ManyToOne
    @JoinColumn(name = "OPTION_FAMILY_Id_Option_Family")
    private OptionFamilyImpl optionFamily;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
    
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public boolean isRestriction() {
        return isRestriction;
    }

    @Override
    public void setIsRestriction(boolean isRestriction) {
        this.isRestriction = isRestriction;
    }

    @Override
    public OptionFamily getOptionFamily() {
        return optionFamily;
    }

    @Override
    public void setOptionFamily(OptionFamily optionFamily) {
        this.optionFamily = (OptionFamilyImpl)optionFamily;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 37 * hash + (this.optionFamily != null ? this.optionFamily.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OptionImpl other = (OptionImpl) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        if (this.optionFamily != other.optionFamily && (this.optionFamily == null || !this.optionFamily.equals(other.optionFamily))) {
            return false;
        }
        return true;
    }
 
}