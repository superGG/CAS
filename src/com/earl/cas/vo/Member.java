/**
 * 
 */
package com.earl.cas.vo;

/**
 * 社团成员的vo类
 * 
 * @author Mr.Chen
 * 
 */
public class Member {

	private int id;// id 用来计数
	private String name; // 成员名字
	private String positionName; // 职位
	private String majorClass; // 专业
	private String phone;// 电话
	private String createtime; // 入社申请

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajorClass() {
		return majorClass;
	}

	public void setMajorClass(String majorClass) {
		this.majorClass = majorClass;
	}


	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
