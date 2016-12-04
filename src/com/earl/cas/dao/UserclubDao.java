package com.earl.cas.dao;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Userclub;


public interface UserclubDao extends BaseDao<Userclub>{
	/**
	 * 根据申请书ID剔除成员
	 * @param applyId
	 * @return
	 */
	int deleteByapplyId(int applyId);
	/**
	 * 通过applyId获得userclub
	 * @param applyId
	 * @return
	 */
	Userclub getUserclubByApplyId(int applyId);
	/**
	 * 获得社团人数
	 * @return
	 */
	Long getNumberByclubId(int clubId);
	/**
	 * 根据clubId删除数据
	 * @param clubId
	 */
	void deleteByClubId(int clubId);
    
}