package com.earl.cas.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.MessageDao;
import com.earl.cas.entity.Message;
import com.earl.cas.vo.PageInfo;

/**
 * messageDao的实现类
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {
	/*
	 * 更新留言 (non-Javadoc)
	 * 
	 * @see com.earl.cas.dao.MessageDao#update(com.earl.cas.entity.Message)
	 */
	@Override
	public Boolean update(Message message) {
		String hql = "update from Message set content= :content where id= :id";
		int flag = getCurrentSession().createQuery(hql)
				.setString("content", message.getContent())
				.setInteger("id", message.getId()).executeUpdate();
		if (flag != 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 通过father_id查找子留言
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findDetail(int fatherId) {
		String hql = "from Message where fatherId= :fatherId order by createtime desc";
		List<Message> detaillist = (List<Message>) getCurrentSession()
				.createQuery(hql).setInteger("fatherId", fatherId).list();
		return detaillist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> searchAlls(String search, PageInfo pageInfo) {
		String hql = "select m from Message m, UserDetails u where ((m.content like :search or u.name like :name) and m.detailId = u.id) order by m.createtime desc";
		List<Message> list = getCurrentSession().createQuery(hql)
				.setString("search", "%" + search + "%")
				.setString("name", "%" + search + "%")
				.setFirstResult((pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();
		
		String hql2 = "select m from Message m, UserDetails u where ((m.content like :search or u.name like :name) and m.detailId = u.id) order by m.createtime desc";
		List<Message> list2 = getCurrentSession().createQuery(hql2)
				.setString("search", "%" + search + "%")
				.setString("name", "%" + search + "%")
				.list();
		Long intValue = (long) list2.size();
		pageInfo.setTotalCount(intValue);
		return list;
	}
	
	public List<Message> findAll(PageInfo pageInfo) {
		String hql = "from Message order by createtime desc";
		@SuppressWarnings("unchecked")
		List<Message> list = getCurrentSession()
				.createQuery(hql)
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();

		String hql2 = "select count(*) from Message";
		Object uniqueResult = getCurrentSession().createQuery(hql2)
				.uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return list;
	}

}
