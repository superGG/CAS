package com.earl.cas.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.ClubTypeDao;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.ClubType;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ClubTypeService;

/**
 * clubTypeService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("clubTypeService")
@Transactional
public class ClubTypeServiceImpl extends BaseServiceImpl<ClubType> implements
		ClubTypeService {

	private static Logger logger = LoggerFactory
			.getLogger(ClubTypeServiceImpl.class);

	@Resource
	private ClubTypeDao clubTypeDao;
	
	@Autowired
	private ClubDao clubDao;

	@Override
	protected BaseDao<ClubType> getDao() {
		return clubTypeDao;
	}
	
	public int deleteById(Integer id) {
		logger.info("进入社团类别删除判断");
		Club club = new Club();
		club.setTypeId(id);
		List<Club> list = clubDao.findByGivenCriteria(club);
		if (!list.isEmpty()) {
			throw new DomainSecurityException("有社团属于该类别，不能删除");
		}
		return clubTypeDao.delete(id);
	}

	public Boolean update(ClubType clubtype) {
	    return clubTypeDao.update(clubtype); 
		 
	}
	

	//获取社团类型名称
	@Override
	public List<ClubType> getByClubTypeName(ClubType clubtype) {
		List<ClubType> typename = clubTypeDao.getTypeName(clubtype);
		return typename;
	}
}
