package com.sjh.web.board.service;

import java.util.List;

import com.sjh.web.Pagination;
import com.sjh.web.board.Board;

public interface IBoardService {

	Board boardSearch(String bId);
	Board boardView(String bId);
	List<Board> boardListUp(Pagination pagination);
	void boardWrite(Board board);
	void boardModify(Board board);
	void boardRemove(String bId);
	int getBoardCnt();
}