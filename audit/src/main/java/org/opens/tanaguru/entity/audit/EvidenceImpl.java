package org.opens.tanaguru.entity.audit;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EVIDENCE")
@XmlRootElement
public class EvidenceImpl implements Evidence, Serializable {

    private static final long serialVersionUID = -5891443605163793783L;
    @Column(name = "Cd_Evidence")
    protected String code;
    @Column(name = "Description")
    protected String description;
    @OneToMany(mappedBy = "evidence", cascade = CascadeType.ALL)
    protected Collection<EvidenceElementImpl> elementList = new HashSet<EvidenceElementImpl>();
    @Id
    @GeneratedValue
    @Column(name = "Id_Evidence")
    protected Long id;
    @Column(name = "Long_Label")
    protected String longLabel;

    public EvidenceImpl() {
        super();
    }

    public EvidenceImpl(String code) {
        this();
        this.code = code;
    }

    @Override
    public void addElement(EvidenceElement element) {
        element.setEvidence(this);
        elementList.add((EvidenceElementImpl) element);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.EvidenceElementImpl.class)})
    public Collection<EvidenceElementImpl> getElementList() {
        return elementList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Collection<Integer> getIntegerValueList() {
//        Collection<Integer> values = new HashSet<Integer>();
//        for (EvidenceElement element : elementList) {
//            NomenclatureCssUnit cssUnitElement = (NomenclatureCssUnit) element;
//            values.add(element.getCssShortValue());
//        }
//        return values;
        return null;
    }

    @Override
    public String getLongLabel() {
        return longLabel;
    }

    @Override
    public Collection<String> getValueList() {
        Collection<String> values = new HashSet<String>();
        for (EvidenceElement element : elementList) {
            values.add(element.getValue());
        }
        return values;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setElementList(
            Collection<? extends EvidenceElement> elementList) {
        this.elementList = (Collection<EvidenceElementImpl>) elementList;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }
    
}