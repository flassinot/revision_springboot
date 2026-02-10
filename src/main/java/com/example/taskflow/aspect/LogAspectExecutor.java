package com.example.taskflow.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspectExecutor {

	@Around("@annotation(com.example.taskflow.annotation.Loggable)")
	public void advice(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Method called : {}.{} | arguments : {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), joinPoint.getArgs());
		joinPoint.proceed();
	}
}