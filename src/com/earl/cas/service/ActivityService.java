package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Activity;
import com.earl.cas.entity.Message;
import com.earl.cas.vo.PageInfo;


public interface ActivityService extends BaseService<Activity> {

/*
 * 修改活动	
 */
	Boolean updateActivity(Activity activity);
	
/*
 * 查看所有活动
 */
	List<Activity> findAllActivity(PageInfo pageInfo);
		
/*
 * 查看活动详情
 */
	Activity findDetail(int id);
	
/*
 * 通过社团id查找社团活动
 */
  List<Activity> findByClubId(Activity activity,PageInfo pageInfo);

/*
 * 将社团名字传进来
 */
Activity findByClubId(Integer id);

}