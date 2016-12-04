package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Position;

public interface PositionDao extends BaseDao<Position> {
	/*
	 * 获取该社团职位
	 */
	List<Position> findByClubId(int id);
	/**
	 * 获得某社团的精确职位
	 * @param clubId
	 * @param Name
	 * @return
	 */
	Position getByClubIdAndName(int clubId,String Name);
	/**
	 * 根据社团ID删除职位
	 * @param clubId
	 */
	void deleteByClubId(int clubId);
}