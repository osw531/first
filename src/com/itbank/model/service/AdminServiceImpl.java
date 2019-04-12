package com.itbank.model.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.common.exception.AccountNotFoundException;
import com.itbank.model.domain.Admin;
import com.itbank.model.repository.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	@Qualifier("jdbcAdminDAO")
	private AdminDAO adminDAO;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin loginCheck(Admin admin) throws AccountNotFoundException{
		Admin obj = adminDAO.loginCheck(admin);
		if(obj == null) {
			throw new AccountNotFoundException("일치하는 정보가 없습니다");
		}
		return obj;
	}

	@Override
	public void insert(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Admin admin) {
		// TODO Auto-generated method stub
		
	}

}
