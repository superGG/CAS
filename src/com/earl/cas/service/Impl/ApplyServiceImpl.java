package com.earl.cas.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.dao.SchoolDao;
import com.earl.cas.entity.Apply;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.Position;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ApplyService;
import com.earl.cas.vo.Member;
import com.earl.cas.vo.PageInfo;

@Service("applyService")
public class ApplyServiceImpl extends BaseServiceImpl<Apply> implements
		ApplyService {

	private static Logger logger = LoggerFactory
			.getLogger(ApplyServiceImpl.class);

	@Resource
	private ApplyDao applyDao;

	@Resource
	private SchoolDao schoolDao;

	@Resource
	private ClubDao clubDao;

	@Resource
	private PositionDao positionDao;

	@Override
	protected BaseDao<Apply> getDao() {
		return applyDao;
	}

	public void update(Apply apply) {
		if (apply.getStatue() != 0) { // 判断申请表状态
			apply.setStatue(0);
			if (!applyDao.update(apply)) {
				throw new DomainSecurityException("更新失败");
			} 
//			else {
//				// 同意后创建新的用户社团关联对象，职位默认为0
//				Userclub uc = new Userclub();
//				uc.setApplyId(apply.getId());
//				uc.setClubId(apply.getClubId());
//				uc.setPositionId(0);
//				// 进行保存
//				if (userclubDao.save(uc) == 0) {
//					throw new DomainSecurityException("保存userclub失败");
//				}
//			}
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
		if (statue == 0) { //如果同意申请，更新创建时间代表 成员入社时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = sdf.format(date);
			apply.setCreatetime(createtime);
		}
		applyDao.updateWithNotNullProperties(apply);
	}

	public List<Member> getMember(int detailId, PageInfo pageInfo) {
		// 一些容器变量
		List<Member> memberlist = new ArrayList<Member>();
		int clubId;
		Club club = clubDao.getClubByuserDetailId(detailId);
		if (club != null) {
			clubId = club.getId();
		} else {
			throw new DomainSecurityException("用户没有创建社团");
		}
		// 获得该社团申请书列表
		Apply applyVo = new Apply();
		applyVo.setClubId(clubId);
		applyVo.setStatue(0);
		List<Apply> applylist = applyDao.findByGivenCriteria(applyVo, pageInfo);
		// 遍历将信息放进vo类
		for (Apply apply : applylist) {
//			name = apply.getName(); // 从申请书中获得成员名字
			setPositionName(apply);
			Member member = new Member();
			member.setName(apply.getName());
			member.setCreatetime(apply.getCreatetime()); 
//			position = positionDao.get(apply.getPositionId()); // 根据具体职位Id获得职位
			member.setPosition(apply.getName());
			member.setTel(apply.getPhone());
			member.setMajorClass(apply.getMajorClass());
			member.setApplyId(apply.getId());
				memberlist.add(member);
		}
		return memberlist;
	}

	public Apply getMemberDetail(Integer detailId, Integer clubId) {
		Apply apply = new Apply();
		apply.setDetailId(detailId);
		apply.setClubId(clubId);
		List<Apply> list = applyDao.findByGivenCriteria(apply);
		if (list.isEmpty()) {
			throw new DomainSecurityException("获取成员信息失败");
		}
		apply = list.get(0);
		return setPositionName(apply);
	}

	public List<Apply> getClubApplyIsOk(int clubId) {
		List<Apply> applyList = new ArrayList<Apply>();
		List<Apply> applyList_NO_Position = new ArrayList<Apply>();
		Apply apply = new Apply();
		apply.setClubId(clubId);
		apply.setStatue(0);
		Position position;
		List<Apply> list = applyDao.findByGivenCriteria(apply);
		for(Apply index : list) {
			if(index.getPositionId()!=null){
				position = positionDao.get(index.getPositionId());
				index.setPositionName(position.getName());
				applyList.add(index);
			} else {
				applyList_NO_Position.add(index);
			}
		}
		applyList.addAll(applyList_NO_Position);//将有职位显示在前面
		if (list.isEmpty()) {
			logger.info("不存在已通过的成员");
		}
		return applyList;
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

	public List<Member> searchMember(int detailId, String name) {
		// 一些临时变量
		List<Member> memberlist = new ArrayList<Member>();
		String memberName;
		int clubId;
		int i = 1; // 计数标记
		Position position;
		// 获得对应的club
		Club club = clubDao.getClubByuserDetailId(detailId);
		clubId = club.getId();
		// 查询相关入社申请书->statue=0，name=%name%，clubId=club.Id;
		List<Apply> applylist = applyDao.getByName(clubId, name);
		if (applylist == null) {
			logger.info("搜索结果为空");
			return null;
		} else {
			
			for (Apply apply : applylist) {
				Member member = new Member();
				memberName = apply.getName(); // 从申请书中获得成员名字
				//member.setId(i); // 编号
				member.setName(memberName);
				member.setCreatetime(apply.getCreatetime()); // userclub上的加入时间
				if(apply.getPositionId()!=null){
					position = positionDao.get(apply.getPositionId()); // 根据具体职位Id获得职位
					member.setPosition(position.getName());		
				}
				member.setTel(apply.getPhone());
				member.setMajorClass(apply.getMajorClass());
				member.setApplyId(apply.getId());
				i++; // 计数器加1
				if (member != null) {
					memberlist.add(member);
				}
			}
			return memberlist;
		}
	}

	public List<Apply> getClubApply(Integer id, Integer statue) {
		// 找到全部申请书
		if (statue == null) {
			return getClubApply(id);
		}
		// 找到已经审核的申请书
		else if (statue.intValue() != 2) {
			return getClubApplyHasExam(id);
		}
		// 找到未审核的申请书
		else {
			return getClubApplyNotExam(id);
		}
	}

	public Apply findById(Integer id) {
		Apply apply = applyDao.get(id);
		if (apply.getPositionId()!=null) {
			Position position = positionDao.get(apply.getPositionId());
			apply.setPositionName(position.getName());
		}
		return apply;
	}

	public void createApply(String clubName, Apply apply, String schoolName) {
		Club club = clubDao.getByNameAndSchool(clubName,
				schoolDao.getByName(schoolName).getId());
		if (club != null) {
			apply.setStatue(2);
			apply.setClubId(club.getId());
			applyDao.save(apply);
		} else {
			throw new DomainSecurityException("社团不存在，无法提交申请书");
		}
	}

	public Apply getByDetailIdAndClubId(int detailId, int clubId) {
		List<Apply> applylist = applyDao.getByDetailIdAndClubId_desc(detailId,
				clubId);
		if (applylist != null) {
			return applylist.get(0);
		} else
			throw new DomainSecurityException("无法找到申请书");
	}
	
	/*
	 * 给社团成员set上职位名称(不是每个成员都具有positionId)
	 */
	private Apply setPositionName(Apply apply){
		if (apply.getPositionId()!=null) {
			Position position = positionDao.get(apply.getPositionId());
			apply.setPositionName(position.getName());
		}
		return apply;
	}

	@Override
	public int updatePosition(Apply apply) {
		return applyDao.updatePosition(apply);
	}

	@Override
	public void quitClub(int detailId, int clubId) {
		Apply apply = new Apply();
		apply.setDetailId(detailId);
		apply.setClubId(clubId);
		List<Apply> applyList = applyDao.findByGivenCriteria(apply);
		if (applyList.isEmpty()) {
			throw new DomainSecurityException("数据有误（该社团没有该用户）");
		}
		applyDao.delete(applyList.get(0));
	}
}
