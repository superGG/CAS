package com.earl.cas.entity;
// Generated 2016-11-21 9:28:51 by Hibernate Tools 5.2.0.Beta1

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Userclub generated by hbm2java
 */
@Entity
@Table(name = "userclub", catalog = "clubsystem")
public class Userclub implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Apply apply;
	private Club club;
	private Position position;
	private Date createtime;

	public Userclub() {
	}

	public Userclub(int id, Date createtime) {
		this.id = id;
		this.createtime = createtime;
	}

	public Userclub(int id, Apply apply, Club club, Position position,
			Date createtime) {
		this.id = id;
		this.apply = apply;
		this.club = club;
		this.position = position;
		this.createtime = createtime;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_id")
	public Apply getApply() {
		return this.apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id")
	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "positon_id")
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Transient   //设置该属性后标致该属性不持久化数据库，由数据库自己管理
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Userclub [id=" + id + ", apply=" + apply + ", club=" + club
				+ ", position=" + position + ", createtime=" + createtime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + id;
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
		Userclub other = (Userclub) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
