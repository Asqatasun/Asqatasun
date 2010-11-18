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
public class SourceCodeRemarkImpl extends ConsolidationRemarkImpl implements
        SourceCodeRemark {

    @Column(name = "Character_Position")
    protected int characterPosition;
    @Column(name = "Line_Number")
    protected int lineNumber;
    @Column(name = "Target", length = 5000)
    protected String target;
    
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

    public void setCharacterPosition(int characterPosition) {
        this.characterPosition = characterPosition;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
