package com.itbank.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itbank.model.domain.Member;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public List selectAll() {
		return sessionTemplate.selectList("Member.selectAll");
	}

	@Override
	public Member select(int member_id) {
		return sessionTemplate.selectOne("Member.select", member_id);
	}

	@Override
	public Member loginCheck(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Member member) {
		return sessionTemplate.insert("Member.insert",member);
		
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return sessionTemplate.update("Member.update",member);
	}

	@Override
	public int delete(int member_id) {
		// TODO Auto-generated method stub
		return sessionTemplate.delete("Member.delete",member_id);
	}

}
