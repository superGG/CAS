package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.PhotoDao;
import com.earl.cas.entity.Photo;

/**
 * photoDao的实现类
 *@author 宋
 *@date 2016-8-15
 */
@Repository("photoDao")
public class PhotoDaoImpl extends BaseDaoImpl<Photo> implements PhotoDao {


}