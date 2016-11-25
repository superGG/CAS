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
 * Club generated by hbm2java
 */
@Entity
@Table(name = "club", catalog = "clubsystem")
public class Club implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private ClubType clubType;
	private School school;
	private String name;
	private String leader;
	private Date createtime;
	private String introduce;
	private String phone;
	private String email;
	private Set<Activity> activities = new HashSet<Activity>(0);
	private Set<Position> positions = new HashSet<Position>(0);
	private Set<Apply> applies = new HashSet<Apply>(0);
	private Set<Userclub> userclubs = new HashSet<Userclub>(0);
	private Set<Album> albums = new HashSet<Album>(0);

	public Club() {
	}

	public Club(int id, Date createtime) {
		this.id = id;
		this.createtime = createtime;
	}

	public Club(int id, ClubType clubType, School school, String name,
			String leader, Date createtime, String introduce, String phone,
			String email, Set<Activity> activities, Set<Position> positions,
			Set<Apply> applies, Set<Userclub> userclubs, Set<Album> albums) {
		this.id = id;
		this.clubType = clubType;
		this.school = school;
		this.name = name;
		this.leader = leader;
		this.createtime = createtime;
		this.introduce = introduce;
		this.phone = phone;
		this.email = email;
		this.activities = activities;
		this.positions = positions;
		this.applies = applies;
		this.userclubs = userclubs;
		this.albums = albums;
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
	@JoinColumn(name = "type_id")
	public ClubType getClubType() {
		return this.clubType;
	}

	public void setClubType(ClubType clubType) {
		this.clubType = clubType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public School getSchool() {
		return this.school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "leader")
	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Transient   //设置该属性后标致该属性不持久化数据库，由数据库自己管理
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "introduce", length = 65535)
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
	public Set<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
	public Set<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
	public Set<Apply> getApplies() {
		return this.applies;
	}

	public void setApplies(Set<Apply> applies) {
		this.applies = applies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
	public Set<Userclub> getUserclubs() {
		return this.userclubs;
	}

	public void setUserclubs(Set<Userclub> userclubs) {
		this.userclubs = userclubs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", clubType=" + clubType + ", school="
				+ school + ", name=" + name + ", leader=" + leader
				+ ", createtime=" + createtime + ", introduce=" + introduce
				+ ", phone=" + phone + ", email=" + email + ", activities="
				+ activities + ", positions=" + positions + ", applies="
				+ applies + ", userclubs=" + userclubs + ", albums=" + albums
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leader == null) ? 0 : leader.hashCode());
		result = prime * result
				+ ((clubType == null) ? 0 : clubType.hashCode());
		result = prime * result
				+ ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((introduce == null) ? 0 : introduce.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Club other = (Club) obj;
		if (leader == null) {
			if (other.leader != null)
				return false;
		} else if (!leader.equals(other.leader))
			return false;
		if (clubType == null) {
			if (other.clubType != null)
				return false;
		} else if (!clubType.equals(other.clubType))
			return false;
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
		if (id != other.id)
			return false;
		if (introduce == null) {
			if (other.introduce != null)
				return false;
		} else if (!introduce.equals(other.introduce))
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
		return true;
	}
}
