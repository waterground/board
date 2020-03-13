package com.sjh.web.member.DAO;

import com.sjh.web.member.Member;

public interface IMemberDAO {

	void memberInsert(Member member);

	Member memberSelect(Member member);

	Member memberUpdate(Member member);

	void memberDelete(Member member);

}