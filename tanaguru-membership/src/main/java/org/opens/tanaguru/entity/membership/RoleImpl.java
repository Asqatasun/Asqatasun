package org.opens.tanaguru.entity.membership;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ROLE")
@XmlRootElement
public class RoleImpl implements Role, Serializable {

	@Id
	@GeneratedValue
	@Column(name = "Id_Role")
	protected Long id;
	@Column(name = "Label", nullable = false)
	protected String label;
	@ManyToMany
	@JoinTable(name = "ROLE_GROUP", joinColumns = @JoinColumn(name = "Id_Role"), inverseJoinColumns = @JoinColumn(name = "Id_Office_Group"))
	protected Collection<OfficeGroupImpl> officeGroupList;
	@ManyToMany(mappedBy = "roleList")
	protected Collection<UserImpl> userList;

	public RoleImpl() {
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
	public Collection<OfficeGroupImpl> getOfficeGroupList() {
		return officeGroupList;
	}

	@XmlElementWrapper
	@XmlAnyElement
	public Collection<UserImpl> getUserList() {
		return userList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOfficeGroupList(
			Collection<? extends OfficeGroup> officeGroupList) {
		this.officeGroupList = (Collection<OfficeGroupImpl>) officeGroupList;
	}

	public void setUserList(Collection<? extends User> userList) {
		this.userList = (Collection<UserImpl>) userList;
	}
}
