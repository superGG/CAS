package com.earl.cas.dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.entity.Club;
import com.earl.cas.vo.PageInfo;

/**
 * clubDao的实现类
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Repository("clubDao")
public class ClubDaoImpl extends BaseDaoImpl<Club> implements ClubDao {

	@SuppressWarnings("unchecked")
	public List<Club> getByName(String clubName) {
		String hql = "from Club where  name = :name";
		List<Club> clublist = getCurrentSession().createQuery(hql)
				.setString("name", clubName).list();
		// logger.info(list.toString());
		return clublist;
	}

	public boolean update(Club club) {
		/*
		 * String hql = "update Club  where id = :id"; int
		 * flag=getCurrentSession().createQuery(hql).setInteger("id",
		 * club.getId()).executeUpdate(); if(flag!=0){ return true; } else{
		 * return false; }
		 */
		try {
			getCurrentSession().update(club);
			getCurrentSession().flush();
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	public Club getClubByuserDetailId(int detailId) {
		String hql = "from Club where detailId = :detailId";
		Club club = (Club) getCurrentSession().createQuery(hql)
				.setInteger("detailId", detailId).uniqueResult();
		return club;
	}

	public Club getByNameAndSchool(String Name, int schoolId) {
		String hql = "from Club where name = :name and schoolId = :schoolId";
		Club club = (Club) getCurrentSession().createQuery(hql)
				.setString("name", Name).setInteger("schoolId", schoolId)
				.uniqueResult();
		return club;
	}

	public List<Club> findAll(PageInfo pageInfo) {
		String hql = "from Club order by createtime desc ";
		@SuppressWarnings("unchecked")
		List<Club> list = getCurrentSession()
				.createQuery(hql)
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();

		String hql2 = "select count(*) from Club";
		Object uniqueResult = getCurrentSession().createQuery(hql2)
				.uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Club> getBySearch(String search, PageInfo pageInfo) {
		String hql = "from Club where name like :search ";
		List<Club> list = getCurrentSession()
				.createQuery(hql)
				.setString("search", "%" + search + "%")
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();

		String hql2 = "select count(*) from Club where name like :search";
		Object uniqueResult = getCurrentSession().createQuery(hql2)
				.setString("search", "%" + search + "%").uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Club> getBySchoolId(int schoolId, PageInfo pageInfo) {
		String hql = "from Club where schoolId = :schoolId ";
		List<Club> list = getCurrentSession()
				.createQuery(hql)
				.setInteger("schoolId", schoolId)
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();

		String hql2 = "select count(*) from Club where schoolId = :schoolId";
		Object uniqueResult = getCurrentSession().createQuery(hql2)
				.setString("schoolId", "%" + schoolId + "%").uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return list;
	}

	public Club getBySearchAndSchool(String search, Integer id) {
		String hql = "from Club where name like : search and schoolId = :id";
		Club club = (Club) getCurrentSession().createQuery(hql)
				.setString("search", "%" + search + "%").setInteger("id", id)
				.uniqueResult();
		return club;
	}

	@SuppressWarnings("unchecked")
	public List<Club> getByRank(PageInfo pageInfo){
		String hql = "select from Club left join Apply on id=Apply.clubId grounp by clubId order by count(*) desc";
	//	String hql = "select from Club where id = (select clubId from Apply group by clubId order by count(*) desc)";
		List<Club> list = getCurrentSession()
				.createQuery(hql)
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();

		String hql2 = "select count(*) from Club where schoolId = :schoolId";
		Object uniqueResult = getCurrentSession().createQuery(hql2).uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return list;		
	}
}