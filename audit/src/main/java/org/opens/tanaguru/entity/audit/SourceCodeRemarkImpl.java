package org.opens.tanaguru.entity.audit;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class SourceCodeRemarkImpl extends ProcessRemarkImpl implements
        SourceCodeRemark {


    @Column(name = "Character_Position")
    protected int characterPosition;
    @Column(name = "Line_Number")
    protected int lineNumber;
    @Column(name = "Target", length = 5000)
    protected String target;
    @OneToMany(mappedBy = "evidence", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    protected Collection<EvidenceElementImpl> elementList = new HashSet<EvidenceElementImpl>();
    
    public SourceCodeRemarkImpl() {
        super();
    }


    public int getCharacterPosition() {
        return characterPosition;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getTarget() {
        return target;
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

    public void setCharacterPosition(int characterPosition) {
        this.characterPosition = characterPosition;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public void setElementList(
            Collection<? extends EvidenceElement> elementList) {
        this.elementList = (Collection<EvidenceElementImpl>) elementList;
    }

    
}
