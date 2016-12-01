package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Album;


public interface AlbumService extends BaseService<Album> {

	/**
	 * 查询社团所有相册.
	 *@author 宋.
	 * @param id
	 * @return
	 */
	List<Album> getByClubId(Integer id);

	

}