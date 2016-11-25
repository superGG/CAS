package com.earl.cas.entity;
// Generated 2016-11-21 9:28:51 by Hibernate Tools 5.2.0.Beta1

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Position generated by hbm2java
 */
@Entity
@Table(name = "position", catalog = "clubsystem")
public class Position implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	//社团
	private Club club;
	
	//创建时间
	private Date createtime;
	
	//职位名称
	private String name;
	
	private Set<Userclub> userclubs = new HashSet<Userclub>(0);

	public Position() {
	}

	public Position(int id, Date createtime) {
		this.id = id;
		this.createtime = createtime;
	}

	public Position(int id, Club club, Date createtime, String name,
			Set<Userclub> userclubs) {
		this.id = id;
		this.club = club;
		this.createtime = createtime;
		this.name = name;
		this.userclubs = userclubs;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id")
	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Transient   //设置该属性后标致该属性不持久化数据库，由数据库自己管理
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	public Set<Userclub> getUserclubs() {
		return this.userclubs;
	}

	public void setUserclubs(Set<Userclub> userclubs) {
		this.userclubs = userclubs;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", club=" + club + ", createtime="
				+ createtime + ", name=" + name + ", userclubs=" + userclubs
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Position other = (Position) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
