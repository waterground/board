package com.sjh.web.reply.service;

import java.util.List;

import com.sjh.web.Pagination;
import com.sjh.web.reply.Reply;

public interface IReplyService {
	
	List<Reply> replyListUp(String bId, Pagination pagination);
	void replyWrite(Reply reply);
	void replyUpdate(Reply reply);
	void replyRemove(String rId);
	int getReplyCnt(String bId);
}