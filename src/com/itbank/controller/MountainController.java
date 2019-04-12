package com.itbank.controller;
//��� ���õ� ��� ó���� ����ϴ� ��Ʈ�ѷ�..

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.common.exception.DataNotFoundException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Mountain;
import com.itbank.model.service.MountainService;

@Controller
public class MountainController {
	@Autowired
	private MountainService mtService;
	
	//������ ����� �� ���� ��ȸ ��û ó��
	@RequestMapping(value="/admin/mountain/list",method = RequestMethod.GET) //�������
	@ResponseBody //jsp ��ȯ���� �ʱ�� ���ڳ� , ��������! �ѱ��� ������ �ִ�!!������̼� �ڵ鷯 ����� �������, �츰 �̹� �س�
	public String getList(@RequestParam("name") String name) {
		List<Mountain> mtList = mtService.getList(name);
		
		//�ڹ� ��ü�� json���� ��ȯ�Ͽ� Ŭ���̾�Ʈ�� ����
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<mtList.size();i++) {
			Mountain mt = mtList.get(i);
			JSONObject obj = new JSONObject();
			obj.put("addr",mt.getAddr());
			obj.put("detail",mt.getDetail());
			
			jsonArray.add(obj);
		}
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	//�� ���� ���!! // ������ �ƴ϶� �����͵��̿����� 1���� �ƴ� �������� �����ͱ⶧���� Mountain �Ű�����
	@RequestMapping(value="/admin/mountain/regist",method=RequestMethod.POST)
	//@ResponseBody //ViewResolve�� ������ �ʰ� ����..���� ������ .jsp�� �ǹ����ϱ�..�츰 �� ������( json ) �� ������/ �̰Ŵ� �񵿱⿡ ����
	//�̷��Ը� �ص� DTO���� ���±���... , �̰� �񵿱Ⱑ �ƴ϶� ����..����ϰ� �ٽ� ��� ����Ʈ�� ���ž�
	public String regist(Mountain mountain,HttpServletRequest request) {
		MultipartFile myFile = mountain.getMyFile();
		String filename = myFile.getOriginalFilename();
		System.out.println("���ϸ���"+ filename);
		
		
		/*
		 * InputStream fis = null; //������ �ƴ϶� �޸𸮿� ���մ°� �о�帵���Ŵϱ� Fis�� �ƴ� is
		 * FileOutputStream fos = null; try { fis = myFile.getInputStream(); //request��
		 * context������ �˰������� request�� �޾ƾ��� String realPath =
		 * request.getServletContext().getRealPath("/data"); fos = new
		 * FileOutputStream(realPath);
		 * 
		 * int data = -1; while(true) { data = fis.read(); if(data==-1)break;
		 * fos.write(data); } } catch (FileNotFoundException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		try {
			String realPath = request.getServletContext().getRealPath("/data");
			myFile.transferTo(new File(realPath+"/"+filename));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mountain.setFilename("2019.jpg");
		mtService.insert(mountain);
		return "redirect:/admin/mountain/mtlist";
	}
	@RequestMapping(value="/admin/mountain/mtlist",method=RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView("admin/map/list");
		return mav;
	}
	
	
	
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	public String getListFail() {
		return null;
	}
	
	@ExceptionHandler(RegistFailException.class)
	@ResponseBody
	public ModelAndView registFail(RegistFailException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("err",e);
		mav.setViewName("admin/error/errorpage");
		return mav;
	}
}
