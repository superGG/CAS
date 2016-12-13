package com.earl.cas.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.AlbumDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.PhotoDao;
import com.earl.cas.entity.Album;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.Photo;
import com.earl.cas.service.PhotoService;

/**
 * photoService实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("photoService")
@Transactional
public class PhotoServiceImpl extends BaseServiceImpl<Photo> implements
		PhotoService {

	private static Logger logger = LoggerFactory
			.getLogger(PhotoServiceImpl.class);

	@Autowired
	private PhotoDao photoDao;

	@Autowired
	private AlbumDao albumDao;

	@Autowired
	private ClubDao clubDao;

	@Override
	protected BaseDao<Photo> getDao() {
		return photoDao;
	}

	@Override
	public  List<Photo> upload(HttpServletRequest request, HttpServletResponse response, Photo photo) throws IllegalStateException, IOException {
		String rootPath = request.getSession().getServletContext().getRealPath("/");    //项目的根目录
		Album album = albumDao.get(photo.getAlbumId());
		Club club = clubDao.get(album.getClubId());
		Photo photo_db = null;
		List<Photo> list = new ArrayList<Photo>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int start = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String fileName = file.getOriginalFilename();
						// 定义上传路径
						String db_path = "/photo/" + club.getName()+ "/" + album.getName()+"/"+ fileName;
						String path = rootPath + db_path;
//						String path = rootPath + "/photo/"+ fileName;
						logger.info("文件保存路径：" + path);
						File localFile = new File(path);
						localFile.mkdirs();
						file.transferTo(localFile);
						//保存到数据库
						photo_db = new Photo();
						String photoName = fileName.split("\\.")[0];
						photo_db.setAlbumId(album.getId());
						photo_db.setPath(db_path);
						photo_db.setContent(photoName);
						photoDao.save(photo_db);
						list.add(photo_db);
					}
				}
				// 记录上传该文件后的时间
				int end = (int) System.currentTimeMillis();
				logger.info("上传文件使用时间：" + (end - start));
				
			}

		}
		return list;
	}
}
