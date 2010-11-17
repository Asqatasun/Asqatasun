package org.opens.tanaguru.entity.audit;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ConsolidationRemarkImpl extends ProcessRemarkImpl implements
        ConsolidationRemark {


    @OneToMany(mappedBy = "evidence", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    protected Collection<EvidenceElementImpl> elementList = new HashSet<EvidenceElementImpl>();
    
    public ConsolidationRemarkImpl() {
        super();
    }

    @Override
    public void addElement(EvidenceElement element) {
        element.setProcessRemark(this);
        elementList.add((EvidenceElementImpl) element);
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.EvidenceElementImpl.class)})
    public Collection<EvidenceElementImpl> getElementList() {
        return elementList;
    }

    @Override
    public void setElementList(
            Collection<? extends EvidenceElement> elementList) {
        this.elementList = (Collection<EvidenceElementImpl>) elementList;
    }

}