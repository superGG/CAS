package com.earl.cas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.earl.cas.commons.BaseController;
import com.earl.cas.entity.Album;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.AlbumService;
import com.earl.cas.util.FileUploadUtil;
import com.earl.cas.vo.PageInfo;
import com.earl.cas.vo.ResultMessage;

/**
 * Album的controller.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@RestController
@RequestMapping(value = "/album")
public class AlbumController extends BaseController {

	private final Logger logger = LoggerFactory
			.getLogger(AlbumController.class);

	@Autowired
	private AlbumService albumService;

	private ResultMessage result = null;

	/**
	 * GET /album -> get all the album
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll(PageInfo pageInfo) {
		logger.debug("REST request to get all album");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Album> albumList = albumService.getAlls(pageInfo);
		result.getResultParm().put("album", albumList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * GET /album -> 根据社团名称模糊查询社团相册
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> searchAll(String search, PageInfo pageInfo) {
		logger.debug("REST request to get all album");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Album> albumList = albumService.searchAll(search,pageInfo);
		result.getResultParm().put("album", albumList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 获取相册详情.
	 *@author 宋.
	 * @return
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> findById(Integer id) {
		result = new ResultMessage();
		result.setServiceResult(true);
		Album album = albumService.findById(id);
		result.getResultParm().put("album", album);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 添加相册.
	 * 
	 * @author 宋.
	 * @param album
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(Album album) {
		if (album.getClubId() == 0) {
			throw new DomainSecurityException("社团id不能为空");
		}
		result = new ResultMessage();
		result.setResultInfo("添加失败");
		result.setServiceResult(false);
		album.setPath("/album/001.jpg");
		Integer save = albumService.save(album);
		if (save != 0) {
			result.setResultInfo("添加成功");
			result.setServiceResult(true);
			result.getResultParm().put("album", album);
		}
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 删除相册.
	 * 
	 * @author 宋.
	 * @param album
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(Integer id) {
		if (id == null) {
			throw new DomainSecurityException("id不能为空");
		}
		result = new ResultMessage();
		result.setResultInfo("删除成功");
		result.setServiceResult(true);
		int delete = albumService.deleteById(id);
		if (delete == 0) {
			result.setResultInfo("删除失败");
			result.setServiceResult(false);
		}
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 更新相册.
	 * 
	 * @author 宋.
	 * @param album
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Album album,
			MultipartFile file, HttpServletRequest request) {
		if (album.getId() == 0) {
			throw new DomainSecurityException("id不能为空");
		}
		if (file!=null) {
			logger.info("file不为空，开始处理上传相册封面");
			String path = FileUploadUtil.NewFileUpload(request, file, "album");
			logger.info("上传相册封面访问地址：" + path);
			album.setPath(path);
		}
		result = new ResultMessage();
		albumService.updateWithNotNullProperties(album);
		result.setServiceResult(true);
		result.setResultInfo("更新成功");
		result.getResultParm().put("album", albumService.get(album.getId()));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 查询社团全部相册
	 * @author 宋.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getByClub", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getByClub(Integer id) {
		result = new ResultMessage();
		List<Album> albumList = albumService.getByClubId(id);
		result.setServiceResult(true);
		result.getResultParm().put("albumList", albumList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

}
