package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Apply;

public interface ApplyDao extends BaseDao<Apply> {
	/**
	 * 对申请表进行更新
	 * 
	 * @return
	 */
	boolean update(Apply apply);

	/**
	 * 通过userdetailsId查找申请书
	 * 
	 * @param id
	 * @return
	 */
	List<Apply> getApplyByClubId(int id);
	/**
	 * 找到已经通过审核的申请书
	 * @param id
	 * @return
	 */
	List<Apply> getApplyByClubIdStatusIsOk(int id);
	/**
	 * 通过社团ID找到该社团statue不等于2的入社申请书
	 * @param id
	 */
	List<Apply> getApplyByClubIdStatueNotTwo(int id);
	/**
	 * 通过社团ID找到该社团statue等于2的入社申请
	 * @param id
	 * @return
	 */
	List<Apply> getApplyByClubIdStatueIsTwo(int id);

}