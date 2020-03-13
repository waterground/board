package com.sjh.web.reply.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sjh.web.Pagination;
import com.sjh.web.reply.Reply;

@Repository
public class ReplyDAO implements IReplyDAO {

	private static final String NAMESPACE = "com.sjh.web.mappers.reply.ReplyMapper";

	private final SqlSession sqlSession;
	
	@Inject 
	ReplyDAO(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Reply> replyList(final String bId, final Pagination pagination) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bId", bId);
		paramMap.put("pagination", pagination);
		
		return sqlSession.selectList(NAMESPACE + ".listUp", paramMap);
	}
	
	@Override
	public Reply replySerach(final String rId) {
		return sqlSession.selectOne(NAMESPACE + ".select", rId);
	}

	@Override
	public void replyInsert(final Reply reply) {
		sqlSession.insert(NAMESPACE + ".create", reply);
	}

	@Override
	public void replyUpdate(final Reply reply) {
		sqlSession.update(NAMESPACE + ".update", reply);
	}

	@Override
	public void replyDelete(final String rId) {
		sqlSession.delete(NAMESPACE + ".delete", rId);
	}
	
	@Override
	public int replyCnt(final String bId) {
		return sqlSession.selectOne(NAMESPACE + ".count", bId);
	}
}
