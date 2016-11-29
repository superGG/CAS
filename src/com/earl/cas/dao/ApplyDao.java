package com.earl.cas.dao;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Apply;


public interface ApplyDao extends BaseDao<Apply>{
	/**
	 * 对申请表进行更新
	 * @return
	 */
	public boolean update(Apply apply);
	
    	
}