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
 * Clubcreate generated by hbm2java
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//jackson 控制，放回字段为null,将被过滤
@Entity
@Table(name = "clubcreate", catalog = "clubsystem")
public class Clubcreate implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//申请表编号
	@IdAnnotatioin //用于动态更新数据
	private int id;
	
	//申请用户
	private Integer detailId;
	
	//社团名称
	private String name;
	
	//申请理由
	private String reason;
	
	//联系电话
	private String phone;
	
	//申请时间
	private String createtime;
	
	//申请状态   默认1（1申请中 2成功 3失败）
	private Integer statue;

	public Clubcreate() {
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

	@Column(name = "detail_id")
	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "reason", length = 65535)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Generated(GenerationTime.INSERT)
	@Column(name = "createtime")
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "statue")
	public Integer getStatue() {
		return this.statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	@Override
	public String toString() {
		return "Clubcreate [id=" + id + ", detailId=" + detailId + ", name="
				+ name + ", reason=" + reason + ", phone=" + phone
				+ ", createtime=" + createtime + ", statue=" + statue + "]";
	}

}
