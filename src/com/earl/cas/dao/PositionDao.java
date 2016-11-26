package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Position;

public interface PositionDao extends BaseDao<Position> {
	/*
	 * 获取该社团职位
	 */
	public List<Position> findByClubId(int id);

}