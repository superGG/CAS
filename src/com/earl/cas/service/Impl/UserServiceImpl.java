package com.earl.cas.service.Impl;

import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.UserDao;
import com.earl.cas.entity.User;
import com.earl.cas.service.UserService;
import com.earl.cas.util.SmsbaoHelper;

/**
 * User的service层实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	private static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Resource
	private UserDao userDao;

	@Override
	protected BaseDao<User> getDao() {
		return userDao;
	}

	@Override
	public User findByAccount(String account) {
		logger.info("进入UserService层的findByAccount方法");
		return userDao.findByAccount(account);
	}

	@Override
	public String getSmsCode(String phone) {
		logger.info("进入UserService层的getSmsCode方法");
		int result = -1;
		// 生成6位数验证码
		Random random = new Random();
		Integer code = random.nextInt(899999) + 100000;
		// 生成指定短信
		String verifyCode = Integer.toString(code);
		String mf = "【社团网申系统】您的验证码是" + verifyCode + ",60秒有效";
		logger.info(mf);
		try {
			// 结果返回0时，短信发送成功
			result = SmsbaoHelper.send(phone,mf);
		} catch (Exception e) {
			logger.info("系统出错，短信发送失败");
			e.printStackTrace();
		} 
		return result==0?verifyCode:null;
	}

}
