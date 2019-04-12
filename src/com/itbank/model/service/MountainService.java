package com.itbank.model.service;

import java.util.List;

import com.itbank.model.domain.Mountain;

public interface MountainService {
	public List getList(String searchWrd);
	public void insert(Mountain mountain);
}
