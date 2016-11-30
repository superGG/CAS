package com.earl.cas.dao;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.School;

public interface SchoolDao extends BaseDao<School> {
	/**
	 * 通过id删除学校
	 */
	int deleteById(Integer id);

	/**
	 * 更新学校
	 */
	boolean update(School school);
}