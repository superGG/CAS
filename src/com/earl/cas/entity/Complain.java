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

import com.earl.cas.commons.domain.IdAnnotatioin;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 投诉实体.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//jackson 控制，放回字段为null,将被过滤
@Entity
@Table(name = "complain", catalog = "clubsystem")
public class Complain implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@IdAnnotatioin //用于动态更新数据
	private Integer id;
	
	//举报人id
	private Integer detailId;
	
	//投诉类别
	private Integer type;  
	
	//举报内容
	private String content;
	
	//创建时间
	private String createtime;
	
	
	public Complain() {
	}

	public Complain(Integer id, String createtime) {
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

	@Column(name = "detail_id")
	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "createtime")
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Complain [id=" + id + ", detailId=" + detailId + ", type="
				+ type + ", content=" + content + ", createtime=" + createtime
				+ "]";
	}
	
}
