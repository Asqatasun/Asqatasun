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
    protected Set<ContentImpl> parentContentSet =
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
    public Set<ContentImpl> getParentContentSet() {
        return parentContentSet;
    }

    public void addAllParentContent(Set<? extends Content> contentList) {
        for (Content content : contentList) {
            addParentContent(content);
        }
    }

    public void addParentContent(Content content) {
        this.parentContentSet.add((ContentImpl) content);
    }
}
