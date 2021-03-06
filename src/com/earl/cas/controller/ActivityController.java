package com.earl.cas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.earl.cas.commons.BaseController;
import com.earl.cas.entity.Activity;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ActivityService;
import com.earl.cas.util.FileUploadUtil;
import com.earl.cas.vo.PageInfo;
import com.earl.cas.vo.ResultMessage;

/**
 * Activity的controller.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@RestController
@RequestMapping(value = "/activity")
public class ActivityController extends BaseController {

	private final Logger logger = LoggerFactory
			.getLogger(ActivityController.class);

	@Autowired
	private ActivityService activityService;

	private ResultMessage result = null;

	/**
	 * 创建活动,标题和内容均不能为空
	 * 
	 * @param activity
	 * @author 祝
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> createActivity(Activity activity) {
		logger.debug("REST request to save activity");
		// 判断输入的活动标题或者活动内容是否为空
		if (StringUtils.isBlank(activity.getContent())
				| StringUtils.isBlank(activity.getTitle())) {// 判断活动标题和内容是否为空
			throw new DomainSecurityException("活动内容或活动标题均不能为空");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.save(activity);
		result.setResultInfo("创建成功");
		// 创建的活动，并将社团名字
		result.getResultParm().put("activity",
				activityService.findByClubId(activity.getId()));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 删除活动
	 * 
	 * @param id
	 * @author 祝
	 */
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deleteActivity(Integer id) {
		logger.debug("REST request to delete activity");
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.deleteById(id);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);

	}

	/**
	 * 修改活动，标题和内容均不能为空
	 * 
	 * @param activity
	 *            必须含有content、title和id
	 * @author 祝
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateActivity(Activity activity) {
		logger.debug("REST request to update activity");
		// 判断输入的活动标题或者活动内容是否为空
		if (StringUtils.isBlank(activity.getContent())
				| StringUtils.isBlank(activity.getTitle())) {// 判断活动标题和内容是否为空
			throw new DomainSecurityException("活动内容或活动标题均不能为空");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.updateActivity(activity);
		result.setResultInfo("更新成功");
		result.getResultParm().put("activity",
				activityService.findDetail(activity.getId()));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 查看所有活动
	 * 
	 * @author 祝
	 * @param pageInfo
	 *            必须含有 indexPageNum\size
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// 返回结果是jason类型
	public ResponseEntity<ResultMessage> getAllActivity(PageInfo pageInfo) {
		logger.debug("REST request to get all activity");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> activityList = activityService.findAllActivity(pageInfo);
		result.getResultParm().put("activity", activityList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 根据社团id查找社团活动
	 * 
	 * @param activity
	 *            必须含有clubId
	 * @param pageInfo
	 *            必须含有 indexPageNum\size
	 * @author 祝
	 */
	@RequestMapping(value = "/getClubActivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getClubActivity(Integer clubId,
			PageInfo pageInfo) {
		logger.debug("通过社团id查找社团活动");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> clubActivityList = activityService.findByClubId(clubId,
				pageInfo);
		result.getResultParm().put("activity", clubActivityList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 查看活动详情
	 * 
	 * @param id
	 * @author 祝
	 */
	@RequestMapping(value = "/getDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getDetail(int id) {
		logger.debug("REST request to get activityDetail");
		result = new ResultMessage();
		result.setServiceResult(true);
		Activity activityDetail = activityService.findDetail(id);
		result.getResultParm().put("activity", activityDetail);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 根据社团名字查找社团活动
	 * 
	 * @param clubName
	 * @param schoolName
	 * @param pageInfo
	 *            必须含有 indexPageNum\size
	 * @author 祝
	 */
	@RequestMapping(value = "/searchClubActivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> searchClubActivity(String clubName) {
		logger.debug("通过社团名字查找社团活动");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> searchList = activityService.findByClubName(clubName);
		result.getResultParm().put("activity", searchList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);

	}

	/**
	 * 模糊查询活动(根据不完整的主题查询）
	 * 
	 * @param input
	 *            * @param pageInfo 必须含有 indexPageNum\size
	 */
	@RequestMapping(value = "/searchByInput", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> searchByInput(String input,
			PageInfo pageInfo) {
		logger.debug("通过社团id查找社团活动");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> clubActivityList = activityService.findByInput(input,
				pageInfo);
		result.getResultParm().put("activity", clubActivityList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 活动上出啊图片.
	 */
	@RequestMapping(value = "/upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void update(MultipartFile upload, HttpServletRequest request,HttpServletResponse response) {
		String path = FileUploadUtil.NewFileUpload(request, upload,"activity");
		logger.info("上传图片访问地址："+ path);
		response.setContentType("text/html;charset=UTF-8");
		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		PrintWriter out;
		String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+"/ClubSystem/"+path+"');</script>";
		try {
			out = response.getWriter();
			out.print(s);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		result.setServiceResult(true);
//		result.setResultInfo("上传成功");
//		result.getResultParm().put("path", path);
//		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
}
