package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Complain;
import com.earl.cas.vo.PageInfo;


public interface ComplainService extends BaseService<Complain> {

	/**
	 * 分页查询所有举报信息
	 */
	List<Complain> getAlls(PageInfo pageInfo);

	/*
	 * 查询举报详情
	 */
	Complain findById(Integer id);

	/*
	 * 根据类型查询
	 */
	List<Complain> getByType(Complain complain, PageInfo pageInfo);

	/*
	 * 根据状态查询
	 */
	List<Complain> getByStatus(Complain complain, PageInfo pageInfo);


}