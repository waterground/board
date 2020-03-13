package com.sjh.web.member.DAO;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sjh.web.member.Member;

@Repository
public class MemberDAO implements IMemberDAO {

	private static final String NAMESPACE = "com.sjh.web.mappers.member.memberMapper";

	private final SqlSession sqlSession;

	@Inject
	public MemberDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void memberInsert(final Member member) {
		sqlSession.insert(NAMESPACE + ".create", member);
	}

	@Override
	public Member memberSelect(final Member member) {
		return sqlSession.selectOne(NAMESPACE + ".select", member);
	}

	@Override
	public Member memberUpdate(final Member member) {
		sqlSession.update(NAMESPACE + ".update", member);
		return sqlSession.selectOne(NAMESPACE + ".select", member);
	}

	@Override
	public void memberDelete(final Member member) {
		sqlSession.delete(NAMESPACE + ".delete", member);
	}
}
