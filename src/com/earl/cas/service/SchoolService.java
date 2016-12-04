package com.earl.cas.service;

import java.util.List;

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
	void update(School school);

	/**
	 * 查看学校名称
	 * @param school
	 * @return
	 */
	List<School> getBySchoolName(School school);
}