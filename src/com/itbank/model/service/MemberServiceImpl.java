package com.itbank.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.common.exception.DeleteFailException;
import com.itbank.common.exception.EditFailException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Member;
import com.itbank.model.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	@Qualifier("mybatisMemberDAO")
	private MemberDAO memberDAO;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return  memberDAO.selectAll();
	}

	@Override
	public Member select(int member_id) {
		// TODO Auto-generated method stub
		return memberDAO.select(member_id);
	}

	@Override
	public Member loginCheck(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Member member) throws RegistFailException{
		int result = memberDAO.insert(member);
		//result = 0; 에러를 보고싶다면
		if(result ==0) {
			throw new RegistFailException("등록되지 않았습니다");
		}
		return result;
	}

	@Override
	public int update(Member member) throws EditFailException{
		int result =  memberDAO.update(member);
		if(result==0) {
			throw new EditFailException("수정 실패했습니다");
		}
		return result;
	}

	@Override
	public int delete(int member_id) throws DeleteFailException{
		int result = memberDAO.delete(member_id);
		if(result==0) {
			throw new DeleteFailException("삭제 실패했습니다");
		}
		return result;
	}
}
