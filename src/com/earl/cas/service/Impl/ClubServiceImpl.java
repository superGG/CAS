package com.earl.cas.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ActivityDao;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.ClubTypeDao;
import com.earl.cas.dao.MessageDao;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.dao.SchoolDao;
import com.earl.cas.dao.UserDetailsDao;
import com.earl.cas.dao.UserclubDao;
import com.earl.cas.entity.Apply;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.ClubType;
import com.earl.cas.entity.School;
import com.earl.cas.entity.Userclub;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ClubService;
import com.earl.cas.util.FileUploadUtil;

/**
 * clubService实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("clubService")
@Transactional
public class ClubServiceImpl extends BaseServiceImpl<Club> implements
		ClubService {

	private static Logger logger = LoggerFactory
			.getLogger(ClubServiceImpl.class);

	@Resource
	private UserclubDao userclubDao;
	
	@Resource
	private SchoolDao schoolDao;

	@Resource
	private ActivityDao activityDao;
	@Resource
	private ApplyDao applyDao;
	
	@Resource
	private MessageDao messageDao;

	@Resource
	private ClubDao clubDao;
	
	@Resource
	private UserDetailsDao userdetailsDao;
	
	@Resource
	private PositionDao positionDao;
	
	@Resource
	private ClubTypeDao clubTypeDao;

	@Override
	protected BaseDao<Club> getDao() {
		return clubDao;
	}

	public List<Club> getByName(String clubName) {

		List<Club> clublist = clubDao.getByName(clubName);
		if (clublist == null) {
			throw new DomainSecurityException("找不到该名字的社团");
		} else {
			return clublist;
		}
	}

	public boolean update(Club club) {
		if (!clubDao.update(club)) {
			throw new DomainSecurityException("更新失败");
		} else {
			return true;
		}

	}

	public Club getClubByuserDetailId(int id) {
		Club club = clubDao.getClubByuserDetailId(id);
		if (club != null) {
			return club;
		} else {
			throw new DomainSecurityException("该用户没创建社团");
		}
	}

	public Club getMyClub(int detailId) {

		// 获得社团信息
		Club club = clubDao.getClubByuserDetailId(detailId);
		// 获得社团当前类型
		String typename = clubTypeDao.get(club.getTypeId()).getName();
		club.setTypeName(typename);
		Long number = userclubDao.getNumberByclubId(club.getId());
		club.setNumber(number);
		return club;
	}

	public Club getMyJoinClub(int clubId) {
		Club club = clubDao.get(clubId);
		Long number = userclubDao.getNumberByclubId(clubId);
		club.setNumber(number);
		String typename = clubTypeDao.get(club.getTypeId()).getName();
		club.setTypeName(typename);
		return club;
	}

	public void updateMyclub(Club club, MultipartFile file,
			HttpServletRequest request, String typeName) {
		if (club.getId() != null) {
			if (!file.isEmpty()) {
				logger.info("file不为空，开始处理上传社徽");
				String shehuipath = FileUploadUtil.NewFileUpload(request, file,
						"shehuipath");
				logger.info("上传社徽访问地址：" + shehuipath);
				club.setBadge(shehuipath);
			}
			// 获得职位Id
			ClubType clubtype = clubTypeDao.getByName(typeName);
			club.setTypeId(clubtype.getId());
			clubDao.updateWithNotNullProperties(club);
		} else {
			throw new DomainSecurityException("找不到社团ID");
		}
	}

	public List<Club> getMyClubList(int detailId) {
		List<Club> clublist = new ArrayList<Club>();
		// 根据detailId获取所有已经通过申请的申请书
		List<Apply> applyList = applyDao.getBydetailIdStatueIsOk(detailId);
		// 根据申请书Id获取所有的userclub里面的clubId
		Userclub uc;
		Club club;
		for (Apply apply : applyList) {

			uc = userclubDao.getUserclubByApplyId(apply.getId());
			if (uc != null) { 
				// 获得社团
				club = clubDao.get(uc.getClubId());
				// 获得社团人数
				Long number = userclubDao.getNumberByclubId(club.getId());
				club.setNumber(number);
				clublist.add(club);
			}
		}
		return clublist;
	}
	
	public Club findById(Integer id){
		return clubDao.get(id);
	}
	
	public void delete(int clubId){
		logger.info("级联删除");
		applyDao.deleteByClubId(clubId);
		userclubDao.deleteByClubId(clubId);
		positionDao.deleteByClubId(clubId);
		activityDao.deleteByClubId(clubId);
		clubDao.deleteById(clubId);		
	}
	
	public boolean isCreated(Integer detailId){
		Club club = clubDao.getClubByuserDetailId(detailId);
		if(club!=null){
			return true;
		}
		else return false;
	}
	
	public void create(Integer detailId,Club club, String schoolName, String clubType){
		if(detailId==null){
			throw new DomainSecurityException("请先登录");
		}
		club.setLeader(userdetailsDao.get(detailId).getName());
		club.setDetailId(detailId);
		School school = schoolDao.getByName(schoolName);
		ClubType clubtype =  clubTypeDao.getByName(clubType);
		if(clubDao.getByNameAndSchool(club.getName(),school.getId())==null){
			club.setTypeId(clubtype.getId());
			club.setSchoolId(school.getId());
			clubDao.save(club);
		}
		else{
			throw new DomainSecurityException("社团已存在");
		}
	}
	
	public void quit(int detailId, int clubId){
		Apply apply = applyDao.getByDetailIdAndClubId(detailId,clubId);
		Userclub userclub = userclubDao.getUserclubByApplyId(apply.getId());
		userclubDao.delete(userclub);
		applyDao.delete(apply);
	}
}
