package org.opens.tanaguru.entity.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    
    public SourceCodeRemarkImpl() {
        super();
    }

    @Override
    public int getCharacterPosition() {
        return characterPosition;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setCharacterPosition(int characterPosition) {
        this.characterPosition = characterPosition;
    }

    @Override
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

}