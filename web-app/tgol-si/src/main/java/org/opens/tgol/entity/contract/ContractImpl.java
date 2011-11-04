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

import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.product.ProductImpl;
import org.opens.tgol.entity.product.Restriction;
import org.opens.tgol.entity.product.RestrictionImpl;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_CONTRACT")
@XmlRootElement
public class ContractImpl implements Contract, Serializable {

    private static final long serialVersionUID = -8963515112270144367L;
    
    @Id
    @GeneratedValue
    @Column(name = "Id_Contract")
    private Long id;

    @Column(name = "Label", nullable=false)
    private String label;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "Begin_Date")
    private Date beginDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "End_Date")
    private Date endDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "Renewal_Date")
    private Date renewalDate;

    @Column(name = "Url")
    private String url;

    @Column(name = "Price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "USER_Id_User")
    private UserImpl user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_Id_Product")
    private ProductImpl product;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Set<ActImpl> actSet = new LinkedHashSet<ActImpl>();

    @ManyToMany
        @JoinTable(name = "TGSI_CONTRACT_RESTRICTION", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "RESTRICTION_Id_Restriction"))
    Set<RestrictionImpl> restrictionSet = new HashSet<RestrictionImpl>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long l) {
        this.id = l;
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
    public Date getBeginDate() {
        if (beginDate != null) {
            return new Date(beginDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setBeginDate(Date beginDate) {
        if (beginDate != null) {
            this.beginDate = new Date(beginDate.getTime());
        } else {
            this.beginDate = null;
        }
    }

    @Override
    public Date getEndDate() {
        if (endDate != null) {
            return new Date(endDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setEndDate(Date endDate) {
        if (endDate != null) {
            this.endDate = new Date(endDate.getTime());
        } else {
            this.endDate = null;
        }
    }

    @Override
    public Date getRenewalDate() {
        if (renewalDate != null) {
            return new Date(renewalDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setRenewalDate(Date renewalDate) {
        if (renewalDate != null) {
            this.renewalDate = new Date(renewalDate.getTime());
        } else {
            this.renewalDate = null;
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    @Override
    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = (UserImpl)user;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = (ProductImpl)product;
    }

    @Override
    public Set<? extends Act> getActSet() {
        return actSet;
    }

    @Override
    public void addAct(Act act) {
        actSet.add((ActImpl)act);
    }

    @Override
    public void addAllAct(Set<? extends Act> actSet) {
        this.actSet.addAll((Set<ActImpl>)actSet);
    }

    @Override
    public Set<RestrictionImpl> getRestrictionSet() {
        return restrictionSet;
    }

    @Override
    public void addRestriction(Restriction restriction) {
        restriction.addContract(this);
        restrictionSet.add((RestrictionImpl)restriction);
    }

    @Override
    public void addAllRestriction(Set<? extends Restriction> restrictionSet) {
        for (Restriction restriction : restrictionSet) {
            restriction.addContract(this);
        }
        this.restrictionSet.addAll((Set<RestrictionImpl>)restrictionSet);
    }

}