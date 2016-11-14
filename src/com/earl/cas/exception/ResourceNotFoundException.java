package com.earl.cas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 资源不存在异常
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason="资源找不到")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9208522773597070069L;

}