package com.earl.cas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * UserDetalis generated by hbm2java
 */
@Entity
@Table(name = "user_details", catalog = "clubsystem")
public class UserDetails implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Boolean sex;
	private String phone;
	private String email;
	private String hobby;
	private String singnation;
	private String headPath;
	private Integer roleId;
	private Date createtime;
	private Set<User> users = new HashSet<User>(0);

	public UserDetails() {
	}

	public UserDetails(int id, Date createtime) {
		this.id = id;
		this.createtime = createtime;
	}

	public UserDetails(int id, String name, Boolean sex, String phone,
			String email, String hobby, String singnation, String headPath,
			Integer roleId, Date createtime, Set<User> users) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.hobby = hobby;
		this.singnation = singnation;
		this.headPath = headPath;
		this.roleId = roleId;
		this.createtime = createtime;
		this.users = users;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex")
	public Boolean getSex() {
		return this.sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "hobby")
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Column(name = "singnation")
	public String getSingnation() {
		return this.singnation;
	}

	public void setSingnation(String singnation) {
		this.singnation = singnation;
	}

	@Column(name = "head_path")
	public String getHeadPath() {
		return this.headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	@Column(name = "role_id")
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Transient   //设置该属性后标致该属性不持久化数据库，由数据库自己管理
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userDetalis")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserDetalis [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", phone=" + phone + ", email=" + email + ", hobby=" + hobby
				+ ", singnation=" + singnation + ", headPath=" + headPath
				+ ", roleId=" + roleId + ", createtime=" + createtime
				+ ", users=" + users + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((headPath == null) ? 0 : headPath.hashCode());
		result = prime * result + ((hobby == null) ? 0 : hobby.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result
				+ ((singnation == null) ? 0 : singnation.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetails other = (UserDetails) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (headPath == null) {
			if (other.headPath != null)
				return false;
		} else if (!headPath.equals(other.headPath))
			return false;
		if (hobby == null) {
			if (other.hobby != null)
				return false;
		} else if (!hobby.equals(other.hobby))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (singnation == null) {
			if (other.singnation != null)
				return false;
		} else if (!singnation.equals(other.singnation))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	
}
