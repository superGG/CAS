package com.earl.cas.interceptor;



import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 判断是否登录拦截器.
 *@author 宋
 *@date 2016-8-15
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		Map<String, String[]> parameterMap = arg0.getParameterMap();

		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			String[] value = entry.getValue();
			if(value.length == 1 ){
				logger.info(entry.getKey() + " : " + entry.getValue()[0]);
			}else{
				StringBuilder tmpBuilder = new StringBuilder();
				for (String string : value) {
					tmpBuilder.append(entry.getKey()+"-"+string+";");
				}
				logger.info(entry.getKey() + " : " +tmpBuilder.toString());
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		logger.info("进入“是否登录”----拦截器");
		HttpSession session = request.getSession();
		String requestPath = request.getServletPath();
		String rolePath = (String) session.getAttribute("rolePath");
		if (rolePath == null || !requestPath.contains(rolePath)) {
			logger.info("没有登录,请重新登陆!");
			response.sendRedirect(request.getContextPath() + "/common/index"); 
			session.setMaxInactiveInterval(1);//使得session过期
		    return false;
		} 
		return true;
	}

}
