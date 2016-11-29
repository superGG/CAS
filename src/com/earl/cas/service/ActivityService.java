package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Activity;
import com.earl.cas.entity.Message;


public interface ActivityService extends BaseService<Activity> {

/*
 * 修改活动	
 */
	public Boolean updateActivity(Activity activity);
/*
 * 查看活动详情
 */
	 public List<Activity> findDetail(int id);

}