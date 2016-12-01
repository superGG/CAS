package com.earl.cas.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.dao.UserclubDao;
import com.earl.cas.entity.Apply;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.Position;
import com.earl.cas.entity.Userclub;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ApplyService;
import com.earl.cas.vo.Member;

@Service("applyService")
public class ApplyServiceImpl extends BaseServiceImpl<Apply> implements
		ApplyService {

	private static Logger logger = LoggerFactory
			.getLogger(ApplyServiceImpl.class);

	@Resource
	private ApplyDao applyDao;

	@Resource
	private UserclubDao userclubDao;

	@Resource
	private ClubDao clubDao;

	@Resource
	private PositionDao positionDao;

	@Override
	protected BaseDao<Apply> getDao() {
		return applyDao;
	}

	public void update(Apply apply) {
		if (apply.getStatue() != 1) { // 判断申请表状态
			apply.setStatue(1);
			if (!applyDao.update(apply)) {
				throw new DomainSecurityException("更新失败");
			} else {
				// 同意后创建新的用户社团关联对象，职位默认为0
				Userclub uc = new Userclub();
				uc.setApplyId(apply.getId());
				uc.setClubId(apply.getClubId());
				uc.setPositionId(0);
				// 进行保存
				if (userclubDao.save(uc) < 1) {
					throw new DomainSecurityException("保存userclub失败");
				}
			}
		} else {
			throw new DomainSecurityException("该成员已在社团");
		}
	}

	public List<Apply> getClubApply(int id) {
		List<Apply> applyList = applyDao.getApplyByClubId(id);
		if (applyList != null) {
			return applyList;
		} else {
			throw new DomainSecurityException("没有找到申请书");
		}
	}

	public void update(int id, int statue) {
		Apply apply = new Apply();
		apply.setId(id);
		apply.setStatue(statue);
		applyDao.updateWithNotNullProperties(apply);
	}

	public List<Member> getMember(int detaliId) {
		// 一些容器变量
		List<Member> memberlist = new ArrayList<Member>();
		int clubId;
		int i = 1; // 计数标记
		String name;
		Userclub userclub;
		Position position;
		// session中有userDetailId ->clubId
		Club club = clubDao.getClubByuserDetailId(detaliId);
		if (club != null) {
			clubId = club.getId();
		} else {
			throw new DomainSecurityException("用户没有创建社团");
		}
		// 获得该社团申请书列表
		List<Apply> applylist = applyDao.getApplyByClubIdStatusIsOk(clubId);
		// 遍历将信息放进vo类
		for (Apply apply : applylist) {
			name = apply.getName(); // 从申请书中获得成员名字
			Member member = new Member();
			member.setId(i); // 编号
			member.setName(name);
			userclub = userclubDao.getUserclubByApplyId(apply.getId()); // 获得成员社团关联表
			userclub.getPositionId();
			member.setCreateTime(userclub.getCreatetime()); // userclub上的加入时间
			position = positionDao.get(userclub.getPositionId()); // 根据具体职位Id获得职位
																	// 提取出职位名字
			member.setPosition(position.getName());
			member.setTel(apply.getPhone());
			member.setMajorClass(apply.getMajorClass());
			member.setApplyId(apply.getId());
			i++; // 计数器加1
			if (member != null) {
				memberlist.add(member);
			}
		}
		if (memberlist != null) {
			return memberlist;
		} else {
			throw new DomainSecurityException("该社团没有成员");
		}
	}

	public List<Member> getMember(int detailId, int pageIndex) {
		// 一些容器变量
		List<Member> memberlist = new ArrayList<Member>();
		int clubId;
		int i = 1; // 计数标记
		String name;
		Userclub userclub;
		Position position;
		// session中有userDetailId ->clubId
		Club club = clubDao.getClubByuserDetailId(detailId);
		if (club != null) {
			clubId = club.getId();
		} else {
			throw new DomainSecurityException("用户没有创建社团");
		}
		// 获得该社团申请书列表
		List<Apply> applylist = applyDao.getPageApplyByClubIdStatusIsOk(clubId,pageIndex);
		// 遍历将信息放进vo类
		for (Apply apply : applylist) {
			name = apply.getName(); // 从申请书中获得成员名字
			Member member = new Member();
			member.setId(i); // 编号
			member.setName(name);
			userclub = userclubDao.getUserclubByApplyId(apply.getId()); // 获得成员社团关联表
			userclub.getPositionId();
			member.setCreateTime(userclub.getCreatetime()); // userclub上的加入时间
			position = positionDao.get(userclub.getPositionId()); // 根据具体职位Id获得职位
																	// 提取出职位名字
			member.setPosition(position.getName());
			member.setTel(apply.getPhone());
			member.setMajorClass(apply.getMajorClass());
			member.setApplyId(apply.getId());
			i++; // 计数器加1
			if (member != null) {
				memberlist.add(member);
			}
		}
		if (memberlist != null) {
			return memberlist;
		} else {
			throw new DomainSecurityException("该社团没有成员");
		}
	}

	public Apply getMemberDetail(int applyId) {
		List<String> positionName = new ArrayList<String>();
		Apply apply = applyDao.get(applyId);
		List<Position> positionlist = positionDao.findByClubId(apply
				.getClubId());
		// 获取成员目前职位
		int positionId = userclubDao.getUserclubByApplyId(applyId)
				.getPositionId();
		Position nowPosition = positionDao.get(positionId);
		positionName.add(nowPosition.getName()); // 当前职位放在list的第一位
		// 获取职位名称
		for (Position position : positionlist) {
			String name = position.getName();
			// 不和当前职位相等 放进list
			if (!nowPosition.getName().equals(name)) {
				positionName.add(name);
			}
		}
		apply.setPositionNameList(positionName);
		return apply;
	}

	public List<Apply> getClubApplyIsOk(int id) {
		List<Apply> list = applyDao.getApplyByClubIdStatusIsOk(id);
		if (list == null) {
			logger.info("不存在已通过的申请书");
		}
		return list;
	}

	public List<Apply> getClubApplyHasExam(int id) {
		return applyDao.getApplyByClubIdStatueNotTwo(id);
	}

	public List<Apply> getClubApplyNotExam(int id) {
		return applyDao.getApplyByClubIdStatueIsTwo(id);
	}

	public void notAgree(int applyId) {
		Apply apply = applyDao.get(applyId);
		apply.setStatue(1);
		applyDao.updateWithNotNullProperties(apply);
	}
}
