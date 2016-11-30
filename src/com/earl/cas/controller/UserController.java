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
			String account, String password, String verifyCode) {
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
		if (userDetail == null) {
			throw new DomainSecurityException("没有该用户详情");
		}
		//将用户详情存放在session中.
		request.getSession().setAttribute("userDetail", userDetail);
		result.setResultInfo("登录成功");
		result.getResultParm().put("userDetail", userDetail);
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

	//
	// /**
	// * POST /change -> changes the current user's password
	// */
	// // @ApiOperation(value = "更改用户密码", notes = "CHANGE USER PASSWORD",
	// httpMethod = "POST", response = String.class)
	// @RequestMapping(value = "/changePassword", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<ResultMessage> changePassword(
	// // @ApiParam(required = true, name = "oldPassword", value = "旧密码")
	// @NotEmpty(message = "旧密码不能为空") String oldPassword,
	// // @ApiParam(required = true, name = "id", value = "用户id")
	// @NotEmpty(message = "用户id不能为空") Long id,
	// // @ApiParam(required = true, name = "newPassword", value = "新密码")
	// @NotEmpty(message = "新密码不能为空") @Length(min = 5, max = 32) String
	// newPassword) {
	// if (StringUtils.isBlank(oldPassword) | StringUtils.isBlank(newPassword))
	// throw new DomainSecurityException("数据有误");
	// result = new ResultMessage();
	// result.setServiceResult(userService.changePassword(id, oldPassword,
	// newPassword));
	// return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	// }
	//
	// /**
	// * 用户登录
	// *
	// * @param loginid
	// * @param password
	// * @return
	// */
	// @RequestMapping(value = "login", produces =
	// MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	// @ApiOperation(value = "登录系统", notes = "loginSystem", httpMethod = "POST",
	// response = String.class)
	// public ResponseEntity<ResultMessage> doLogin(
	// @ApiParam(required = true, name = "loginid", value = "用户账号")
	// @NotEmpty(message = "登录账号不能为空") String account,
	// @ApiParam(required = true, value = "用户登录密码") @NotEmpty(message =
	// "密码不能为空") String password) {
	// result = new ResultMessage();
	//
	// User user = userService.findOneByAccount(account);
	//
	// if (user != null) {
	// String mD5_passwordString = MD5Util.md5(password);
	// if (user.getPassword().equals(mD5_passwordString)) {
	// String resultInfo = null;
	// switch (user.getRole()) {
	// case 1:
	// resultInfo = "manager/main";
	// break;
	// case 2:
	// resultInfo = "team/main";
	// break;
	// case 3:
	// resultInfo = "teacher/main";
	// break;
	// default:
	// resultInfo = "academy/main";
	// break;
	// }
	// result.getResultParm().put("user", user);
	// result.setResultInfo(resultInfo);
	// return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	// } else {
	// throw new DomainSecurityException("密码错误");
	// }
	// } else {
	// throw new DomainSecurityException("该账号不存在");
	// }
	//
	// }
	//
	// @ApiOperation(value = "用户注册", notes = "user register system account ",
	// httpMethod = "POST", response = String.class)
	// @RequestMapping(value = "/register", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<ResultMessage> regitsterAccount(User user, Model
	// model) {
	// log.info("进入controller层create方法");
	// // 校验数据
	// if (StringUtils.isBlank(user.getAccount())
	// | StringUtils.isBlank(user.getPassword())
	// | StringUtils.isBlank(user.getRole().toString())
	// | StringUtils.isBlank(user.getRole_id().toString())) {
	// throw new DomainSecurityException("数据有误");
	// }
	// // 校验账号是否存在
	// if (userService.findOneByAccount(user.getAccount()) != null) {
	// throw new DomainSecurityException("该账号已存在");
	// }
	//
	// // String MD5_password = MD5Util.md5(user.getPassword());
	// // user.setPassword(MD5_password);
	// User new_user = userService.registerAccount(user);
	//
	// if (new_user != null) {
	// model.addAttribute("user", new_user);
	// result = new ResultMessage();
	// result.setResultInfo("创建账号成功");
	// result.getResultParm().put("user", new_user);
	// return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	// } else {
	// throw new DomainSecurityException("创建账号失败");
	// }
	// }

}
