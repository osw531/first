package com.itbank.common.auth;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.model.domain.Admin;

//매번 로그인이 필요한 코드마다 로직을 작성하지 말고, 공통 코드로 분리시켜 AOP를 적용해본다!!
@Aspect
public class LoginCheck {
	
	@Pointcut("execution(public * com.itbank.controller.AdminController..main*(..))")
	public void loginCut() {
		
	}
	
	@Around("loginCut()")
	public String checkSession(ProceedingJoinPoint joinPoint) {
		String viewName = null;
		HttpServletRequest request = null;
		//세션 있는지 여부 체크!!  ,, 낚아 챘을때 정보를 joinpoint가 가지고있다.
		Object[] args = joinPoint.getArgs();
		for(int i=0;i<args.length;i++) {
			System.out.println(args[i]);
			//오브젝트의 자료형이 HttpServletRequest 형인지 알아보기
			if(args[i] instanceof HttpServletRequest) {
				request = (HttpServletRequest) args[i]; // 요청 객체만 골라서 담기!!
			}
		}
		//세션에 담겨있는 무언가가 있는지 체크 들어가자!!
		if(request.getSession().getAttribute("admin")==null) {
			System.out.println("로그인 안했자나!!");
			viewName = "redirect:/admin/error/accessdeny.jsp";
		}else {
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			System.out.println("로그인 했군!!"+admin.getId());
			viewName = "admin/index";
		}
		return viewName;
	}
}
