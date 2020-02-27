package com.sjh.web.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjh.web.Pagination;
import com.sjh.web.board.Board;
import com.sjh.web.board.DAO.BoardDAO;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	BoardDAO dao;
	
	@Override
	public Board boardSearch(String bId) {
		return dao.boardSelect(bId);
	}
	
	@Override
	public Board boardView(String bId) {
		Board b = dao.boardSelect(bId);
		dao.hitUp(bId);
		return b;
	}
	
	@Override
	public List<Board> boardListUp(Pagination pagination) {
		return dao.boardList(pagination);
	}
	
	@Override
	public void boardWrite(Board board) {
		dao.boardInsert(board);
	}
	
	@Override
	public void boardModify(Board board) {
		dao.boardUpdate(board);
	}
	
	@Override
	public void boardRemove(String bId) {
		dao.boardDelete(bId);
	}
	
	@Override
	public int getBoardCnt() {
		return dao.boardCnt();
	}
}
