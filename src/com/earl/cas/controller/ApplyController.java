package com.earl.cas.controller;

import java.util.List;

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
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ApplyService;
import com.earl.cas.service.ClubService;
import com.earl.cas.service.UserclubService;
import com.earl.cas.vo.PageInfo;
import com.earl.cas.vo.ResultMessage;
import com.earl.cas.vo.Member;
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
	private ClubService clubService;

	@Autowired
	private UserclubService userclubService;

	private ResultMessage result = null;
	
	
	/**
	 * POST->创建申请书
	 */
	@RequestMapping(value = "/createApply", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> createApply(int detailId,Apply apply,String clubName,String schoolName) {
		logger.debug("REST request to create a apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		apply.setDetailId(detailId);
		applyService.createApply(clubName,apply,schoolName);
		result.setResultInfo("申请书已提交，等待审核");
		result.getResultParm().put("apply", applyService.findById(apply.getId()));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
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
		result.getResultParm().put("apply", applyService.findById(apply.getId()));
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
	 * 查看自己社团的全部入社申请书->申请书管理列表
	 */
	@RequestMapping(value = "/displayClubApply", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayClubApply(int detailId) {
		logger.debug("REST request to display club apply");
		result = new ResultMessage();
		result.setServiceResult(true);

		// 获取用户创建的社团
		Club club = clubService.getClubByuserDetailId(detailId);

		// 通过clubId获得对应社团的申请书
		List<Apply> applylist = applyService.getClubApply(club.getId());
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 查看自己社团的已通过审核的入社申请书->申请书管理列表
	 */
	@RequestMapping(value = "/displayClubApplyIsOk", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayClubApplyStatueIsOk(int detailId) {
		logger.debug("REST request to display club apply which statue is OK");
		result = new ResultMessage();
		result.setServiceResult(true);

		// 获取用户创建的社团
		Club club = clubService.getClubByuserDetailId(detailId);

		// 通过clubId获得对应社团的已经通过的申请书
		List<Apply> applylist = applyService.getClubApplyIsOk(club.getId());
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 查看自己社团的已审核的入社申请书->申请书管理列表
	 */
	@RequestMapping(value = "/displayClubApplyHasExam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayClubApplyHasExam(int detailId) {
		logger.debug("REST request to display club apply which statue is 1 or 0");
		result = new ResultMessage();
		result.setServiceResult(true);

		// 获取用户创建的社团
		Club club = clubService.getClubByuserDetailId(detailId);

		// 通过clubId获得对应社团的已经审核的申请书
		List<Apply> applylist = applyService.getClubApplyHasExam(club.getId());
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 查看自己社团的未审核的入社申请书->申请书管理列表
	 */
	@RequestMapping(value = "/displayClubApplyNotExam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayClubApplyNotExam(int detailId) {
		logger.debug("REST request to display club apply which statue is 2");
		result = new ResultMessage();
		result.setServiceResult(true);
		// 获取用户创建的社团
		Club club = clubService.getClubByuserDetailId(detailId);
		// 通过clubId获得对应社团的未审核的申请书
		List<Apply> applylist = applyService.getClubApplyNotExam(club.getId());
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 申请书管理列表3合1
	 */
	@RequestMapping(value = "/displayAllClubApply", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayAllClubApply(int detailId,Integer statue) {
		logger.debug("REST request to display club apply which statue is ?");
		result = new ResultMessage();
		result.setServiceResult(true);
		// 获取用户创建的社团
		Club club = clubService.getClubByuserDetailId(detailId);
		if(club==null){
			throw new DomainSecurityException("该用户未创建社团");
		}
		// 通过clubId获得对应社团的未审核的申请书
		List<Apply> applylist = applyService.getClubApply(club.getId(),statue);
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 审核入社申请->statue由前端传入  0同意，1拒绝 
	 */
	@RequestMapping(value = "/isAgree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> isAgree(int applyId,int statue) {
		logger.debug("REST request to agree a apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		applyService.update(applyId,statue);
		if(statue==0){
			result.setResultInfo("该用户已加入社团");
		}
		else result.setResultInfo("拒绝该用户加入社团");		
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 拒绝申请->对非空属性进行更新
	 */
	@RequestMapping(value = "/notAgree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> notAgree(int applyId) {
		logger.debug("REST request to do not agree a apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		applyService.notAgree(applyId);
		result.setResultInfo("拒绝该成员加入社团");
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
		result.getResultParm().put("apply", applyService.findById(applyId));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	
	}
	/**
	 * 查看成员列表
	 */
	@RequestMapping(value = "/displayMember", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayMember(int detailId) {
		logger.debug("REST request to display club member");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Member> memberlist = applyService.getMember(detailId);
		result.getResultParm().put("memberlist", memberlist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 查看成员列表->分页查询
	 */
	@RequestMapping(value = "/displayPageMember", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayPageMember(int detailId,PageInfo pageInfo) {
		logger.debug("REST request to display club member");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Member> memberlist = applyService.getMember(detailId,pageInfo);
		result.getResultParm().put("memberlist", memberlist);
		result.getResultParm().put("totalCount",pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 查看成员详情
	 */
	@RequestMapping(value = "/memberDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> memberDetail(int applyId) {
		logger.debug("REST request to display club member detail");
		result = new ResultMessage();
		result.setServiceResult(true);
		Apply apply = applyService.getMemberDetail(applyId);
		result.getResultParm().put("apply", apply);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 搜索成员
	 */
	@RequestMapping(value = "/searchMember", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> searchMember(int detailId,String name) {
		logger.debug("REST request to search club member");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Member> memberlist = applyService.searchMember(detailId,name);
		result.getResultParm().put("apply", memberlist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 根据detailId和clubId获取社团申请书，根据时间排序，传回statue 
	 * @param detailId  写申请书的用户的ID
	 * @param clubId  社团Id
	 */
	@RequestMapping(value = "/getStatue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getStatue(int detailId,int clubId) {
		logger.debug("REST request to get statue");
		result = new ResultMessage();
		result.setServiceResult(true);
		Apply apply = applyService.getByDetailIdAndClubId(detailId,clubId);
		int statue = apply.getStatue();
		result.getResultParm().put("statue（0通过，1拒绝，2未审核）", statue);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
}
