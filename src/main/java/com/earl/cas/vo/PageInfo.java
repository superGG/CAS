package com.earl.cas.vo;

/**
 * 分页信息值对象类.
 */
public class PageInfo {
	/**
	 * 单页条目数.
	 */
	private Integer size;

	/**
	 * 当前页码.
	 */
	private Integer indexPageNum;

	/**
	 * 总条目数.
	 */
	private Long totalCount;

	/**
	 * @return 获取的size
	 */
	public final Integer getSize() {
		return size;
	}

	/**
	 * 设置size的方法.
	 * 
	 * @param size
	 *            赋值给size的值
	 */
	public final void setSize(final Integer size) {
		this.size = size;
	}

	/**
	 * @return 获取的indexPageNum
	 */
	public final Integer getIndexPageNum() {
		return indexPageNum;
	}

	/**
	 * 设置indexPageNum的方法.
	 * 
	 * @param indexPageNum
	 *            赋值给indexPageNum的值
	 */
	public final void setIndexPageNum(final Integer indexPageNum) {
		this.indexPageNum = indexPageNum;
	}

	/**
	 * @return 获取的totalCount
	 */
	public final Long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置totalCount的方法.
	 * 
	 * @param totalCount
	 *            赋值给totalCount的值
	 */
	public final void setTotalCount(final Long totalCount) {
		this.totalCount = totalCount;
	}
}
