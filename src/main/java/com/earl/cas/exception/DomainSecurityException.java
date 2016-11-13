package com.earl.cas.exception;

/**
 * Created by Administrator on 2016/4/16.
 * 系统安全方面的异常
 */
public class DomainSecurityException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DomainSecurityException(String message){
        super(message);
    }
}
