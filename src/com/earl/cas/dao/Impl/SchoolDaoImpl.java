package com.earl.cas.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.SchoolDao;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.ClubType;
import com.earl.cas.entity.School;
import com.earl.cas.vo.PageInfo;

/**
 * schoolDao的实现类
 *@author 宋
 *@date 2016-8-15
 */
@Repository("schoolDao")
public class SchoolDaoImpl extends BaseDaoImpl<School> implements SchoolDao {
	
	@Override
	public int deleteById(Integer id){
		String hql = "delete from School where id = ?";
		return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();		
	}
	
	@Override
	public boolean update(School school){
		String hql = "update School set name = :name where id = :id";
		int flag=getCurrentSession().createQuery(hql).setString("name",school.getName()).setInteger("id", school.getId()).executeUpdate();
		if(flag!=0){
			return true;
		}
		else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<School> getSchoolName(School school) {
		String hql = "from School where name = :name ";
		List<School> schoolname= getCurrentSession().createQuery(hql).setString("name",school.getName()).list();
		return schoolname;
	}
	
	public School getByName(String schoolName){
		String hql = "from School where name = :name ";
		School school= (School)getCurrentSession().createQuery(hql).setString("name",schoolName).uniqueResult();
		return school;
	}
	
	@SuppressWarnings("unchecked")
	public List<School> getBySearch(String search){
		String hql = "from School where  name like :search ";
		List<School> list = getCurrentSession()
				.createQuery(hql)
				.setString("search", search).list();
		return list;
	}

}