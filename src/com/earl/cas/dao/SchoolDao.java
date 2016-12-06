package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.ClubType;
import com.earl.cas.entity.School;
import com.earl.cas.vo.PageInfo;

public interface SchoolDao extends BaseDao<School> {
	/**
	 * 通过id删除学校
	 */
	int deleteById(Integer id);

	/**
	 * 更新学校
	 */
	boolean update(School school);

	/**
	 * 查找所有学校
	 * @return
	 */
	List<School> getSchoolName(School school);
	/**
	 * getByName
	 * @param schoolName
	 * @return
	 */
	School getByName(String schoolName);
	/**
	 * 模糊搜索
	 * @param search
	 * @param pageInfo 
	 * @return
	 */
	List<School> getBySearch(String search);

	
}