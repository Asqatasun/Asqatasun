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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.opens.tanaguru.entity.audit.Audit;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class SiteImpl extends WebResourceImpl implements Site, Serializable {

    private static final long serialVersionUID = -5472991643021548362L;
    @OneToMany(mappedBy = "parent")
    private final Collection<WebResourceImpl> componentList = new ArrayList<>();

    public SiteImpl() {
        super();
    }

    public SiteImpl(String url) {
        super(url);
    }

    @Override
    public void addAllChild(Collection<WebResource> webResourceList) {
        for (WebResource webResource : webResourceList) {
            addChild(webResource);
        }
    }

    @Override
    public void addChild(WebResource webResource) {
        webResource.setParent(this);

        if (webResource instanceof SiteImpl) {
            componentList.add((SiteImpl) webResource);
            return;
        }
        if (webResource instanceof PageImpl) {
            componentList.add((PageImpl) webResource);
        }
    }

    @Override
    public boolean contains(String url) {
        for (WebResourceImpl component : componentList) {
            if (component.getURL().equals(url)) {
                return true;
            }
        }
        return false;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.SiteImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.PageImpl.class)})
    @Override
    public Collection<WebResource> getComponentList() {
        // Bug #540 correction
        return (Collection) componentList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setComponentList(Collection<WebResource> componentList) {
        for (WebResource wr : componentList) {
            this.componentList.add((WebResourceImpl)wr);
        }
    }

    @Override
    public void setAudit(Audit audit) {
        super.setAudit(audit);
    }

}