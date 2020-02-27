package com.sjh.web.member.service;

import com.sjh.web.member.Member;

public interface IMemberService {

	void memberRegister(Member member);

	Member memberSearch(Member member);

	Member memberModify(Member member);

	void memberRemove(Member member);

}