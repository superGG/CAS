package com.earl.cas.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.earl.cas.commons.BaseController;
import com.earl.cas.entity.Photo;
import com.earl.cas.service.PhotoService;
import com.earl.cas.vo.PageInfo;
import com.earl.cas.vo.ResultMessage;

/**
 * Photo的controller.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@RestController
@RequestMapping(value = "/photo")
public class PhotoController extends BaseController {

	private final Logger logger = LoggerFactory
			.getLogger(PhotoController.class);

	@Autowired
	private PhotoService photoService;

	private ResultMessage result = null;

	/**
	 * GET /photo -> get all the photo
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all photo");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Photo> photoList = photoService.findAll();
		result.getResultParm().put("photo", photoList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 多图片上传(每个文件不能同名——同名覆盖).
	 * 
	 * @author 宋.
	 * @param file(每个参数都不能相同，不然会覆盖)
	 * @param photo
	 *            不可缺少 albumId
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> upload2(HttpServletRequest request,
			HttpServletResponse response, Photo photo)
			throws IllegalStateException, IOException {
		result = new ResultMessage();
		List<Photo> list = photoService.upload(request, response, photo);
		result.setResultInfo("上传完成");
		result.setServiceResult(true);
		result.getResultParm().put("photoList", list);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 获取某相册照片（分页）
	 * 
	 * @author 宋.
	 * @param photo
	 *            必须含有 albumId
	 * @param pageInfo
	 *            必须含有 indexPageNum\size
	 * @return
	 */
	@RequestMapping(value = "/getAlbumPhoto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAlbumPhoto(Photo photo,
			PageInfo pageInfo) {
		result = new ResultMessage();
		List<Photo> photoList = photoService.findByGivenCreteria(photo,
				pageInfo);
		result.setServiceResult(true);
		result.getResultParm().put("photoList", photoList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取某相册所有照片（不分页）
	 * 
	 * @author 宋.
	 * @param albumId 相册id
	 * @return
	 */
	@RequestMapping(value = "/getAllAlbumPhoto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAlbumPhoto(Integer albumId) {
		result = new ResultMessage();
		Photo photo = new Photo();
		photo.setAlbumId(albumId);
		List<Photo> photoList = photoService.findByGivenCreteria(photo);
		result.setServiceResult(true);
		result.getResultParm().put("photoList", photoList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 根据id删除照片.
	 * 
	 * @author 宋.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(Integer id) {
		result = new ResultMessage();
		int delete = photoService.deleteById(id);
		result.setResultInfo("删除成功");
		result.setServiceResult(true);
		if (delete == 0) {
			result.setResultInfo("删除失败");
			result.setServiceResult(false);
		}
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}


	/**
	 * 更新照片信息.
	 *@author 宋.
	 * @param photo
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Photo photo) {
		result = new ResultMessage();
		photoService.updateWithNotNullProperties(photo);
		result.setResultInfo("更新成功");
		result.setServiceResult(true);
		result.getResultParm().put("photo", photo);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
}
