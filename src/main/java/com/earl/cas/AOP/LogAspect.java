package com.earl.cas.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.earl.cas.vo.JsonEntry;

/**
 * AOP：将controller层返回的ResultMessage或者result转成json
 */
@Service
@Aspect
public class LogAspect {

	public static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
//	* com.earl.fishshop.domain.*.*DaoImpl.*(..)
	@Pointcut("execution(* com.earl.cas.controller.*Controller.*(..))")
	public void logManager() throws Throwable {
	}

	// @Pointcut("execution(* com.mysoft.manager.impl.TxtFileManager.*(..))")
	public void txtFileManager() {
	}

	// @Pointcut("execution(* com.mysoft.manager.KeywordsChecker.checkNormalKeywords(..))")
	public void checkNormalKeywords() {
	}

	// @Pointcut("execution(* com.mysoft.manager.KeywordsChecker.checkFixKeywords()) && args(text, ..)")
	public void checkFixKeywords(String text) {
	}

	// @Around("propertyManager() || txtFileManager()")
	//两个相同的@Around("logManager()")在两个方法上，
	//@Around
	//invoke()
	//@Around
	//invoke1()
	//执行顺序是，
	//invoke
//		invoke1
//		
//		invoke1
//	  invoke
	@Around("logManager()")
	public Object invoke(ProceedingJoinPoint join) throws Throwable {

		logger.info("start "+join.getSignature());
		long start = System.currentTimeMillis();
		Object result = join.proceed();

		String toShow = null;

		if(ResponseEntity.class.isInstance(result)){
			Object resultParams = ((ResponseEntity) result).getBody();
			if (resultParams instanceof JsonEntry){
				JsonEntry show = (JsonEntry)resultParams;
				toShow = show.toJson();
			}
		}else{
			if (result instanceof JsonEntry){
				JsonEntry show = (JsonEntry)result;
				toShow = show.toJson();
			}
		}

		Signature name = join.getSignature();
		// String name =
		// MethodSignature.class.cast(join.getSignature()).getMethod().getName();
		Object[] args = join.getArgs();
		long time = System.currentTimeMillis() - start;
		StringBuilder builder = new StringBuilder();
		builder.append("MethodSignature:").append(name).append("\n").append("Args:")
				.append(args).append("\n").append("毫秒:").append(time).append("秒:")
				.append(time / 1000).append("\n").append("result:").append(toShow);
		logger.info(builder.toString());
		return result;
	}
	
	// @Around("checkNormalKeywords()")
	public String invokeAndReturnString(ProceedingJoinPoint join)
			throws Throwable {
		return "";
	}

	/**
	 * @param text
	 * @return
	 * @throws Throwable
	 */
	// @Around(value = "checkFixKeywords(text)")
	public String invokeCheckFixKeywords(ProceedingJoinPoint join, String text)
			throws Throwable {
		if ("abcflg".equals(text)) {
			return "flg";
		}
		return null;
	}
	
}