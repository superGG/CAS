package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.ClubType;


public interface ClubTypeService extends BaseService<ClubType> {
	/**
	 * 删除学校
	 */
	int deleteById(Integer id);

}