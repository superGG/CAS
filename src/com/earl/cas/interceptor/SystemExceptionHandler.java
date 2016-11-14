package com.earl.cas.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.earl.cas.exception.ApplicationException;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.vo.ResultMessage;

@ControllerAdvice
public class SystemExceptionHandler implements HandlerExceptionResolver {

	private static Log logger = LogFactory.getLog(SystemExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		ResultMessage resultMessage = new ResultMessage();

		if (ex instanceof ApplicationException) {
			resultMessage.setServiceResult(false);
			resultMessage.setResultInfo(ex.getMessage());
		} else if (ex instanceof DomainSecurityException) {
			resultMessage.setServiceResult(false);
			resultMessage.setResultInfo(ex.getMessage());
		}   else if (ex instanceof SecurityException) {
			resultMessage.setServiceResult(false);
			resultMessage.setResultInfo(ex.getMessage());
			logger.info("resultMessage =>" + resultMessage.toJson());
		} else {
			resultMessage.setServiceResult(false);
			resultMessage.setResultInfo("系统出错");
			logger.info("resultMessage =>" + resultMessage.toJson());
		}
		try {
			out = response.getWriter();
			out.write(resultMessage.toJson());
			// out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.printStackTrace();
		// 注掉这个，后台不会报out.getWriter is ...异常
		// out.flush();
		// out.close();
		return null;
	}
}