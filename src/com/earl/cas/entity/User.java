package com.earl.cas.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.earl.cas.commons.domain.IdAnnotatioin;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="user")
@JsonInclude(JsonInclude.Include.NON_NULL) //jackson 控制，放回字段为null,将被过滤
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 字段描述：Integer 
	 * 字段类型：userId  
	 */
	@IdAnnotatioin
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	
	private String name;
	
	private String classname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String toString() {
		return "User [userId=" + id + ", name=" + name + ", classname="
				+ classname + "]";
	}
}
