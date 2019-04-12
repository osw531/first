package com.itbank.model.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.itbank.model.domain.Admin;

public interface AdminService {
	public List selectAll();
	public Admin select();
	public Admin loginCheck(Admin admin);
	public void insert(Admin admin);
	public void update(Admin admin);
	public void delete(Admin admin);
}
