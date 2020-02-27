package com.sjh.web.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjh.web.member.Member;
import com.sjh.web.member.DAO.MemberDAO;

@Service
public class MemberService implements IMemberService {
	@Autowired
	MemberDAO dao;
	
	@Override
	public void memberRegister(Member member) {
		dao.memberInsert(member);
	}
	
	@Override
	public Member memberSearch(Member member) {
		return dao.memberSelect(member);
	}
	
	@Override
	public Member memberModify(Member member) {
		return dao.memberUpdate(member);
	}
	
	@Override
	public void memberRemove(Member member) {
		dao.memberDelete(member);
	}
}
