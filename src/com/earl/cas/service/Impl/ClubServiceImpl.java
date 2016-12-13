package com.earl.cas.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.earl.cas.dao.PositionDao;
import com.earl.cas.dao.SchoolDao;
import com.earl.cas.dao.UserDetailsDao;
import com.earl.cas.entity.Apply;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.ClubType;
import com.earl.cas.entity.Position;
import com.earl.cas.entity.School;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ClubService;
import com.earl.cas.util.FileUploadUtil;
import com.earl.cas.vo.PageInfo;

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
	private SchoolDao schoolDao;

	@Resource
	private ActivityDao activityDao;
	@Resource
	private ApplyDao applyDao;

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

	public Club getClubByuserDetailId(int detailId) {
		Club club = clubDao.getClubByuserDetailId(detailId);
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
		// Long number = userclubDao.getNumberByclubId(club.getId());
		// 获取社团人数
		setNumber(club);
		School school = schoolDao.get(club.getSchoolId());
		club.setSchoolName(school.getName());
		return club;
	}

	public Club getMyJoinClub(int clubId) {
		Club club = clubDao.get(clubId);
		// Long number = userclubDao.getNumberByclubId(clubId);
		// club.setNumber(number);
		setNumber(club);
		String typename = clubTypeDao.get(club.getTypeId()).getName();
		club.setTypeName(typename);
		School school = schoolDao.get(club.getSchoolId());
		club.setSchoolName(school.getName());
		return club;
	}

	public void updateMyclub(Club club, String typeName) {

		// if (file!=null) {
		// logger.info("file不为空，开始处理上传社徽");
		// String shehuipath = FileUploadUtil.NewFileUpload(request, file,
		// "shehuipath");
		// logger.info("上传社徽访问地址：" + shehuipath);
		// club.setBadge(shehuipath);
		// }
		// 获得职位Id
		if (club.getId() != null) {
			ClubType clubtype = clubTypeDao.getByName(typeName);
			club.setTypeId(clubtype.getId());
			clubDao.updateWithNotNullProperties(club);
			School school = schoolDao.getByName(club.getSchoolName());
			club.setSchoolName(school.getName());
		} else {
			throw new DomainSecurityException("找不到社团ID");
		}
	}

	public void updateBadge(MultipartFile file, HttpServletRequest request,
			int clubId) {
		String path = null;
		Club club = clubDao.get(clubId);
		if (file != null) {
			logger.info("file不为空，开始处理上传社徽");
			path = FileUploadUtil.NewFileUpload(request, file, "shehuipath");
			logger.info("上传社徽访问地址：" + path);
		}
		club.setBadge(path);
		clubDao.updateWithNotNullProperties(club);
	}

	public List<Club> getMyClubList(int detailId) {
		List<Club> clublist = new ArrayList<Club>();
		// 根据detailId获取所有已经通过申请的申请书
		List<Apply> applyList = applyDao.getBydetailIdStatueIsOk(detailId);
		Club club;
		if (applyList.isEmpty()) {
			throw new DomainSecurityException("该用户没有加入社团");
		}
		for (Apply apply : applyList) {
			// 获得社团
			club = clubDao.get(apply.getClubId());
			// 设置社团人数
			setNumber(club);
			clublist.add(club);
		}
		
		return setName(clublist);
	}

	public Club findById(Integer id) {
		return clubDao.get(id);
	}

	public void delete(int clubId) {
		logger.info("级联删除");
		applyDao.deleteByClubId(clubId);
		positionDao.deleteByClubId(clubId);
		activityDao.deleteByClubId(clubId);
		clubDao.deleteById(clubId);
	}

	public boolean isCreated(Integer detailId) {
		Club club = clubDao.getClubByuserDetailId(detailId);
		if (club != null) {
			return true;
		} else
			return false;
	}

	public void create(Integer detailId, Club club, String schoolName,
			String clubType) {
		Position position = new Position();
		if (detailId == null) {
			throw new DomainSecurityException("请先登录");
		}
		UserDetails ud = userdetailsDao.get(detailId);
		club.setLeader(ud.getName());
		club.setDetailId(detailId);
		School school = schoolDao.getByName(schoolName);
		ClubType clubtype = clubTypeDao.getByName(clubType);
		if (clubDao.getByNameAndSchool(club.getName(), school.getId()) == null) {
			club.setTypeId(clubtype.getId());
			club.setSchoolId(school.getId());
			clubDao.save(club);
			createApplyAndPosition(ud,position,club.getId());
		} else {
			throw new DomainSecurityException("社团已存在");
		}
	}

	public List<Club> getBySchoolName(String name, PageInfo pageInfo) {
		Club club = new Club();
		club.setSchoolId(schoolDao.getByName(name).getId());
		List<Club> clublist = clubDao.findByGivenCriteria(club, pageInfo);
		setNumber(clublist);
		return setName(clublist);
	}

	public List<Club> getByTypeName(String typeName, PageInfo pageInfo) {
		Club club = new Club();
		club.setTypeId(clubTypeDao.getByName(typeName).getId());
		List<Club> clublist = clubDao.findByGivenCriteria(club, pageInfo);
		setNumber(clublist);
		return setName(clublist);
	}

	public List<Club> getAlls(PageInfo pageInfo) {
		List<Club> clublist = clubDao.findAll(pageInfo);
		setNumber(clublist);
		return setName(clublist);
	}

	public Club getById(int clubId) {
		Club club = get(clubId);
		// 设置社团人数
		setNumber(club);
		// club.setNumber(userclubDao.getNumberByclubId(club.getId()));

		ClubType type = clubTypeDao.get(club.getTypeId());
		School school = schoolDao.get(club.getSchoolId());
		club.setSchoolName(school.getName());
		club.setTypeName(type.getName());
		return club;
	}

	public List<Club> getBySearch(String search, PageInfo pageInfo) {
		List<Club> clublist = null;
		List<Club> clublist_school = null;
		clublist = clubDao.getBySearch(search, pageInfo);
		List<School> schoollist = schoolDao.getBySearch(search);
		if (schoollist != null) {
			for (School school : schoollist) {
				clublist_school = clubDao.getBySchoolId(school.getId(),
						pageInfo);
				if (clublist.size() <= pageInfo.getSize()) {
					compare(clublist, clublist_school);
				}
			}
			setNumber(clublist);
			return setName(clublist);
		} else {
			setNumber(clublist);
			return setName(clublist);
		}
	}

	public List<Club> getAllsByRank(PageInfo pageInfo) {
		List<Club> list = clubDao.findAll();
		list = setName(list);
		setNumber(list);
		rank(list);
		pageInfo.setTotalCount((long) list.size());
		if (pageInfo.getSize() * pageInfo.getIndexPageNum() > list.size()) {
			list = list.subList(
					(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize(),
					list.size());
		} else {
			list = list.subList(
					(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize(),
					pageInfo.getSize() * pageInfo.getIndexPageNum());
		}
		return list;
	}

	/**
	 * 为社团加上number属性
	 * 
	 * @param clublist
	 */
	private void setNumber(List<Club> clublist) {
		Apply apply = new Apply();
		apply.setStatue(0);// 设置状态为0：通过申请的
		for (Club club : clublist) {
			apply.setClubId(club.getId());
			List<Apply> applyList = applyDao.findByGivenCriteria(apply);
			club.setNumber(applyList.size());
		}
	}

	/**
	 * 为社团加上number属性
	 * 
	 * @param club
	 */
	private void setNumber(Club club) {
		Apply apply = new Apply();
		apply.setStatue(0);// 设置状态为0：通过申请的
		apply.setClubId(club.getId());
		List<Apply> applyList = applyDao.findByGivenCriteria(apply);
		club.setNumber(applyList.size());
	}



	/**
	 * 为club加上学校名字
	 * 
	 * @param list
	 */
	private List<Club> setName(List<Club> list) {
		List<Club> newList = new ArrayList<Club>();
		School school = null;
		ClubType type = null;
		for (Club club : list) {
			type = clubTypeDao.get(club.getTypeId());
			school = schoolDao.get(club.getSchoolId());
			club.setSchoolName(school.getName());
			club.setTypeName(type.getName());
			newList.add(club);
		}
		return newList;
	}

	/**
	 * 根据number进行排行
	 */
	private void rank(List<Club> list) {
		Collections.sort(list, new Comparator<Club>() {
			@Override
			public int compare(Club club1, Club club2) {
				return club2.getNumber().compareTo(club1.getNumber());
			}
		});
	}

	/**
	 * club进行比较
	 */
	private void compare(List<Club> clublist, List<Club> clublistforschool) {
		// List<Club> temp = new ArrayList<Club>();
		if (clublist.size() > 0) {
			for (Club club_first : clublist) {
				for (Club club_second : clublistforschool) {
					if (club_first.getId() == club_second.getId()) {
						clublist.remove(club_first);
						break;
					}
				}
			}
			clublist.addAll(clublistforschool);
		} else {
			for (Club club_second : clublistforschool) {
				{
					clublist.add(club_second);
				}
			}
		}
	}
	/**
	 * 创建社团时默认把自己放进社团成员里面
	 */
	private void createApplyAndPosition(UserDetails ud,Position position,int clubId){
		position.setName("社长");
		position.setClubId(clubId);
		positionDao.save(position);
		Apply apply = new Apply();
		apply.setClubId(clubId);
		apply.setDetailId(ud.getId());
		apply.setName(ud.getName());
		apply.setPositionId(position.getId());
		if(ud.getPhone()!=null){
			apply.setPhone(ud.getPhone());
		}
		if(ud.getHobby()!=null){
			apply.setHobby(ud.getHobby());
		}
		if(ud.getSex()!=null){
			apply.setSex(ud.getSex());
		}
		apply.setStatue(0);
		applyDao.save(apply);
	}

}
