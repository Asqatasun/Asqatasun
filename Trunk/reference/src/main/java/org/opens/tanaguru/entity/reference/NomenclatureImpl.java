package org.opens.tanaguru.entity.reference;

import org.opens.tanaguru.entity.service.reference.NomenclatureCssUnit;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "NOMENCLATURE")
@XmlRootElement
public class NomenclatureImpl implements Nomenclature, Serializable {

    @Column(name = "Cd_Nomenclature")
    protected String code;
    @Column(name = "Description")
    protected String description;
    @OneToMany(mappedBy = "nomenclature", cascade = CascadeType.ALL)
    protected Collection<NomenclatureElementImpl> elementList = new HashSet<NomenclatureElementImpl>();
    @Id
    @GeneratedValue
    @Column(name = "Id_Nomenclature")
    protected Long id;
    @Column(name = "Long_Label")
    protected String longLabel;
    @ManyToOne
    @JoinColumn(name = "Id_Nomenclature_Parent")
    protected NomenclatureImpl parent;
    @Column(name = "Short_Label")
    protected String shortLabel;

    public NomenclatureImpl() {
        super();
    }

    public NomenclatureImpl(String code) {
        this();
        this.code = code;
    }

    public void addElement(NomenclatureElement element) {
        element.setNomenclature(this);
        elementList.add((NomenclatureElementImpl) element);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.reference.NomenclatureElementImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.reference.NomenclatureCssUnitImpl.class)})
    public Collection<NomenclatureElementImpl> getElementList() {
        return elementList;
    }

    public Long getId() {
        return id;
    }

    public Collection<Integer> getIntegerValueList() {
        Collection<Integer> values = new HashSet<Integer>();
        for (NomenclatureElement element : elementList) {
            NomenclatureCssUnit cssUnitElement = (NomenclatureCssUnit) element;
            values.add(cssUnitElement.getCssShortValue());
        }
        return values;
    }

    public String getLongLabel() {
        return longLabel;
    }

    public Nomenclature getParent() {
        return parent;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public Collection<String> getValueList() {
        Collection<String> values = new HashSet<String>();
        for (NomenclatureElement element : elementList) {
            values.add(element.getLabel());
        }
        return values;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElementList(
            Collection<? extends NomenclatureElement> elementList) {
        this.elementList = (Collection<NomenclatureElementImpl>) elementList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    public void setParent(Nomenclature parent) {
        this.parent = (NomenclatureImpl) parent;
    }

    public void setShortLabel(String label) {
        this.shortLabel = label;
    }
}
