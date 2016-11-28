package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.ClubType;


public interface ClubTypeService extends BaseService<ClubType> {
	/**
	 * 删除社团类型
	 */
	int deleteById(Integer id);
	
	/**
	 * 更新社团
	 */
	public Boolean update(ClubType clubs);

}