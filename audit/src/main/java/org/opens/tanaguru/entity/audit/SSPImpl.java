package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.PageImpl;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class SSPImpl extends ContentImpl implements SSP, Serializable {

    @Column(name = "Adapted_Content", length = 400000)
    protected String dom;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Id_Page")
    protected PageImpl page;

    @Column(name = "Source", length = 400000)
    protected String source;

    @Column(name = "Doctype", columnDefinition = "mediumtext")
    protected String doctype;

    @Column(name = "Charset")
    protected String charset;

//    @ManyToMany
//        @JoinTable(name = "CONTENT_RELATIONSHIP", joinColumns =
//        @JoinColumn(name = "Id_Content_Parent"), inverseJoinColumns =
//        @JoinColumn(name = "Id_Content_Child"))
    @ManyToMany(
        targetEntity=org.opens.tanaguru.entity.audit.RelatedContentImpl.class,
        mappedBy="parentContentSet", cascade=CascadeType.MERGE)
    protected Set<RelatedContentImpl> relatedContentSet =
            new HashSet<RelatedContentImpl>();

    public SSPImpl() {
        super();
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
    @XmlTransient
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
        content.addParentContent(this);
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
