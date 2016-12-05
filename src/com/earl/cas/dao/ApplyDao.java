package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Apply;

public interface ApplyDao extends BaseDao<Apply> {
	/**
	 * 对申请表进行更新
	 * 
	 * @return
	 */
	boolean update(Apply apply);

	/**
	 * 通过clubId查找申请书
	 * 
	 * @param id
	 * @return
	 */
	List<Apply> getApplyByClubId(int id);
	/**
	 * 找到已经通过审核的申请书
	 * @param id
	 * @return
	 */
	List<Apply> getApplyByClubIdStatusIsOk(int id);
	/**
	 * 通过社团ID找到该社团statue不等于2的入社申请书
	 * @param id
	 */
	List<Apply> getApplyByClubIdStatueNotTwo(int id);
	/**
	 * 通过社团ID找到该社团statue等于2的入社申请
	 * @param id
	 * @return
	 */
	List<Apply> getApplyByClubIdStatueIsTwo(int id);
	/**
	 * 分页查询status为0（通过审核）的apply
	 * @param clubId
	 * @param pageIndex
	 * @return
	 */
	List<Apply> getPageApplyByClubIdStatusIsOk(int clubId, int pageIndex);
	/**
	 * 根据名字搜索成员
	 * @param id
	 * @param name
	 */
	List<Apply> getByName(Integer id, String name);
	/**
	 * 获得个人的已经通过的申请书
	 * @param detailId
	 */
	List<Apply> getBydetailIdStatueIsOk(int detailId);
	/**
	 * 根据社团Id删除申请书
	 * @param clubId
	 */
	void deleteByClubId(int clubId);
	/**
	 * 根据detailId和clubId获得申请书
	 * @param detailId
	 * @param clubId
	 */
	Apply getByDetailIdAndClubId(int detailId, int clubId);

}