package com.earl.cas.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ClubTypeDao;
import com.earl.cas.entity.ClubType;
import com.earl.cas.entity.Position;

/**
 * clubTypeDao的实现类
 *@author 宋
 *@date 2016-8-15
 */
@Repository("clubTypeDao")
public class ClubTypeDaoImpl extends BaseDaoImpl<ClubType> implements ClubTypeDao {

	@Override
	public int delete(Integer id){
		String hql = "delete from ClubType where id = :id";
		return getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
	}
	
	@Override
	 public Boolean update(ClubType clubtype){
		String hql = "update from ClubType set name= :name where id= :id";
		int flag=getCurrentSession().createQuery(hql).setString("name",clubtype.getName()).setInteger("id", clubtype.getId()).executeUpdate();
		if(flag!=0){
			return true;
		}
		else{
			return false;
		}
	}
	
    //获取输入社团类型名称是否在数据库中存在
	public List<ClubType> getTypeName(ClubType clubtype) {
		String hql = "from ClubType where name = :name ";
		List<ClubType> typename= getCurrentSession().createQuery(hql).setString("name",clubtype.getName()).list();
		return typename;
	}
	
	public ClubType getByName(String name){
		String hql = "from ClubType where name = :name ";
		ClubType clubtype = (ClubType) getCurrentSession().createQuery(hql).setString("name",name).uniqueResult();
		return clubtype;
	}

}