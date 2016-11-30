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
import com.earl.cas.entity.Apply;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.service.ApplyService;
import com.earl.cas.service.ClubService;
import com.earl.cas.service.UserDetailsService;
import com.earl.cas.service.UserclubService;
import com.earl.cas.vo.ResultMessage;

/**
 * Apply的controller.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@RestController
@RequestMapping(value = "/apply")
public class ApplyController extends BaseController {

	private final Logger logger = LoggerFactory
			.getLogger(ApplyController.class);

	@Autowired
	private ApplyService applyService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ClubService clubService;

	@Autowired
	private UserclubService userclubService;

	private ResultMessage result = null;

	/**
	 * GET /apply -> get all the apply
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Apply> applyList = applyService.findAll();
		result.getResultParm().put("apply", applyList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 同意申请并添加成员
	 */
	@RequestMapping(value = "/agree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> agree(Apply apply) {
		logger.debug("REST request to agree a apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		applyService.update(apply);
		result.setResultInfo("该成员已加入社团");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 查看申请书
	 */
	@RequestMapping(value = "/displayApply", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayApply() {
		logger.debug("REST request to display all apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Apply> applylist = applyService.findAll();
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 查看自己社团的入社申请书->申请书管理列表
	 */
	@RequestMapping(value = "/displayClubApply", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayClubApply(int userId) {
		logger.debug("REST request to display club apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		// 获取用户详情，前端传进来userId或者session获取
		UserDetails userDetails = userDetailsService.getByUserId(userId);

		// 获取用户创建的社团
		Club club = clubService.getClubByuserDetailId(userDetails.getId());

		// 通过clubId获得对应社团的申请书
		List<Apply> applylist = applyService.getClubApply(club.getId());
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 同意申请并添加成员->对非空属性进行更新
	 */
	@RequestMapping(value = "/isAgree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> isAgree(int applyId,int statue) {
		logger.debug("REST request to agree a apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		applyService.update(applyId,statue);
		result.setResultInfo("该成员已加入社团");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 剔除成员->根据申请书Id
	 */
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deleteMember(int applyId) {
		logger.debug("REST request to delete a member");
		result = new ResultMessage();
		result.setServiceResult(true);
		userclubService.deleteByapplyId(applyId);
		result.setResultInfo("该成员已从社团中剔除");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 修改成员职位
	 * 逻辑关系
	 * session中有userDetailId ->clubId->list<position>
	 * 传递参数是一个positionName->list<position>比较获得position->positionID
	 * 要更新userclub必须要有userclubId或者是applyId，因此传递参数有一个是applyId
	 */
	@RequestMapping(value = "/updateposition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updatePosition(int detailId,int applyId,String positionName) {
		logger.debug("REST request to update a menberPosition");
		result = new ResultMessage();
		result.setServiceResult(true);
		userclubService.updatePosition(detailId,applyId,positionName);
		result.setResultInfo("职位更新成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	
	}
}
