package com.earl.cas.entity;
// Generated 2016-11-21 9:28:51 by Hibernate Tools 5.2.0.Beta1

import java.io.Serializable;
import java.util.Set;

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
 * School generated by hbm2java
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//jackson 控制，放回字段为null,将被过滤
@Entity
@Table(name = "school", catalog = "clubsystem")
public class School implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@IdAnnotatioin //用于动态更新数据
	private Integer id;
	
	//学校名称
	private String name;
	
	//创建时间
	private String createtime;
	
	public School() {
	}

	public School(Integer id, String createtime) {
		this.id = id;
		this.createtime = createtime;
	}

	public School(Integer id, String name, String createtime, Set<Club> clubs) {
		this.id = id;
		this.name = name;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
		return "School [id=" + id + ", name=" + name + ", createtime="
				+ createtime + "]";
	}
}
