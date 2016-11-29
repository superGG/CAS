package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Apply;


public interface ApplyService extends BaseService<Apply> {
	
	/**
	 * 同意申请->添加成员
	 * @param apply
	 */
	public void update(Apply apply);


}