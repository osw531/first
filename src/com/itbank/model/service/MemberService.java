package com.itbank.model.service;

import java.util.List;

import com.itbank.model.domain.Member;

public interface MemberService {
	public List selectAll();
	public Member select(int member_id);
	public Member loginCheck(Member member);
	public int insert(Member member);
	public int update(Member member);
	public int delete(int member_id);
}
