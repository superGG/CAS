package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Activity;


public interface ActivityDao extends BaseDao<Activity>{

/*
 * 修改活动	
 */
 Boolean update(Activity activity);
	
/*
 * 查找活动详情
 */
  List<Activity> findDetail(int id);
}