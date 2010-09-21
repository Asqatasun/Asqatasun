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
	@Column(name = "Target", columnDefinition = "mediumtext")
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
