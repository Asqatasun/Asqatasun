package org.opens.tanaguru.entity.membership;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "OFFICE")
@XmlRootElement
public class OfficeImpl implements Office, Serializable {

	@ManyToOne
	@JoinColumn(name = "Id_Office_Group")
	protected OfficeGroupImpl group;
	@Id
	@GeneratedValue
	@Column(name = "Id_Office")
	protected Long id;
	@Column(name = "Label", nullable = false)
	protected String label;

	public OfficeImpl() {
		super();
	}

	@XmlElementRef(type = org.opens.tanaguru.entity.membership.OfficeGroupImpl.class)
	public OfficeGroup getGroup() {
		return group;
	}

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setGroup(OfficeGroup officeGroup) {
		this.group = (OfficeGroupImpl) officeGroup;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
