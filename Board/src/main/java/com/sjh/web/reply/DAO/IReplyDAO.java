package com.sjh.web.reply.DAO;

import java.util.List;

import com.sjh.web.Pagination;
import com.sjh.web.reply.Reply;

public interface IReplyDAO {
	
	List<Reply> replyList(String bId, Pagination pagination);
	Reply replySerach(String rId);
	void replyInsert(Reply reply);
	void replyUpdate(Reply reply);
	void replyDelete(String rId);
	int replyCnt(String bId);
}