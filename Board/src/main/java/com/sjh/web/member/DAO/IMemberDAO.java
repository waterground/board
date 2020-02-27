package com.sjh.web.member.DAO;

import com.sjh.web.member.Member;

public interface IMemberDAO {

	int memberInsert(Member member);

	Member memberSelect(Member member);

	Member memberUpdate(Member member);

	int memberDelete(Member member);

}