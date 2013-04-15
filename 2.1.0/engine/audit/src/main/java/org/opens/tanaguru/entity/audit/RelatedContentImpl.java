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
package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
@org.hibernate.annotations.Entity(
		selectBeforeUpdate = false,
		dynamicInsert = true,
		dynamicUpdate = true)
public class RelatedContentImpl extends ContentImpl implements
        RelatedContent, Serializable {

    private static final long serialVersionUID = 4167575969739916971L;
    @ManyToMany(
        targetEntity=org.opens.tanaguru.entity.audit.SSPImpl.class,
        mappedBy="relatedContentSet")
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<ContentImpl> parentContentSet =
            new HashSet<ContentImpl>();

    public RelatedContentImpl() {
        super();
    }

    public RelatedContentImpl(String uri, SSP ssp) {
        super(uri);
        if (ssp != null)
            this.parentContentSet.add((SSPImpl) ssp);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri);
        if (ssp != null)
            this.parentContentSet.add((SSPImpl) ssp);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri, int httpStatusCode) {
        super(dateOfLoading, uri, httpStatusCode);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri, SSP ssp, int httpStatusCode) {
        super(dateOfLoading, uri, httpStatusCode);
        if (ssp != null)
            this.parentContentSet.add((SSPImpl) ssp);
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.SSPImpl.class)})
    @XmlTransient
    @Override
    public Set<Content> getParentContentSet() {
        return (Set)parentContentSet;
    }

    @Override
    public void addAllParentContent(Set<Content> contentList) {
        for (Content content : contentList) {
            addParentContent(content);
        }
    }

    @Override
    public void addParentContent(Content content) {
        this.parentContentSet.add((ContentImpl) content);
    }
    
}