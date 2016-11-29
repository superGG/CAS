package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.School;


public interface SchoolService extends BaseService<School> {
	/**
	 * 删除学校
	 */
	int deleteById(Integer id);
	/**
	 * 更新学校
	 */
	public void update(School school);
}