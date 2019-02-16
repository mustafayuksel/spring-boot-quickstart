package com.mustafayuksel.quickstart.springbootquickstart;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
public class AopConfiguration {
	@Bean
	public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
		return new PerformanceMonitorInterceptor(true);
	}

	@Bean
	public Advisor performanceMonitorAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(
				"execution(* *(..)) && (within(com.mustafayuksel.quickstart.springbootquickstart.controller..*)||within(com.mustafayuksel.quickstart.springbootquickstart.service.impl..*))");
		return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
	}
}