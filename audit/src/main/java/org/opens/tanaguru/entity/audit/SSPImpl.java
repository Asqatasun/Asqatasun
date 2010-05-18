package org.opens.tanaguru.entity.audit;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.PageImpl;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

    @ManyToOne
    @JoinColumn(name = "Id_Page")
    protected PageImpl page;

    @Column(name = "Source", length = 400000)
    protected String source;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name = "CONTENT_RELATIONSHIP", joinColumns =
    @JoinColumn(name = "Id_Content_Parent"), inverseJoinColumns =
    @JoinColumn(name = "Id_Content_Child"))
    protected List<RelatedContentImpl> relatedContentList =
            new ArrayList<RelatedContentImpl>();

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

    public String getDOM() {
        return dom;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.subject.PageImpl.class)
    public Page getPage() {
        return page;
    }

    public void setDOM(String dom) {
        this.dom = dom;
    }

    public void setPage(Page page) {
        this.page = (PageImpl) page;
    }

//    /**
//     * @param stylesheet
//     *            the stylesheet to set
//     */
//    public void setStylesheet(String stylesheet) {
//        this.stylesheet = stylesheet;
//    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.JavascriptContentImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.StylesheetContentImpl.class)})
    @XmlTransient
    public List<RelatedContentImpl> getRelatedContentList() {
        return relatedContentList;
    }

    public void addAllRelationContent(List<? extends RelatedContent> contentList) {
        for (RelatedContent content : contentList) {
            addRelatedContent(content);
        }
    }

    public void addRelatedContent(RelatedContent content) {
        content.addParentContent(this);
        this.relatedContentList.add((RelatedContentImpl) content);
    }

    @Override
    public String getAdaptedContent() {
        return dom;
    }

    @Override
    public void setAdaptedContent(String adaptedContent) {
        this.dom = adaptedContent;
    }

}
