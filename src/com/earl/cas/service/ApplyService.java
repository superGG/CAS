package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Apply;


public interface ApplyService extends BaseService<Apply> {
	
	/**
	 * 同意申请->添加成员
	 * @param apply
	 */
	public void update(Apply apply);
	/**
	 * 通过创建人ID查找属于自己社团的申请书
	 * @param id
	 * @return
	 */
	public List<Apply> getClubApply(int id);

}