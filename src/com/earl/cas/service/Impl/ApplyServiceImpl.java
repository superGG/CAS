package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.dao.UserclubDao;
import com.earl.cas.entity.Apply;
import com.earl.cas.entity.Userclub;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ApplyService;

@Service("applyService")
@Transactional
public class ApplyServiceImpl extends BaseServiceImpl<Apply> implements
		ApplyService {

	private static Logger logger = LoggerFactory
			.getLogger(ApplyServiceImpl.class);

	@Resource
	private ApplyDao applyDao;

	@Resource
	private UserclubDao userclubDao;
	
	@Override
	protected BaseDao<Apply> getDao() {
		return applyDao;
	}

	public void update(Apply apply){
		if(apply.getStatue()!=1){		//判断申请表状态
			apply.setStatue(1);
			if(!applyDao.update(apply)){
				throw new DomainSecurityException("更新失败");
			}else{
				//同意后创建新的用户社团关联对象，职位默认为0
				Userclub uc=new Userclub();
				uc.setApplyId(apply.getId());
				uc.setClubId(apply.getClubId());
				uc.setPositionId(0);
				//进行保存
				if(userclubDao.save(uc)<1){
					throw new DomainSecurityException("保存userclub失败");
				}
			}
		}else{
			throw new DomainSecurityException("该成员已在社团");
		}
	}
	

}
