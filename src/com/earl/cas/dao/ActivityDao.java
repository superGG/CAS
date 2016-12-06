package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Activity;
import com.earl.cas.vo.PageInfo;

public interface ActivityDao extends BaseDao<Activity> {

	/*
	 * 修改活动
	 */
	Boolean update(Activity activity);

	/*
	 * 查找活动详情
	 */
	Activity findDetail(int id);

	/*
	 * 根据社团Id删除社团
	 * 
	 * @param clubId
	 */
	void deleteByClubId(int clubId);
	
	/*
	 * 根据社团id查找社团活动
	 */
	List<Activity> findByClubId(Integer clubId, PageInfo pageInfo);
}