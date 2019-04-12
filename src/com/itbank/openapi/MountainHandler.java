package com.itbank.openapi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.itbank.model.domain.Mountain;

//산 정보 xml을 자바의 객체로 변환하는 핸들러 클래스
public class MountainHandler extends DefaultHandler {
	private boolean item;
	private boolean mntiadd;
	private boolean mntidetails;

	private List<Mountain> mtList;
	Mountain mt;

	public List<Mountain> getMtList() {
		return mtList;
	}

	@Override
	public void startDocument() throws SAXException {
		mtList = new ArrayList();
	}

	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		if (tag.equals("item")) {
			mt = new Mountain();
			item = true;
		} else if (tag.equals("mntiadd")) {
			mntiadd = true;
		} else if (tag.equals("mntidetails")) {
			mntidetails = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		if (mntiadd) { // 현재 위치가 주소라면
			mt.setAddr(content);
		} else if (mntidetails) { // 현재 위치가 상세내용이라면..
			mt.setDetail(content);
		}
	}

	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		if (tag.equals("item")) {
			item = false;
			mtList.add(mt);
		} else if (tag.equals("mntiadd")) {
			mntiadd = false;
		} else if (tag.equals("mntidetails")) {
			mntidetails = false;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("최종 결과" + mtList.size());
	}
}