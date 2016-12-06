package com.earl.cas.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ActivityDao;
import com.earl.cas.entity.Activity;
import com.earl.cas.vo.PageInfo;

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
		Activity detail = (Activity) getCurrentSession().createQuery(hql)
				.setInteger("id", id).uniqueResult();
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

	public void deleteByClubId(int clubId) {
		String hql = "delete Activity where clubId = :clubId";
		getCurrentSession().createQuery(hql).setInteger("clubId", clubId)
				.executeUpdate();
		getCurrentSession().flush();
	}

	/*
	 * 根据clubId查找社团活动 (non-Javadoc)
	 * 
	 * @see com.earl.cas.dao.ActivityDao#findByClubId(java.lang.Integer,
	 * com.earl.cas.vo.PageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> findByClubId(Integer clubId, PageInfo pageInfo) {
		String hql = "from Activity where clubId= :clubId order by createtime desc";
		@SuppressWarnings("unchecked")
		List<Activity> clubActivity = getCurrentSession()
				.createQuery(hql)
				.setInteger("clubId", clubId)
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();
		String hql2 = "select count(*) from Activity where clubId= :clubId";
		Object uniqueResult = getCurrentSession()
				.createQuery(hql2)
				.setInteger("clubId", clubId)
				.uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return clubActivity;
	}

}