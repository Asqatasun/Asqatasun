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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class SSPImpl extends ContentImpl implements SSP, Serializable {

    @Column(name = "Dom", length = 400000)
    protected String dom;
    @Column(name = "Javascript", length = 400000)
    protected String javascript;
    @ManyToOne
    @JoinColumn(name = "Id_Page")
    protected PageImpl page;
    @Column(name = "Stylesheet", length = 400000)
    protected String stylesheet;
    @OneToMany(mappedBy = "ssp", cascade = CascadeType.ALL)
    protected List<RelatedContentImpl> relatedContentImpl = new ArrayList<RelatedContentImpl>();
    @Column(name = "Source", length = 400000)
    protected String source;

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

    public String getDOM() {
        return dom;
    }

    /**
     * @return the javascript
     */
    public String getJavascript() {
        return javascript;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.subject.PageImpl.class)
    public Page getPage() {
        return page;
    }

    /**
     * @return the stylesheet
     */
    public String getStylesheet() {
        return stylesheet;
    }

    public void setDOM(String dom) {
        this.dom = dom;
    }

    /**
     * @param javascript
     *            the javascript to set
     */
    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public void setPage(Page page) {
        this.page = (PageImpl) page;
    }

    /**
     * @param stylesheet
     *            the stylesheet to set
     */
    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

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
    public List<? extends RelatedContent> getRelatedContentImpl() {
        return relatedContentImpl;
    }

    public void setRelatedContentImpl(List<? extends RelatedContent> relatedContentImpl) {
        this.relatedContentImpl = (List<RelatedContentImpl>) relatedContentImpl;
    }
}
