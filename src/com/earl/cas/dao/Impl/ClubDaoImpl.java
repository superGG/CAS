package com.earl.cas.dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.Position;

/**
 * clubDao的实现类
 *@author 宋
 *@date 2016-11-23
 */
@Repository("clubDao")
public class ClubDaoImpl extends BaseDaoImpl<Club> implements ClubDao {

	@SuppressWarnings("unchecked")
	public List<Club> getByName(String clubName){
		String hql = "from Club where  name = :name";
		List<Club> club = (List<Club>) getCurrentSession().createQuery(hql).setString("name",clubName).list();
		//	logger.info(list.toString());
		return club;
	}
	public boolean update(Club club){
		/*
		String hql = "update Club  where id = :id";
		int flag=getCurrentSession().createQuery(hql).setInteger("id", club.getId()).executeUpdate();
		if(flag!=0){
			return true;
		}
		else{
			return false;
		}
		*/
		try{
			getCurrentSession().update(club);
			getCurrentSession().flush();
			return true;
		}catch(HibernateException e){
			return false;
		}
	

	}
}