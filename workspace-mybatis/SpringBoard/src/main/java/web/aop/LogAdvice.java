package web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {

	// 로깅 객체
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

	@Before("within(web.controller.*)")
	public void logBefore(JoinPoint joinPoint) {
		logger.debug("====================================================");
		logger.debug(">>>>>>>>>>>>>>>>>>>>>> START <<<<<<<<<<<<<<<<<<<<<<<");
		logger.debug("[class] {}", joinPoint.getStaticPart().getClass());
		logger.debug("[method] {}", joinPoint.getSignature().getName() + "()");

	}

	@After("within(web.controller.*)")
	public void logAfter() {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>> END <<<<<<<<<<<<<<<<<<<<<<<");
		logger.debug("====================================================");

	}

	@Around("within(web.service..*)")
	private Object logAroung(ProceedingJoinPoint joinPoint) {

		// 실행 전 시간체크
		long beforeTime = System.currentTimeMillis();

		Object obj = null;
		try {
			//서비스 메소드 실행
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		//실행 후 시간체크
		long afterTime = System.currentTimeMillis();

		logger.debug("[소요시간] {}ms", afterTime-beforeTime);

	
		return obj;
	}

	
	
	
	
}// class
