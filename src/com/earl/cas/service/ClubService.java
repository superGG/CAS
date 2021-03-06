package com.earl.cas.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Club;
import com.earl.cas.vo.PageInfo;

public interface ClubService extends BaseService<Club> {
	/**
	 * 获取某学校的某个社团
	 * 
	 * @param schoolName
	 * @param clubName
	 * @return
	 */
	List<Club> getByName(String clubName);

	/**
	 * 更新社团
	 * 
	 * @param club
	 * @return
	 */
	boolean update(Club club);

	/**
	 * 根据detailId查找社团
	 * 
	 * @param id
	 * @return
	 */
	Club getClubByuserDetailId(int detailId);
	/**
	 * 获取自己的社团信息
	 * @param detailId
	 * @return
	 */
	Club getMyClub(int detailId);
	/**
	 * 更新我的社团
	 * @param club
	 * @param file
	 * @param request
	 * @param typeName 
	 * @return 
	 */
	void updateMyclub(Club club, String typeName);
	/**
	 * 获取已经加入的社团信息
	 * @param clubId
	 * @return
	 */
	Club getMyJoinClub(int clubId);
	/**
	 * 获得自己所有的已加入社团
	 * @param detailId
	 * @return
	 */
	List<Club> getMyClubList(int detailId);
	/**
	 * 更新或者新建后返回club
	 * @param id
	 * @return
	 */
	Club findById(Integer id);
	/**
	 * 删除社团以及级联删除userclub  apply message activity position
	 * @param clubId
	 */
	void delete(int clubId);
	/**
	 * 判断用户是否创建过社团
	 * @param detailId
	 */
	boolean isCreated(Integer detailId);
	/**
	 * 创建社团
	 * @param detailId 
	 * @param club
	 * @param schoolName
	 * @param clubType
	 */
	void create(Integer detailId, Club club, String schoolName, String clubType);
	
	/**
	 * 根据学校名字获得社团
	 * @param name
	 * @param pageInfo
	 * @return
	 */
	List<Club> getBySchoolName(String name, PageInfo pageInfo);
	/**
	 * 根据类型获取社团
	 * @param typeName
	 * @param pageInfo
	 * @return
	 */
	List<Club> getByTypeName(String typeName, PageInfo pageInfo);
	/**
	 * 找到所有的club
	 * @param pageInfo 
	 * @return
	 */
	List<Club> getAlls(PageInfo pageInfo);
	/**
	 * get By id  封装好所有属性
	 * @param clubId
	 * @return
	 */
	Club getById(int clubId);
	/**
	 * 搜索获得社团
	 * @param serach
	 * @return
	 */
	List<Club> getBySearch(String serach,PageInfo pageInfo);
	/**
	 * 根据热度进行排序
	 * @param pageInfo
	 * @return
	 */
	List<Club> getAllsByRank(PageInfo pageInfo);
	/**
	 * 更新社徽
	 * @param file
	 * @param request
	 */
	void updateBadge(MultipartFile file, HttpServletRequest request,int clubId);
	
}