package com.earl.cas.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.MessageDao;
import com.earl.cas.entity.Message;

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

}
