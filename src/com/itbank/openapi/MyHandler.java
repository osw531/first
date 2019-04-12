package com.itbank.openapi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 *	xml�� �Ľ��� �� ���� �̺�Ʈ�� �߻���Ű�� ��ü!! 
 */
public class MyHandler extends DefaultHandler {
	List<EMP> memberList;
	EMP emp = null;
	
	//���� ������� ��ġ�� �� �� �ִ� �ܼ�
	private boolean member;
	private boolean id;
	private  boolean name;
	private boolean sal;
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("������ ���۵˴ϴ�.");
		memberList = new ArrayList();
		//����� ���� ��� size�� 0�̿��� �Ѵ�.��, null�̸� �ƴϵȴ�.
	}

	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<" + tag + ">");
		if(tag.equals("member")) { //<member> �±׸� ������ DTO 1�� ����!!
			emp = new EMP();
			member=true;
		}if(tag.equals("id")) {
			id=true;
		}if(tag.equals("name")) {
			name=true;
		}if(tag.equals("sal")) {
			sal=true;
		}
		
	}

	//�±� ���̿� ���� �������� �����Ͽ� �˷��ִ� �޼��� , �� �±� ������ �۾��� ������ �� �޼��尡 ����
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		System.out.print(content); 
		
		if(id) {
			emp.setId(content);
		}else if(name) {
			emp.setName(content);
		}else if(sal) {
			emp.setSal(Integer.parseInt(content));
		}
	}

	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</" + tag + ">");
		
		if(tag.equals("member")) { //<member> �±׸� ������ DTO 1�� ����!!
			emp = new EMP();
			member=false;
			//����Ʈ�� ���
			memberList.add(emp);
		}if(tag.equals("id")) {
			id=false;
		}if(tag.equals("name")) {
			name=false;
		}if(tag.equals("sal")) {
			sal=false;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("������ ����˴ϴ�., ���� ������"+memberList.size());
	}
}
