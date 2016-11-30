package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.dao.UserclubDao;
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
	private PositionDao positionDao;
	
	@Override
	protected BaseDao<Userclub> getDao() {
		return userclubDao;
	}

	public boolean deleteByapplyId(int applyId) {
		int flag = userclubDao.deleteByapplyId(applyId);
		if (flag != 0) {
			return true;
		} else {
			throw new DomainSecurityException("删除失败");
		}
	}

	public boolean updatePosition(int clubId,String positionName) {
		//根据positionName和clubId找到positionId
		Position position = positionDao.getByClubIdAndName(clubId,positionName);
		
		//update userclub表的positionId
		//
	}
}
