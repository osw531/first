package com.itbank.openapi;
//로컬파일이 아니라 네트워크 상에 존재하는 자원을 실행중인 어플리케이션으로 가져오자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLLoader {
	//지정된 url과 연결하여, 스트림을 생성해준다. 따라서 개발자가 스트림을 이용하여 실행중인 프로그램으로 입력처리를 하면 된다.
	URL url;
	URLConnection con;
	BufferedReader buffr;
	public URLLoader() {
		try {
			url = new URL("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey=V%2FCMhPkKHP%2B3ATuGCgGXQEvnIZZvCqh2wcj6DON70mzjI599hECyNMpkGv5USnCMm9ziFalU7MOJdg7K6Hznkw%3D%3D&searchWrd=%EB%B6%81%ED%95%9C%EC%82%B0");
			con=url.openConnection();
			
			InputStream is = con.getInputStream(); //바이트기반, 1바이트씩 천천히 읽으니까 시간이 오래걸림
			buffr = new BufferedReader(new InputStreamReader(is,"utf-8"));
			String data = null;
			while(true) {
				data = buffr.readLine();
				if(data ==null)break;
				System.out.println(data);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(buffr != null) {
				try {
					buffr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new URLLoader();
	}
}
