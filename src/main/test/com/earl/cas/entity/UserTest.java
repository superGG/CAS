package com.earl.cas.entity;

import org.junit.Test;

import com.earl.cas.dao.UserDao;
import com.earl.cas.dao.Impl.UserDaoImpl;

public class UserTest {

	@Test
	public void saveTest(){
		User user = new User();
		user.setId(10);
		user.setClassname("软件1133");
		user.setName("Kellan");
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
	}
	
	
}
