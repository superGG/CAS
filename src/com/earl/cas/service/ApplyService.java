package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Apply;
import com.earl.cas.vo.Member;

public interface ApplyService extends BaseService<Apply> {

	/**
	 * 同意申请->添加成员
	 * 
	 * @param apply
	 */
	void update(Apply apply);

	/**
	 * 通过创建人ID查找属于自己社团的申请书
	 * 
	 * @param id
	 * @return
	 */
	List<Apply> getClubApply(int id);

	/**
	 * 同意申请->非空更新
	 * 
	 * @param statue
	 */
	void update(int id, int statue);
	/**
	 * 获得社团成员列表
	 * @param applyId
	 * @return
	 */
	List<Member> getMember(int detaliId);
	/**
	 * 查看成员详情
	 * @param applyId
	 * @return
	 */
	Apply getMemberDetail(int applyId);
	/**
	 * 获得已通过审核的入社申请列表
	 * @param id
	 * @return
	 */
	List<Apply> getClubApplyIsOk(int id);
	/**
	 * 获得已审核的入社申请列表
	 * @param id
	 * @return
	 */
	List<Apply> getClubApplyHasExam(int id);
	/**
	 * 获得未审核的入社申请书
	 * @param id
	 * @return
	 */
	List<Apply> getClubApplyNotExam(int id);
	/**
	 * 拒绝申请
	 * @param applyId
	 * @param statue
	 */
	void notAgree(int applyId);

}