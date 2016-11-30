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
	private int applyId;// 入社申请书的ID
	private String position; // 职位
	private String majorClass; // 专业
	private String tel;// 电话
	private String createTime; // 入社申请

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

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMajorClass() {
		return majorClass;
	}

	public void setMajorClass(String majorClass) {
		this.majorClass = majorClass;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
