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
@Table(name = "USER")
@XmlRootElement
public class UserImpl implements User, Serializable {

	@Id
	@GeneratedValue
	@Column(name = "Id_User")
	protected Long id;
	@Column(name = "Password", nullable = false)
	protected String password;
	@ManyToMany
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "Id_User"), inverseJoinColumns = @JoinColumn(name = "Id_Role"))
	protected Collection<RoleImpl> roleList;
	@Column(name = "Username", nullable = false)
	protected String username;

	public UserImpl() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	@XmlElementWrapper
	@XmlAnyElement
	public Collection<RoleImpl> getRoleList() {
		return roleList;
	}

	public String getUsername() {
		return username;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleList(Collection<? extends Role> roleList) {
		this.roleList = (Collection<RoleImpl>) roleList;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
