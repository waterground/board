package com.sjh.web.board.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sjh.web.Pagination;
import com.sjh.web.board.Board;

@Repository
public class BoardDAO implements IBoardDAO {
	
	private static final String NAMESPACE = "com.sjh.web.mappers.board.BoardMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Board> boardList(final Pagination pagination) {
		return sqlSession.selectList(NAMESPACE + ".listUp", pagination);
	}
	
	public Board boardSelect(final String bId) {
		return sqlSession.selectOne(NAMESPACE + ".select", bId);
	}
	
	@Override
	public void boardUpdate(final Board board) {
		sqlSession.update(NAMESPACE + ".update", board);
	}
	
	@Override
	public void boardDelete(final String bId) {
		sqlSession.delete(NAMESPACE + ".delete", bId);
	}
	
	@Override
	public void boardInsert(final Board board) {
		sqlSession.insert(NAMESPACE + ".create", board);
	}
	
	@Override
	public void hitUp(final String bId) {
		sqlSession.update(NAMESPACE + ".hitUp", bId);
	}
	
	@Override
	public int boardCnt() {
		return sqlSession.selectOne(NAMESPACE +".count");
	}
}
