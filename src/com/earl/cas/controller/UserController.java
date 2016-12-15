package com.earl.cas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.earl.cas.commons.BaseController;
import com.earl.cas.entity.User;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.UserDetailsService;
import com.earl.cas.service.UserService;
import com.earl.cas.util.ImgVerifyCodeUtil;
import com.earl.cas.util.MD5Util;
import com.earl.cas.vo.ResultMessage;
import com.mysql.jdbc.StringUtils;

/**
 * user的controller.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	private ResultMessage result = null;

	/**
	 * GET /users -> get all the users
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all Users");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<User> userList = userService.findAll();
		result.getResultParm().put("user", userList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * POST /users -> save user
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(User user) {
		logger.debug("REST request to save Users");
		result = new ResultMessage();
		result.setServiceResult(true);
		Integer userId = userService.save(user);
		UserDetails userDetail = new UserDetails();
		userDetail.setName("test2");
		userDetail.setEmail("wergsdf@qq.com");
		userDetail.setUserId(userId);
		userDetailsService.save(userDetail);
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(Integer id) {
		logger.debug("REST request to delete Users");
		result = new ResultMessage();
		result.setServiceResult(true);
		userService.deleteById(id);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 用户注册.
	 * 
	 * @author 宋.
	 * @param account
	 * @param password
	 * @param verifyCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> register(User user, String verifyCode,
			HttpServletRequest request) {
		result = new ResultMessage();
		if (StringUtils.isNullOrEmpty(user.getAccount())
				| StringUtils.isNullOrEmpty(user.getPassword())
				| StringUtils.isNullOrEmpty(verifyCode)) {
			throw new DomainSecurityException("数据有误");
		}
		if (!checkVerifyCode(request, verifyCode)) {
			throw new DomainSecurityException("验证码错误");
		}
		if (userService.findByAccount(user.getAccount()) != null) {
			throw new DomainSecurityException("该用户已存在");
		}
		UserDetails userDetail = userService.register(user);
		if (userDetail.getId() != 0) {
			result.setServiceResult(true);
			result.setResultInfo("注册成功");
			result.getResultParm().put("userDetail", userDetail);
		} else {
			result.setServiceResult(false);
			result.setResultInfo("注册失败");
		}
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 用户登录.
	 * 
	 * @author 宋.
	 * @param request
	 * @param account
	 * @param password
	 * @param VerifyCode
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> login(HttpServletRequest request,
			String account, String password, String verifyCode, Integer role) {
		result = new ResultMessage();
		if (!checkVerifyCode(request, verifyCode)) {
			throw new DomainSecurityException("验证码错误");
		}
		User user = userService.findByAccount(account);
		if (user == null) {
			throw new DomainSecurityException("没有该用户");
		}
		String password_md5 = MD5Util.md5(password);
		if (!password_md5.equals(user.getPassword())) {
			throw new DomainSecurityException("密码错误");
		}
		UserDetails userDetail = userDetailsService.getByUserId(user.getId());
		if (role != null) {
			if (userDetail.getRoleId() != 0) {
				throw new DomainSecurityException("该用户不是管理员");
			}
		}
		// 将用户详情存放在session中.
		request.getSession().setAttribute("userDetail", userDetail);
		result.setResultInfo("登录成功");
		result.getResultParm().put("userDetail", userDetail);
		request.getSession().setAttribute("userDetailId", userDetail.getId());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 管理员登录.
	 * 
	 * @author 宋.
	 * @param request
	 * @param account
	 * @param password
	 * @param VerifyCode
	 * @return
	 */
	@RequestMapping(value = "/mlogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> mlogin(HttpServletRequest request,
			String account, String password,  Integer role) {
		result = new ResultMessage();
		User user = userService.findByAccount(account);
		if (user == null) {
			throw new DomainSecurityException("没有该用户");
		}
		String password_md5 = MD5Util.md5(password);
		if (!password_md5.equals(user.getPassword())) {
			throw new DomainSecurityException("密码错误");
		}
		UserDetails userDetail = userDetailsService.getByUserId(user.getId());
		if (role != null) {
			if (userDetail.getRoleId() != 0) {
				throw new DomainSecurityException("该用户不是管理员");
			}
		}
		// 将用户详情存放在session中.
		request.getSession().setAttribute("userDetail", userDetail);
		result.setResultInfo("登录成功");
		result.getResultParm().put("userDetail", userDetail);
		request.getSession().setAttribute("userDetailId", userDetail.getId());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 更改密码.
	 * 
	 * @author 宋.
	 * @param detailId
	 *            用户详情id
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updatePassword(Integer detailId,
			String oldPassword, String newPassword) {
		result = new ResultMessage();
		UserDetails userDetail = userDetailsService.get(detailId);
		User user = userService.get(userDetail.getUserId());
		if (!user.getPassword().equals(MD5Util.md5(oldPassword))) {
			throw new DomainSecurityException("旧密码错误");
		}
		user.setPassword(MD5Util.md5(newPassword));
		userService.updateWithNotNullProperties(user);
		result.setServiceResult(true);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 找回密码.
	 * 
	 * @author 宋.
	 * @param verifyCode
	 * @param account
	 * @param newPassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> findPassword(String verifyCode,
			String account, String newPassword, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(newPassword)
				| StringUtils.isNullOrEmpty(verifyCode)) {
			throw new DomainSecurityException("数据有误");
		}
		if (!checkVerifyCode(request, verifyCode)) {
			throw new DomainSecurityException("验证码错误");
		}
		User user = userService.findByAccount(account);
		if (user == null) {
			throw new DomainSecurityException("无该用户");
		}
		user.setPassword(MD5Util.md5(newPassword));
		userService.updateWithNotNullProperties(user);
		result = new ResultMessage();
		result.setResultInfo("更新成功");
		result.setServiceResult(true);
		result.getResultParm().put("userDtail",
				userDetailsService.getByUserId(user.getId()));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 获取图片验证码.
	 * 
	 * @author 宋.
	 * @return
	 */
	@RequestMapping(value = "/getImgVerifyCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getQRCode(HttpServletRequest request) {
		result = new ResultMessage();
		ImgVerifyCodeUtil v = new ImgVerifyCodeUtil();
		String path = v.getVerifyCode(request);
		request.getSession().setAttribute("VerifyCode", v.getText());
		System.out.println("图片验证码:" + v.getText());
		result.setServiceResult(true);
		result.setResultInfo("生成图片验证码成功");
		result.getResultParm().put("path", path);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 获取短信验证码.
	 * 
	 * @author 宋.
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSmsCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getSmsCode(String phone,
			HttpServletRequest request) {
		result = new ResultMessage();
		String verifyCode = userService.getSmsCode(phone);
		if (StringUtils.isNullOrEmpty(verifyCode)) {
			throw new DomainSecurityException("获取验证码失败");
		}
		result.setResultInfo("获取验证码成功");
		result.setServiceResult(true);
		request.getSession().setAttribute("VerifyCode", verifyCode);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 验证验证码.
	 * 
	 * @author 宋.
	 * @param request
	 * @param VerifyCode
	 * @return
	 */
	private Boolean checkVerifyCode(HttpServletRequest request,
			String verifyCode) {
		logger.info("verifyCode:" + verifyCode);
		String VerifyCode = (String) request.getSession().getAttribute(
				"VerifyCode");
		logger.info("VerifyCode:" + VerifyCode);
		return VerifyCode.equalsIgnoreCase(verifyCode);
	}

}
