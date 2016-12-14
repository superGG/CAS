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
import com.earl.cas.dao.ComplainDao;
import com.earl.cas.dao.UserDetailsDao;
import com.earl.cas.entity.Complain;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.service.ComplainService;
import com.earl.cas.vo.PageInfo;

/**
 * Complain的service层实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("complainService")
@Transactional
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements
		ComplainService {

	private static Logger logger = LoggerFactory
			.getLogger(ComplainServiceImpl.class);

	@Resource
	private ComplainDao complainDao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;


	@Override
	protected BaseDao<Complain> getDao() {
		return complainDao;
	}


	@Override
	public List<Complain> getAlls(PageInfo pageInfo) {
		List<Complain> list = complainDao.findAll(pageInfo);
		return setUserName(list);
	}
		
	private List<Complain> setUserName(List<Complain> list) {
		logger.info("给每个complain设置userName");
		List<Complain> newList = new ArrayList<Complain>();
		UserDetails userDetail = new UserDetails();
		for(Complain complain : list){
			userDetail = userDetailsDao.get(complain.getDetailId());
			complain.setUserName(userDetail.getName());
			newList.add(complain);
		}
		return newList;
	}

}
