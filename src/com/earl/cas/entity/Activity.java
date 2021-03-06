package com.earl.cas.entity;
// Generated 2016-11-21 9:28:51 by Hibernate Tools 5.2.0.Beta1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.earl.cas.commons.domain.IdAnnotatioin;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Activity generated by hbm2java
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//jackson 控制，放回字段为null,将被过滤
@Entity
@Table(name = "activity", catalog = "clubsystem")
public class Activity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@IdAnnotatioin
	private Integer id;
	
	//社团id
	private Integer clubId;
	
	//活动主题
	private String title;
	
	//活动内容
	private String content;
	
	//创建时间
	private String createtime;
	
	//社团名称
	private String clubName;
	
	//学校名字
	private String schoolName;

	public Activity() {
	}

	public Activity(int id, String createtime) {
		this.id = id;
		this.createtime = createtime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "club_id")
	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Transient 
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	@Transient 
	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	//@Transient  设置该属性后标致该属性不持久化数据库，由数据库自己管理
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
		return "Activity [id=" + id + ", clubId=" + clubId + ", title="
				+ title + ", content=" + content + ", createtime=" + createtime
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clubId == null) ? 0 : clubId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Activity other = (Activity) obj;
		if (clubId == null) {
			if (other.clubId != null)
				return false;
		} else if (!clubId.equals(other.clubId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
