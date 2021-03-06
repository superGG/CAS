package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Activity;
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
List<Activity> findByClubId(Integer clubId,PageInfo pageInfo);

/*
 * 将社团名字传进来
 */
Activity findByClubId(Integer id);

/*
 * 根据社团名字查找社团活动
 */
List<Activity> findByClubName(String clubName);

/*
 * 模糊查询，根据输入的不完整的title查询活动
 */
List<Activity> findByInput(String input, PageInfo pageInfo);



}