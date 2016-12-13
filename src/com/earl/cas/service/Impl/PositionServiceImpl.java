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
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.entity.Apply;
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
	

	@Autowired
	private ApplyDao applyDao;
	
	@Override
	protected BaseDao<Position> getDao() {
		return positionDao;
	}

	/*
	 * find position by club
	 * t
	 * @return List<Position>
	 */

	
	public List<Position> getByClubId(int id) {
			return positionDao.findByClubId(id);
		}
	
	public void update(Position position){
		positionDao.updateWithNotNullProperties(position);
	}
	public List<String> getNameByClubId(int id){
		List<String> positionName =  new ArrayList<String>();

		List<Position> positionList = positionDao.findByClubId(id);
		for(Position position:positionList){
			positionName.add(position.getName());
		}
		return positionName;
	}
	public Position findById(Integer id){
		return	positionDao.get(id);
	}
	
	public Position getByClubIdAndName(Integer clubId, String name){
		return positionDao.getByClubIdAndName(clubId,name);
	}
	
	public int deleteById(Integer positionId){
		logger.info("进入删除社团职位判断");
		Apply apply = new Apply();
		apply.setPositionId(positionId);
		List<Apply> list = applyDao.findByGivenCriteria(apply);
		if (!list.isEmpty()) {
			throw new DomainSecurityException("有社团成员拥有该职位，不能删除");
		}
		return positionDao.deleteById(positionId);
		
	}
}
