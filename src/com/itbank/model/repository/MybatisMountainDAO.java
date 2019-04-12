package com.itbank.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itbank.model.domain.Mountain;

@Repository
public class MybatisMountainDAO implements MountainDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mountain select(int mountain_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Mountain mountain) {
		return sessionTemplate.insert("Mountain.insert",mountain);
	}

	@Override
	public int update(Mountain mountain) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int mountain_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
