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
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.ClubTypeDao;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.ClubType;
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
	private ClubDao clubDao;
	
	@Resource
	private ClubTypeDao clubTypeDao;

	@Override
	protected BaseDao<Club> getDao() {
		return clubDao;
	}

	public List<Club> getByName(String clubName) {

		List<Club> clublist = clubDao.getByName(clubName);
		if (clublist == null) {
			throw new DomainSecurityException("找不到该社团");
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
		// 一些容器
		List<String> typelist = new ArrayList<String>();
		// 获得社团信息
		Club club = clubDao.getClubByuserDetailId(detailId);
		// 获得所有社团类型
		List<ClubType> clubtypelist = clubTypeDao.findAll();
		// 获得社团当前类型
		String typename = clubTypeDao.get(club.getTypeId()).getName();
		// 放在list第一位
		typelist.add(typename);
		// 遍历clubtypelist
		for (ClubType typeVo : clubtypelist) {
			String nameVo = typeVo.getName();
			if (!nameVo.equals(typename)) {
				typelist.add(nameVo);
			}
		}
		club.setClubTypeList(typelist);
		return club;
	}

	public void updateMyclub(Club club, MultipartFile file,
			HttpServletRequest request,String typeName) {
		if (club.getId() != null) {
			if (!file.isEmpty()) {
				logger.info("file不为空，开始处理上传社徽");
				String shehuipath = FileUploadUtil.NewFileUpload(request, file,
						"shehuipath");
				logger.info("上传社徽访问地址：" + shehuipath);
				club.setBadge(shehuipath);
			}
			//获得职位Id
			ClubType clubtype = clubTypeDao.getByName(typeName);
			club.setTypeId(clubtype.getId());
			clubDao.updateWithNotNullProperties(club);
		}
		else{
			throw new DomainSecurityException("找不到社团ID");
		}
	}
}
