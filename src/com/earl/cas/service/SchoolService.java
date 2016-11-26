package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.School;


public interface SchoolService extends BaseService<School> {
	/**
	 * 删除学校
	 */
	@Override
	public void deleteById(Integer id);

}