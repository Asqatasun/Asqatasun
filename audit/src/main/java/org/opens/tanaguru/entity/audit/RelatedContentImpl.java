package org.opens.tanaguru.entity.audit;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class RelatedContentImpl extends ContentImpl implements
        RelatedContent, Serializable {

    @ManyToOne
    @JoinColumn(name = "Id_Ssp")
    protected SSPImpl ssp;

    @ManyToMany(targetEntity=org.opens.tanaguru.entity.audit.SSPImpl.class, mappedBy="relatedContentList", cascade=CascadeType.MERGE)
    protected List<ContentImpl> parentContentList =
            new ArrayList<ContentImpl>();

    public RelatedContentImpl() {
        super();
    }

    public RelatedContentImpl(Date dateOfLoading, String uri) {
        super(dateOfLoading, uri);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri, SSP ssp) {
        super(dateOfLoading, uri);
        this.ssp = (SSPImpl)ssp;
        this.parentContentList.add((SSPImpl) ssp);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri, int httpStatusCode) {
        super(dateOfLoading, uri, httpStatusCode);
    }

    public RelatedContentImpl(Date dateOfLoading, String uri, SSP ssp, int httpStatusCode) {
        super(dateOfLoading, uri, httpStatusCode);
        this.ssp = (SSPImpl)ssp;
        this.parentContentList.add((SSPImpl) ssp);
    }

    @XmlTransient
    public SSP getSSP() {
        return ssp;
    }

    public void setSSP(SSP ssp) {
        this.ssp = (SSPImpl) ssp;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.SSPImpl.class)})
    @XmlTransient
    public List<ContentImpl> getParentContentList() {
        return parentContentList;
    }

    public void addAllParentContent(List<? extends Content> contentList) {
        for (Content content : contentList) {
            addParentContent(content);
        }
    }

    public void addParentContent(Content content) {
        this.parentContentList.add((ContentImpl) content);
    }
}
