package com.itbank.model.service;

import java.util.List;

import com.itbank.model.domain.Mountain;

public interface MountainService {
	public List getList(String searchWrd);
	public void insert(Mountain mountain);
	public List selectAll();
	public Mountain select(int mountain_id);
	public void update(Mountain mountain);
	public void delete(int mountain_id);
}
