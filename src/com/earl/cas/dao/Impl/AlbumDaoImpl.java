package com.earl.cas.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.AlbumDao;
import com.earl.cas.entity.Album;
import com.earl.cas.vo.PageInfo;

/**
 * albumDao的实现类
 *@author 宋
 *@date 2016-11-23
 */
@Repository("albumDao")
public class AlbumDaoImpl extends BaseDaoImpl<Album> implements AlbumDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> getByClubId(Integer id) {
		String hql = "from Album where clubId = :id";
		return getCurrentSession().createQuery(hql).setInteger("id", id).list();
	}
	
	public void deleteByClubId(int clubId){
		String hql = "delete Album where clubId = :clubId";
		getCurrentSession().createQuery(hql).setInteger("clubId", clubId)
				.executeUpdate();
		getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> searchAll(String search, PageInfo pageInfo) {
		String hql = "select a from Album a, Club c where c.name like :search and a.clubId = c.id order by a.createtime desc";
		List<Album> list = getCurrentSession().createQuery(hql)
				.setString("search", "%" + search + "%")
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();
		
		String hql2 = "select count(*) from Album a, Club c where c.name like :search and a.clubId = c.id";
		Object uniqueResult = getCurrentSession().createQuery(hql2)
				.setString("search", "%" + search + "%").uniqueResult();
		Long intValue = Long.parseLong(uniqueResult.toString());
		pageInfo.setTotalCount(intValue);
		return list;
	}

}