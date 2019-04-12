package com.itbank.openapi;
/*
 	xml�� �ڹٰ� �ƴ϶� �׳� �ؽ�Ʈ����� �������� ���̹Ƿ�, jsonó�� �Ľ��� �ؼ� �ڹ�ȭ ���Ѻ���!!
 	xml �Ľ��� json �Ľ̺��ٴ� �ణ ����..
 	xml �Ľ� ����� ũ�� 2������ �ִ�
 		(1) xml�� html�� ���� �±׷� ǥ���Ǵ� ��ũ�� �������̹Ƿ�,
 		DOMȭ ��ų �� �ִ�. ���� �̷��� DOM������ �̿��� �Ľ̹���� �ִ�.
 		���α׷����� ������ javascript DOM�� ����ϹǷ� ��������, �޸� �δ��� �ǹǷ�
 		( Ư�� ����Ʈ�� ���߽ÿ��� �����ϴ� �߼� )
 		(2) SAX �Ľ� ��� : �±׸� ���� ������ �Ʒ����� ������� �о���� ���
 								  �޸� ȿ������ ������ �ڵ尡 ��ƴ�...
 								  SAX �ļ��� ���������� �Ʒ��� �о���� �����߿� �±׵��� ������
 								  �̺�Ʈ�� �߻��ϰ�, �����ڴ� �� �̺�Ʈ�� �� �����ؼ� ���ϴ� �±�������
 								  �ڹٿ� ������Ѿ� �Ѵ�. �̶� �� �̺�Ʈ�� �����ϴ� ��ü�� �ٷ�
 								  DefaultHandler �̴�!!
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MountainParser {	
	SAXParserFactory saxParserFactory;//saxParser�� ������ ���丮
	SAXParser saxParser;//�Ľ��� ����ϴ� ��ü
	URL url;
	URLConnection con;
	MountainHandler handler;
	public MountainParser() {
		saxParserFactory = saxParserFactory.newInstance();
	}
	
	//�����ڷ��ϸ� ��ư���������� �����Ǵϱ� �׳� �޼���� �ҷ�����!!
	public List getList(String apiKey, String searchWrd) {	
		List mtList = null;
		try {
			url = new URL("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey="+apiKey+"&searchWrd="+ URLEncoder.encode(searchWrd, "UTF-8"));
			con = url.openConnection();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�ļ� ����
		try {
			saxParser = saxParserFactory.newSAXParser();
			
			//���ϴ� ��� xml �Ľ� ����!!
			BufferedReader buffr = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			saxParser.parse(con.getInputStream(),handler = new MountainHandler());
			mtList = handler.getMtList();
			System.out.println("�Ľ� �Ϸ��� �Ǽ��� "+mtList.size());
			//�Ľ��� �����
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mtList;
	}
	/*
	 * public static void main(String[] args) { new MountainParser(); }
	 */
}
