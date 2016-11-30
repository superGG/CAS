package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Userclub;


public interface UserclubService extends BaseService<Userclub> {
	/**
	 * 根据申请书ID剔除成员
	 * @param applyId
	 */
	boolean deleteByapplyId(int applyId);
	/**
	 * 修改成员职位
	 * @param positionName
	 * @return
	 */
	boolean updatePosition(int clubId,String positionName);
}