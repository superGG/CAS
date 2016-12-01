/**
 * 
 */
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
import org.springframework.web.multipart.MultipartFile;

import com.earl.cas.commons.BaseController;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ClubService;
import com.earl.cas.util.FileUploadUtil;
import com.earl.cas.vo.ResultMessage;

/**
 * 新的Club的Controller
 * @author Mr.Chen
 *
 */

@RestController
@RequestMapping(value = "/club")
public class ClubController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(ClubController.class);
	@Autowired
	private ClubService clubService;

	private ResultMessage result = null;
	
	/**
	 * GET /club -> get all the club
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all club");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clubList = clubService.findAll();
		result.getResultParm().put("club", clubList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * 保存Club
	 * @param club
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(Club club){
		logger.debug("REST request to save a club");
		result = new ResultMessage();
		result.setServiceResult(true);
		clubService.save(club);
		result.setResultInfo("保存成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	} 
	/**
	 * 通过名字获取社团
	 * @param clubName
	 * @return
	 */
	@RequestMapping(value = "/getByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getByName(String name) {
		logger.debug("REST request to get a club by name");
		//String clubName = null;
		//try {
		//	clubName = new String (name.getBytes("iso8859-1"),"utf-8");
		//} catch (UnsupportedEncodingException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clubList = clubService.getByName(name);
		result.getResultParm().put("club", clubList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * 注销社团
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> delete(Club club) {
		logger.debug("REST request to delete a club");
		result = new ResultMessage();
		result.setServiceResult(true);
		clubService.deleteById(club.getId());
		result.setResultInfo("注销成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * 更新社团信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Club club){
		logger.debug("REST request to update a club");
		result = new ResultMessage();
		result.setServiceResult(true);
		clubService.update(club);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 查看自己社团信息
	 */
	@RequestMapping(value = "/getMyClub", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getMyClub(int detailId) {
		logger.debug("REST request to get myclub");
		result = new ResultMessage();
		result.setServiceResult(true);
		Club club = clubService.getMyClub(detailId);
		result.getResultParm().put("club", club);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * 更新社团信息
	 */
	@RequestMapping(value = "/updateMyClub",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Club club, MultipartFile file, HttpServletRequest request,String typeName) {
		logger.debug("REST request to update myclub");
		result = new ResultMessage();
		clubService.updateMyclub(club,file,request,typeName);
		result.setServiceResult(true);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
}
