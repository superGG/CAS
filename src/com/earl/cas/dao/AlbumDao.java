package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Album;


public interface AlbumDao extends BaseDao<Album>{

	/**
	 * 查询社团所有相册.
	 *@author 宋.
	 * @param id
	 * @return
	 */
	List<Album> getByClubId(Integer id);
	/**
	 * 删除社团相册
	 * @param clubId
	 */
	void deleteByClubId(int clubId);
    
}