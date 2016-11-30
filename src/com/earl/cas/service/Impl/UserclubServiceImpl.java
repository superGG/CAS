package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.dao.UserclubDao;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.Position;
import com.earl.cas.entity.Userclub;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.UserclubService;

@Service("userclubService")
@Transactional
public class UserclubServiceImpl extends BaseServiceImpl<Userclub> implements
		UserclubService {

	private static Logger logger = LoggerFactory
			.getLogger(UserclubServiceImpl.class);

	@Resource
	private UserclubDao userclubDao;
	
	@Resource
	private ClubDao clubDao;
	
	@Resource
	private ApplyDao applyDao;
	
	@Resource
	private PositionDao positionDao;

	@Override
	protected BaseDao<Userclub> getDao() {
		return userclubDao;
	}

	public boolean deleteByapplyId(int applyId) {
		int flag_1 = userclubDao.deleteByapplyId(applyId);
		int flag_2 = applyDao.deleteById(applyId);
		
		if (flag_1!= 0 && flag_2!=0) {
			return true;
		} else {
			throw new DomainSecurityException("删除失败");
		}
	}

	public void updatePosition(int detaliId,int applyId, String positionName) {
		logger.info("更新职位业务逻辑");
		//session中有userDetailId ->clubId
		int clubId;
		Club club = clubDao.getClubByuserDetailId(detaliId);
		if(club!=null){
			 clubId = club.getId();
		}
		else{
			throw new DomainSecurityException("用户没有创建社团");
		}
		// 根据positionName和clubId找到positionId
		int positionId ;
		Position position =positionDao.getByClubIdAndName(clubId, positionName);
		if(position!=null){
			positionId = position.getId();
		}
		else{
			throw new DomainSecurityException("社团中没有该职位");
		}
		//通过applyId获得对应的userclub
		Userclub userclub = userclubDao.getUserclubByApplyId(applyId);
		if(userclub!=null){
			// 更新职位ID
			userclub.setPositionId(positionId);
			// update userclub表的positionId
			userclubDao.updateWithNotNullProperties(userclub);
		}
		else{
			throw new DomainSecurityException("社团没有该成员");
		}
	}
}
