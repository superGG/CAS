package com.earl.cas.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.Position;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.PositionService;

/**
 * positionService实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("positionService")
@Transactional
public class PositionServiceImpl extends BaseServiceImpl<Position> implements
		PositionService {

	private static Logger logger = LoggerFactory
			.getLogger(PositionServiceImpl.class);

	@Resource
	private PositionDao positionDao;

	@Override
	protected BaseDao<Position> getDao() {
		return positionDao;
	}

	@Resource
	private ClubDao clubDao;

	/*
	 * find position by club
	 * t
	 * @return List<Position>
	 */
	public List<Position> getByClubName(String clubName) {
		List<Club> clublist = clubDao.getByName(clubName);
		if(clublist==null){
			throw new DomainSecurityException("没有这个社团");
		}
		else{
			Club club=clublist.get(0);
			return positionDao.findByClubId(club.getId());
		}
	}
	
	public List<Position> getByClubId(int id) {
			return positionDao.findByClubId(id);
		}
	
	public void update(Position position){
		positionDao.updateWithNotNullProperties(position);
	}

}
