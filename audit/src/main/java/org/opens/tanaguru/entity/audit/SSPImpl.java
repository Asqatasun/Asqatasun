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

import javax.persistence.Column;
import javax.persistence.Entity;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.PageImpl;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class SSPImpl extends ContentImpl implements SSP, Serializable {

    private static final long serialVersionUID = -7889349852989199094L;
    @Column(name = "Adapted_Content", length = 16777215)
    protected String dom;

    @ManyToOne
    @JoinColumn(name = "Id_Page")
    protected PageImpl page;

    @Column(name = "Source", length = 16777215)
    protected String source;

    @Column(name = "Doctype", length = 512)
    protected String doctype;

    @Column(name = "Charset")
    protected String charset;

    @ManyToMany
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(name = "CONTENT_RELATIONSHIP", joinColumns =
    @JoinColumn(name = "Id_Content_Parent"), inverseJoinColumns =
    @JoinColumn(name = "Id_Content_Child"))
    protected Set<RelatedContentImpl> relatedContentSet =
            new HashSet<RelatedContentImpl>();

    public SSPImpl() {
        super();
    }

    public SSPImpl(String uri) {
        super(uri);
    }

    public SSPImpl(String uri, Page page) {
        super(uri);
        this.page = (PageImpl) page;
    }

    public SSPImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public SSPImpl(Date dateOfLoading, String uri, String sourceCode, Page page) {
        super(dateOfLoading, uri);
        this.source = sourceCode;
        this.page = (PageImpl) page;
    }

    public SSPImpl(Date dateOfLoading, String uri, int httpStatusCode) {
        super(dateOfLoading, uri, httpStatusCode);
    }

    public SSPImpl(Date dateOfLoading, 
            String uri,
            String sourceCode,
            Page page,
            int httpStatusCode) {
        super(dateOfLoading, uri, httpStatusCode);
        this.source = sourceCode;
        this.page = (PageImpl) page;
    }

    @Override
    public String getDOM() {
        return dom;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.subject.PageImpl.class)
    @Override
    @XmlTransient
    public Page getPage() {
        return page;
    }

    @Override
    public void setDOM(String dom) {
        this.dom = dom;
    }

    @Override
    public void setPage(Page page) {
        this.page = (PageImpl) page;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.JavascriptContentImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.StylesheetContentImpl.class)})
//    @XmlTransient
    @Override
    public Set<RelatedContentImpl> getRelatedContentSet() {
        return relatedContentSet;
    }

    @Override
    public void addAllRelationContent(Set<? extends RelatedContent> contentList) {
        for (RelatedContent content : contentList) {
            addRelatedContent(content);
        }
    }

    @Override
    public void addRelatedContent(RelatedContent content) {
        this.relatedContentSet.add((RelatedContentImpl) content);
    }

    @Override
    public String getAdaptedContent() {
        return dom;
    }

    @Override
    public void setAdaptedContent(String adaptedContent) {
        this.dom = adaptedContent;
    }

    @Override
    public String getCharset() {
        return charset;
    }

    @Override
    public String getDoctype() {
        return doctype;
    }

    @Override
    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

}
