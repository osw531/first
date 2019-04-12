package com.itbank.openapi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 *	xml을 파싱할 때 각종 이벤트를 발생시키는 객체!! 
 */
public class MyHandler extends DefaultHandler {
	List<EMP> memberList;
	EMP emp = null;
	
	//현재 실행부의 위치를 알 수 있는 단서
	private boolean member;
	private boolean id;
	private  boolean name;
	private boolean sal;
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("문서가 시작됩니다.");
		memberList = new ArrayList();
		//사원이 없을 경우 size가 0이여야 한다.즉, null이면 아니된다.
	}

	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<" + tag + ">");
		if(tag.equals("member")) { //<member> 태그를 만나면 DTO 1개 생성!!
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

	//태그 사이에 들어가는 컨텐츠를 감지하여 알려주는 메서드 , 즉 태그 사이의 글씨를 만나면 이 메서드가 동작
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
		
		if(tag.equals("member")) { //<member> 태그를 만나면 DTO 1개 생성!!
			emp = new EMP();
			member=false;
			//리스트에 담기
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
		System.out.println("문서가 종료됩니다., 최종 갯수는"+memberList.size());
	}
}
