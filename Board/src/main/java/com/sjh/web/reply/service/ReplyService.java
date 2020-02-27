package com.sjh.web.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjh.web.Pagination;
import com.sjh.web.reply.Reply;
import com.sjh.web.reply.DAO.ReplyDAO;

@Service
public class ReplyService implements IReplyService{

	@Autowired
	ReplyDAO dao;

	public List<Reply> replyListUp(String bId, Pagination pagination) {
		List<Reply> rList = dao.replyList(bId, pagination);
		return rList;
	}

	@Override
	public void replyWrite(Reply reply) {
		dao.replyInsert(reply);
	}

	@Override
	public void replyUpdate(Reply reply) {
		dao.replyUpdate(reply);
	}

	@Override
	public void replyRemove(String rId) {
		dao.replyDelete(rId);
	}

	@Override
	public int getReplyCnt(String bId) {
		return dao.replyCnt(bId);
	}
}
