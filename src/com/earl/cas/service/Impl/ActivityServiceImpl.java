package com.earl.cas.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ActivityDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.entity.Activity;
import com.earl.cas.entity.Club;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ActivityService;
import com.earl.cas.vo.PageInfo;

/**
 * activityService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("activityService")
@Transactional
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements
ActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Autowired
	private ActivityDao activityDao;//spring自动注入，不需要去实例化就可以引用
	
	@Autowired
	private ClubDao clubDao;

	@Override
	protected BaseDao<Activity> getDao() {
		return activityDao;
	}
	
/*
 * 查看所有活动
 */
	@Override
	public List<Activity> findAllActivity(){
		List<Activity> allList = activityDao.findAll();
		if(allList != null){
			for(Activity activity:allList){
				Club club = clubDao.get(activity.getClubId());//从活动里提取出社团id
				activity.setClubName(club.getName());//将对应社团id的名字传进活动列表
			}
			return allList;
		} else{
			throw new DomainSecurityException("该活动页面没有活动 ");			
		}
	}
	
/*
 * 查看活动详情
 * (non-Javadoc)
 * @see com.earl.cas.service.ActivityService#findDetail(int)
 */
	@Override
	public Activity findDetail(int id) {
		Activity detail = activityDao.findDetail(id);
		if(detail==null){
			throw new DomainSecurityException("该活动已被删除 或不存在");
			
		} else{
			Club club = clubDao.get(detail.getClubId());//从活动表里抽取社团id
			detail.setClubName(club.getName());//将对应社团id的名字传进活动列表
			return detail;
		}
	}

/*
 * 更新活动
 * (non-Javadoc)
 * @see com.earl.cas.service.ActivityService#updateActivity(com.earl.cas.entity.Activity)
 */
	@Override
	public Boolean updateActivity(Activity activity) {
			Boolean update = activityDao.update(activity);
			return update;
	}

}
