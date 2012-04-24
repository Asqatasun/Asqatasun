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

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.opens.tgol.entity.option.Option;
import org.opens.tgol.entity.option.OptionImpl;
import org.opens.tgol.entity.product.Product;
import org.opens.tgol.entity.product.ProductImpl;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.entity.user.UserImpl;

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
        @JoinTable(name = "TGSI_CONTRACT_OPTION", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "OPTION_Id_Option"))
    Set<OptionImpl> optionSet = new HashSet<OptionImpl>();

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
    public Set<OptionImpl> getOptionSet() {
        return optionSet;
    }

    @Override
    public void addOption(Option option) {
        optionSet.add((OptionImpl)option);
    }

    @Override
    public void addAllOption(Set<? extends Option> optionSet) {
        this.optionSet.addAll((Set<OptionImpl>)optionSet);
    }

}