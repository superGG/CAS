package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ActivityDao;
import com.earl.cas.entity.Activity;

/**
 * activityDao的实现类
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Repository("activityDao")
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements
		ActivityDao {
	
	/*
	 * 查找活动详情 (non-Javadoc)
	 * 
	 * @see com.earl.cas.dao.ActivityDao#find(int)
	 */
	@Override
	public Activity findDetail(int id) {
		String hql = "from Activity where id= :id";
		Activity detail = (Activity) getCurrentSession()
				.createQuery(hql).setInteger("id", id).uniqueResult();
		return detail;
	}

	/*
	 * 修改活动 (non-Javadoc)
	 * 
	 * @see com.earl.cas.dao.ActivityDao#update(com.earl.cas.entity.Activity)
	 */
	@Override
	public Boolean update(Activity activity) {
		String hql = "update from Activity set title= :title,content= :content where id = :id";
		int flag = getCurrentSession().createQuery(hql)
				.setString("title", activity.getTitle())
				.setString("content", activity.getContent())
				.setInteger("id", activity.getId()).executeUpdate();
		if (flag != 0) {
			return true;
		} else {
			return false;
		}
	}

	

}