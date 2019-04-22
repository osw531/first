package com.itbank.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
/*
 * ���� �ۼ����� ��Ʈ�ѷ��� Ŭ���̾�Ʈ�� ������ �������� ������ ����...�� ���������� �ܸ��� ���� �����ϱ� ����
 * ����� ���������� ���� ������ ���·� �����ؾ���..( json , xml ) 
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itbank.model.domain.Mountain;
import com.itbank.model.service.MountainService;

@RestController //���� �������� REST, �� ����...���� �� ��Ŭ�������� ResponseBody�� ����� �ʿ䰡 ����
public class RestMountainController {
	@Autowired
	private MountainService mtService;
	
	//�������� ������ ���� �ʰ�, �����ڰ� ���� RESTful �� ���񽺸� ó���� ���...�����ڰ� ������ JSON�� ����� �����Ѵ�..
	
	@RequestMapping(value="/rest/mountains2",method=RequestMethod.GET)
	//@ResponseBody //viewResolver�� ������Ű�� �ʴ´�..�갡 ������ �ڲ� ���� .jsp�� �پ �������� ����..
	//�� ��ȯ�� ��ü�� ���� ������ ��ü�� �Ǿ �� ��ü�� ����������.
	public String selectAll() {
		List<Mountain> mtList = mtService.selectAll();
		System.out.println("�� ��� ������ ���ϼ���?");
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<mtList.size();i++) {
			Mountain mt = mtList.get(i);
			JSONObject json = new JSONObject();
			json.put("mountain_id", mt.getMountain_id());
			json.put("name",mt.getName());
			json.put("addr",mt.getAddr());
			json.put("detail",mt.getDetail());
			json.put("filename",mt.getFilename());
			json.put("lati",mt.getLati());
			json.put("longi",mt.getLongi());
			json.put("marker",mt.getMarker());
			
			jsonArray.add(json);
		}
		return jsonArray.toString();
	}
	
	// �� ���� ������ �����ڵ��� ������ POJO�� json, xml ���� ��ȯ �۾� ( Converting ) �� ó���ؾ� �ϴµ�, �� ������ �������� 
	// �ڵ������������ش�!!
	@RequestMapping(value="/rest/mountains",method=RequestMethod.GET,produces="application/json") // text/html�� ����, application/xml
	public List selectAllByRest() {
		List<Mountain> mtList = mtService.selectAll();
		return mtList;
	}
}
