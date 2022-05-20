package com.main.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class LoggerAdvice {

	@Pointcut("execution( * com.main.service.*.*(..))")
	private void logfirst() {}
	
	@Pointcut("execution( * com.main.service.*.getAll(..))")
	private void logAftRet() {}
	
	@Before(value = "logfirst()")
	public void loggbefore(JoinPoint jp) {
		log.info(" INSIDE OUR POINTCUT EXPRESSION !!!!!!!1 \n");
		log.info("THE METHOD SIGNATURE IS {}",jp.getSignature());
	}
	@After(value = "logfirst()")
	public void loggafter(JoinPoint jp) {
		log.info(" INSIDE OUR POINTCUT EXPRESSION !!!!!!!1 \n");
		log.info("THE METHOD args IS {}",jp.getArgs());
	}
	@AfterReturning(pointcut = "logAftRet()",returning = "list")
	public void loggafterReturning(JoinPoint jp,List<?> list) {
		log.info(" INSIDE OUR POINTCUT EXPRESSION !!!!!!!1 \n");
		log.info("THE METHOD args IS {}",jp.getArgs());
		list.forEach(x->log.info("X {}",x));
	}
	
}
