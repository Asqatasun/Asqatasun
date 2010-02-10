package org.opens.tanaguru.entity.subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.opens.tanaguru.entity.audit.Audit;

@Entity
@XmlRootElement
public class SiteImpl extends WebResourceImpl implements Site, Serializable {

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Collection<WebResourceImpl> componentList = new ArrayList<WebResourceImpl>();

    public SiteImpl() {
        super();
    }

    public SiteImpl(String url) {
        super(url);
    }

    public void addAllChild(Collection<WebResource> webResourceList) {
        for (WebResource webResource : webResourceList) {
            addChild(webResource);
        }
    }

    public void addChild(WebResource webResource) {
        webResource.setParent(this);

        if (webResource instanceof Site) {
            componentList.add((SiteImpl) webResource);
            return;
        }

        componentList.add((PageImpl) webResource);
    }

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
    public Collection<WebResourceImpl> getComponentList() {
        return componentList;
    }

    public void setComponentList(Collection<? extends WebResource> componentList) {
        this.componentList = (Collection<WebResourceImpl>) componentList;
    }

    @Override
    public void setAudit(Audit audit) {
        super.setAudit(audit);

        for (WebResource component : componentList) {
            component.setAudit(audit);
        }
    }
}
