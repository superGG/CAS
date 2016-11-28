package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.SchoolDao;
import com.earl.cas.entity.School;

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
	public void update(School school){
		String hql = "update School set name = :name where id = :id";
		getCurrentSession().createQuery(hql).setString("name",school.getName()).setInteger("id", school.getId()).executeUpdate();
	}

}