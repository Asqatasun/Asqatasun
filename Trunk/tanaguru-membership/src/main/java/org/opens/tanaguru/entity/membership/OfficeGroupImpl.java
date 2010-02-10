package org.opens.tanaguru.entity.membership;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
@Entity
@Table(name = "OFFICE_GROUP")
@XmlRootElement
public class OfficeGroupImpl implements OfficeGroup, Serializable {

	@Id
	@GeneratedValue
	@Column(name = "Id_Office_Group")
	protected Long id;
	@Column(name = "Label")
	protected String label;
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	protected Collection<OfficeImpl> officeList;
	@ManyToMany(mappedBy = "officeGroupList")
	protected Collection<RoleImpl> roleList;

	public OfficeGroupImpl() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	@XmlElementWrapper
	@XmlAnyElement
	public Collection<OfficeImpl> getOfficeList() {
		return officeList;
	}

	@XmlElementWrapper
	@XmlAnyElement
	public Collection<RoleImpl> getRoleList() {
		return roleList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOfficeList(Collection<? extends Office> offices) {
		this.officeList = (Collection<OfficeImpl>) offices;
	}

	public void setRoleList(
			Collection<? extends org.opens.tanaguru.entity.membership.Role> roleList) {
		this.roleList = (Collection<RoleImpl>) roleList;
	}
}
