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

@Entity
@XmlRootElement
public class SiteImpl extends WebResourceImpl implements Site, Serializable {

    private static final long serialVersionUID = -5472991643021548362L;
    @OneToMany(mappedBy = "parent")
    protected Collection<WebResourceImpl> componentList = new ArrayList<WebResourceImpl>();

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
    public Collection<WebResourceImpl> getComponentList() {
        return componentList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setComponentList(Collection<? extends WebResource> componentList) {
        this.componentList = (Collection<WebResourceImpl>) componentList;
    }

    @Override
    public void setAudit(Audit audit) {
        super.setAudit(audit);
    }
}
