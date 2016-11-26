package com.earl.cas.entity;
// Generated 2016-11-21 9:28:51 by Hibernate Tools 5.2.0.Beta1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * Userclub generated by hbm2java
 */
@Entity
@Table(name = "userclub", catalog = "clubsystem")
public class Userclub implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//关联项编号
	private int id;
	
	//成员id
	private Integer applyId;
	
	//社团id
	private Integer clubId;
	
	//职位id
	private Integer positionId;
	
	//创建时间
	private String createtime;

	public Userclub() {
	}

	public Userclub(int id, String createtime) {
		this.id = id;
		this.createtime = createtime;
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

	@Column(name = "apply_id")
	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	@Column(name = "club_id")
	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	@Column(name = "position_id")
	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

//	@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.INSERT)
	@Column(name = "createtime")
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Userclub [id=" + id + ", applyId=" + applyId + ", clubId="
				+ clubId + ", positionId=" + positionId + ", createtime="
				+ createtime + "]";
	}

}
