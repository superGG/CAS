package com.earl.cas.service;

import java.util.List;

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
	 Boolean update(ClubType clubtype);
	
	
	/**
	 * 获取社团类型名字
	 */
	 List<ClubType> getByClubTypeName(ClubType clubtype);

}