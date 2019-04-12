package com.itbank.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//톰캣이 가동될때의 이벤트를 감지하여 원하는 작업을 할 수 있는 클래스
public class MyContextLoaderListener implements ServletContextListener{

	//톰캣이 가동될 때 호출되는 메서드
	@Override
	public void contextInitialized(ServletContextEvent e) {
		// TODO Auto-generated method stub
		System.out.println("고양이 가동");
		String test = e.getServletContext().getInitParameter("contextConfigLocation");
		System.out.println(test);
	}
	
	//톰캣이 종료될 때 호출되는 메서드
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub
		System.out.println("고양이 종료");
	}


}
