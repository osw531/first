package com.itbank.controller;
//산과 관련된 모든 처리를 담당하는 컨트롤러..

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
	
	//관리자 모드의 산 정보 조회 요청 처리
	@RequestMapping(value="/admin/mountain/list",method = RequestMethod.GET) //정석대로
	@ResponseBody //jsp 반환하지 않기로 했자나 , 조심할쩜! 한글이 깨질수 있다!!어노테이션 핸들러 어댑터 등록해찌, 우린 이미 해놈
	public String getList(@RequestParam("name") String name) {
		List<Mountain> mtList = mtService.getList(name);
		
		//자바 개체를 json으로 변환하여 클라이언트에 전송
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
	
	//산 정보 등록!! // 사진이 아니라 데이터들이였으면 1개가 아닌 여러개의 데이터기때문에 Mountain 매개변수
	@RequestMapping(value="/admin/mountain/regist",method=RequestMethod.POST)
	//@ResponseBody //ViewResolve를 만나지 않게 해줌..만약 만나면 .jsp가 되버리니까..우린 날 데이터( json ) 을 보낼것/ 이거는 비동기에 쓰네
	//이렇게만 해도 DTO에는 들어가는구나... , 이건 비동기가 아니라 동기..등록하고 다시 어디 사이트로 갈거야
	public String regist(Mountain mountain,HttpServletRequest request) {
		MultipartFile myFile = mountain.getMyFile();
		String filename = myFile.getOriginalFilename();
		System.out.println("파일명은"+ filename);
		
		
		/*
		 * InputStream fis = null; //파일이 아니라 메모리에 떠잇는걸 읽어드링ㄹ거니까 Fis가 아닌 is
		 * FileOutputStream fos = null; try { fis = myFile.getInputStream(); //request가
		 * context정보를 알고있으니 request도 받아야해 String realPath =
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
