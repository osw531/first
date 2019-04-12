package com.itbank.model.repository;

import java.util.List;

import com.itbank.model.domain.Mountain;

public interface MountainDAO {
	public List selectAll();
	public Mountain select(int mountain_id);
	public int insert(Mountain mountain);
	public int update(Mountain mountain);
	public int delete(int mountain_id);
}
