package com.itbank.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.common.exception.DataNotFoundException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Mountain;
import com.itbank.model.repository.MountainDAO;
import com.itbank.openapi.MountainParser;

@Service
public class MountainServiceImpl implements MountainService{
	private MountainParser mtParser;
	@Autowired
	private MountainDAO mountainDAO;
	String apiKey = "V%2FCMhPkKHP%2B3ATuGCgGXQEvnIZZvCqh2wcj6DON70mzjI599hECyNMpkGv5USnCMm9ziFalU7MOJdg7K6Hznkw%3D%3D";
	
	@Override
	public List getList(String searchWrd) throws DataNotFoundException{
		mtParser = new MountainParser();
		List mtList = mtParser.getList(apiKey,searchWrd);
		if(mtList == null) {
			throw new DataNotFoundException("검색 결과가 없어요");
		}
		return mtList; 
	}

	@Override
	public void insert(Mountain mountain) throws RegistFailException{
		int result = mountainDAO.insert(mountain);
		if(result ==0) {
			throw new RegistFailException("등록 실패!!");
		}
	}

	@Override
	public List selectAll() {
		return mountainDAO.selectAll();
	}

	@Override
	public Mountain select(int mountain_id) {
		return mountainDAO.select(mountain_id); //만약 그 1건이 null인 경우에 트라이문 처리 해줘야함
	}

	@Override
	public void update(Mountain mountain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int mountain_id) {
		// TODO Auto-generated method stub
		
	}
}
