package com.itbank.openapi;
//���������� �ƴ϶� ��Ʈ��ũ �� �����ϴ� �ڿ��� �������� ���ø����̼����� ��������

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLLoader {
	//������ url�� �����Ͽ�, ��Ʈ���� �������ش�. ���� �����ڰ� ��Ʈ���� �̿��Ͽ� �������� ���α׷����� �Է�ó���� �ϸ� �ȴ�.
	URL url;
	URLConnection con;
	BufferedReader buffr;
	public URLLoader() {
		try {
			url = new URL("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey=V%2FCMhPkKHP%2B3ATuGCgGXQEvnIZZvCqh2wcj6DON70mzjI599hECyNMpkGv5USnCMm9ziFalU7MOJdg7K6Hznkw%3D%3D&searchWrd=%EB%B6%81%ED%95%9C%EC%82%B0");
			con=url.openConnection();
			
			InputStream is = con.getInputStream(); //����Ʈ���, 1����Ʈ�� õõ�� �����ϱ� �ð��� �����ɸ�
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
