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
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ClubService;
import com.earl.cas.vo.PageInfo;
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
	public  ResponseEntity<ResultMessage> getAll(PageInfo pageInfo) {
		logger.debug("REST request to get all club");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clubList = clubService.getAlls(pageInfo);
		result.getResultParm().put("club", clubList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * GET /club -> get a the club by id
	 */
	@RequestMapping(value = "/getById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getById(int clubId) {
		logger.debug("REST request to get all club");
		result = new ResultMessage();
		result.setServiceResult(true);
		Club club = clubService.getById(clubId);
		result.getResultParm().put("club", club);
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
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clublist = clubService.getByName(name);
		result.getResultParm().put("club", clublist);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}

	/**
	 * 注销社团
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> delete(int clubId) {
		logger.debug("REST request to delete a club");
		result = new ResultMessage();
		result.setServiceResult(true);
		clubService.delete(clubId);
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
	 * 获取自己已加入的社团列表
	 */
	@RequestMapping(value = "/getMyClubList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getMyClubList(int detailId) {
		logger.debug("REST request to get myclubList");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clublist = clubService.getMyClubList(detailId);
		result.getResultParm().put("club", clublist);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * 查看自己已经加入的社团信息
	 */
	@RequestMapping(value = "/getMyJoinClub", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getMyJoinClub(int clubId) {
		logger.debug("REST request to get myclub");
		result = new ResultMessage();
		result.setServiceResult(true);
		Club club = clubService.getMyJoinClub(clubId);
		result.getResultParm().put("club", club);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 更新社团信息
	 */
	@RequestMapping(value = "/updateMyClub",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Club club, String typeName) {
		logger.debug("REST request to update myclub");
		result = new ResultMessage();
		clubService.updateMyclub(club,typeName);
		result.setServiceResult(true);
		result.setResultInfo("更新成功");
		Club club2 = clubService.findById(club.getId());
//		Long number = userclubDao.getNumberByclubId(club2.getId());  //TODO  获取社团人数
//		club2.setNumber(number);
		result.getResultParm().put("club", club2);
		
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 更新社徽
	 */
	@RequestMapping(value = "/updateBadge",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateBadge(MultipartFile file, HttpServletRequest request,int clubId) {
		logger.debug("REST request to update myclub");
		result = new ResultMessage();
		clubService.updateBadge(file,request,clubId);
		result.setServiceResult(true);
		result.setResultInfo("更新成功");
		Club club2 = clubService.findById(clubId);
//		Long number = userclubDao.getNumberByclubId(club2.getId());//TODO  获取社团人数
//		club2.setNumber(number);
		result.getResultParm().put("club", club2);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * 判断是否已创建社团
	 */
	@RequestMapping(value = "/isCreated",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> isCreated(Integer detailId) {
		logger.debug("REST request to 判断用户是否创建过社团 ");
		result = new ResultMessage();
		boolean flag = clubService.isCreated(detailId);
		result.setServiceResult(true);
		result.setResultInfo("true->已创建过社团，false->没创建社团");
		result.getResultParm().put("flag",flag);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * 创建社团
	 */
	@RequestMapping(value = "/create",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> create(Integer detailId,String clubType,String schoolName,Club club) {
		logger.debug("REST request to 创建社团");
		result = new ResultMessage();
		if(clubService.isCreated(detailId)){
			throw new DomainSecurityException("用户已创建过社团");
		}
		clubService.create(detailId,club,schoolName,clubType);
		result.setServiceResult(true);
		result.setResultInfo("社团创建成功");
		result.getResultParm().put("club", clubService.findById(club.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	
	/**
	 * 根据学校找到社团
	 */
	@RequestMapping(value = "/getBySchoolName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getBySchoolName(String schoolName,PageInfo pageInfo) {
		logger.debug("REST request to get a club by schoolname");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clublist = clubService.getBySchoolName(schoolName,pageInfo);
		result.getResultParm().put("totalCount",pageInfo.getTotalCount());
		result.getResultParm().put("clublist", clublist);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 根据类型找到社团
	 */
	@RequestMapping(value = "/getByTypeName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getBytypeName(String typeName,PageInfo pageInfo) {
		logger.debug("REST request to get a club by schoolname");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clublist = clubService.getByTypeName(typeName,pageInfo);
		result.getResultParm().put("totalCount",pageInfo.getTotalCount());
		result.getResultParm().put("clublist", clublist);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 通过名字获取社团->模糊搜索
	 * @param clubName
	 * @return
	 */
	@RequestMapping(value = "/getBySearch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getBySearch(String search,PageInfo pageInfo) {
		logger.debug("REST request to get a club by name");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clublist = clubService.getBySearch(search,pageInfo);
		result.getResultParm().put("totalCount",pageInfo.getTotalCount());
		result.getResultParm().put("clublist", clublist);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 获取所有->根据热度排行
	 */
	@RequestMapping(value = "/getAllsByRank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAllsByRank(PageInfo pageInfo) {
		logger.debug("REST request to get all club By Rank");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clubList = clubService.getAllsByRank(pageInfo);
		result.getResultParm().put("club", clubList);
		result.getResultParm().put("totalCount",pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
}
