package com.itbank.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//��Ĺ�� �����ɶ��� �̺�Ʈ�� �����Ͽ� ���ϴ� �۾��� �� �� �ִ� Ŭ����
public class MyContextLoaderListener implements ServletContextListener{

	//��Ĺ�� ������ �� ȣ��Ǵ� �޼���
	@Override
	public void contextInitialized(ServletContextEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����� ����");
		String test = e.getServletContext().getInitParameter("contextConfigLocation");
		System.out.println(test);
	}
	
	//��Ĺ�� ����� �� ȣ��Ǵ� �޼���
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����� ����");
	}


}
