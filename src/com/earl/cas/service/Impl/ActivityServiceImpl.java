package com.earl.cas.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ActivityDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.SchoolDao;
import com.earl.cas.entity.Activity;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.School;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ActivityService;
import com.earl.cas.vo.PageInfo;

/**
 * activityService实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("activityService")
@Transactional
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements
		ActivityService {

	private static Logger logger = LoggerFactory
			.getLogger(ActivityServiceImpl.class);

	@Autowired
	private ActivityDao activityDao;// spring自动注入，不需要去实例化就可以引用

	@Autowired
	private ClubDao clubDao;
	
	@Autowired
	private SchoolDao schoolDao;


	@Override
	protected BaseDao<Activity> getDao() {
		return activityDao;
	}

	/*
	 * 查看所有活动
	 */
	@Override
	public List<Activity> findAllActivity(PageInfo pageInfo) {
		List<Activity> allList = activityDao.findAllActivity(pageInfo);
		if (!allList.isEmpty()) {
			return setName(allList);
		} else {
			throw new DomainSecurityException("该活动页面没有活动 ");
		}
	}

	/*
	 * 查看活动详情 (non-Javadoc)
	 * 
	 * @see com.earl.cas.service.ActivityService#findDetail(int)
	 */
	@Override
	public Activity findDetail(int id) {
		Activity detail = activityDao.findDetail(id);
		if (detail == null) {
			throw new DomainSecurityException("该活动已被删除 或不存在");

		} else {
			// 从活动表里抽取社团id,得到社团对象
			Club club = clubDao.get(detail.getClubId());
			// 从社团表里抽取学校id,得到学校对象
			School school = schoolDao.get(club.getSchoolId());
			// 将对应社团id的名字传进活动列表
			detail.setClubName(club.getName());
			// 将对应学校id的名字传进活动列表
			detail.setSchoolName(school.getName());
			return detail;
		}
	}

	/*
	 * 更新活动 (non-Javadoc)
	 * 
	 * @see
	 * com.earl.cas.service.ActivityService#updateActivity(com.earl.cas.entity
	 * .Activity)
	 */
	@Override
	public Boolean updateActivity(Activity activity) {
		Boolean update = activityDao.update(activity);
		return update;
	}
	
	/*根据社团id从社团表中找出社团名字和从学校表中找出学校名字并放进活动表中
	 * (non-Javadoc)
	 * @see com.earl.cas.service.ActivityService#findByClubId(com.earl.cas.entity.Activity, com.earl.cas.vo.PageInfo)
	 */
	@Override
	public List<Activity> findByClubId(Integer clubId, PageInfo pageInfo) {
		List<Activity> findByClubIdList = activityDao.findByClubId(clubId, pageInfo);
		if(!findByClubIdList.isEmpty()){
			return setName(findByClubIdList);
		} else{
			throw new DomainSecurityException("该社团没有发布活动 ");			
		}
	 }

/*
 * 将社团编号传进来,并返回活动对象
 * (non-Javadoc)
 * @see com.earl.cas.service.ActivityService#findByClubId(java.lang.Integer)
 */
@Override
public Activity findByClubId(Integer id) {
	Activity activity = activityDao.findDetail(id);
	Club club = clubDao.get(activity.getClubId());//从活动里提取出社团id
	School school = schoolDao.get(club.getSchoolId());
	activity.setClubName(club.getName());//将对应社团id的名字传进活动对象
	activity.setSchoolName(school.getName());
	return activity;
}

/*
 * 根据社团名字查找所有学校的社团活动
 * (non-Javadoc)
 * @see com.earl.cas.service.ActivityService#findByClubName(com.earl.cas.entity.Activity, com.earl.cas.vo.PageInfo)
 */
@Override
public List<Activity> findByClubName(String clubName) {
	if(clubName == null){
		throw new DomainSecurityException("社团名字不能为空");	
	} else{
	 List<Activity> addClubSchoolList = new ArrayList<Activity>();
	 List<Club> clubList = clubDao.getByName(clubName);
	 Activity activity = new Activity();
	 List<Activity> activityList = new ArrayList<Activity>();
	 for(Club club:clubList){
		 activity.setClubId(club.getId());
		 activityList = activityDao.findByGivenCriteria(activity);
		 addClubSchoolList.addAll(activityList);
	 }
	return setName(addClubSchoolList);
	}
}
 
/*
 * 模糊查询，根据输入的不完整的活动主题查找活动
 * (non-Javadoc)
 * @see com.earl.cas.service.ActivityService#findByInput(java.lang.String, com.earl.cas.vo.PageInfo)
 */
@Override
public List<Activity> findByInput(String input, PageInfo pageInfo) {
	List<Activity> findByInputList = activityDao.findByInput(input, pageInfo);
	if(!findByInputList.isEmpty()){
		return setName(findByInputList);
	} else{
		throw new DomainSecurityException("类似该主题的活动不存在 ");			
	}
}

/*
 * 将学校名字和社团名字加入到社团活动中去
 */
  private List<Activity>  setName(List<Activity> indexList){
	  Club club = new Club();
	  School school = new School();
	  for(Activity activity :indexList){
		 club = clubDao.get( activity.getClubId());
		 activity.setClubName(club.getName());
		 school = schoolDao.get(club.getSchoolId());
		 activity.setSchoolName(school.getName());
	  }
	  return indexList;
  }

}

