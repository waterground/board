package com.sjh.web.board.DAO;

import java.util.List;

import com.sjh.web.Pagination;
import com.sjh.web.board.Board;

public interface IBoardDAO {

	List<Board> boardList(Pagination pagination);
	Board boardSelect(String bId);
	int boardUpdate(Board board);
	int boardDelete(String bId);
	int boardInsert(Board board);
	int hitUp(String bId);
	int boardCnt();
}