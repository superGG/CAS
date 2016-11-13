package com.earl.cas.commons;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.earl.cas.interceptor.StringEscapeEditor;

/**
 * @author song.
 * @date:2016-1-15 上午11:39:19
 * @version :
 */
public abstract class BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false, false));
	}

	/**
	 * 计算总页数.
	 * 
	 * @author 宋.
	 * @param totalRow
	 *            数据总数.
	 * @param pageSize
	 *            每页数据大小.
	 * @return
	 */
	protected int calcTotalPage(int totalRow, int pageSize) {
		int totalPage = 1;
		if (totalRow == 0)
			totalPage = 1;
		else if (totalRow % pageSize == 0) {
			totalPage = totalRow / pageSize;
		} else {
			totalPage = totalRow / pageSize + 1;
		}
		return totalPage;
	}
}
