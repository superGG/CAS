package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Album;
import com.earl.cas.vo.PageInfo;


public interface AlbumService extends BaseService<Album> {

	/**
	 * 查询社团所有相册.
	 *@author 宋.
	 * @param id
	 * @return
	 */
	List<Album> getByClubId(Integer id);

	/*
	 * 根据Id获取详情.
	 */
	Album findById(Integer id);

	/*
	 * 获取所有相册.
	 */
	List<Album> getAlls(PageInfo pageInfo);

	/*
	 * 根据社团名称模糊查询社团相册
	 */
	List<Album> searchAll(String search, PageInfo pageInfo);

	

}