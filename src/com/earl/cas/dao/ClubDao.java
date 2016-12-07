package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Club;
import com.earl.cas.vo.PageInfo;

public interface ClubDao extends BaseDao<Club> {
	/**
	 * 通过名字找到该社团
	 * 
	 * @param name
	 * @param clubName
	 */
	List<Club> getByName(String clubName);

	/**
	 * 更新社团
	 * 
	 * @param club
	 * @return
	 */
	public boolean update(Club club);

	/**
	 * 根据userDetailId找到社团
	 * 
	 * @param id
	 * @return
	 */
	Club getClubByuserDetailId(int id);

	/**
	 * 通过学校和名字精确找到社团
	 */
	Club getByNameAndSchool(String Name, int schoolId);

	/**
	 * 分页查询所有社团
	 */
	@Override
	List<Club> findAll(PageInfo pageInfo);
	/**
	 * 模糊查询根据名字获取社团
	 * @param search
	 * @return
	 */
	List<Club> getBySearch(String search,PageInfo pageInfo);
	/**
	 * 通过学校找到社团
	 * @param search
	 * @param pageInfo 
	 * @return
	 */
	List<Club> getBySchoolId(int schoolId, PageInfo pageInfo);
	/**
	 * 通过模糊查询和学校ID获得社团
	 * @param search
	 * @param id
	 * @return
	 */
	Club getBySearchAndSchool(String search, Integer id);
	/**
	 * 根据热度进行排序
	 * @param pageInfo
	 */
	List<Club> getByRank(PageInfo pageInfo);


	
}