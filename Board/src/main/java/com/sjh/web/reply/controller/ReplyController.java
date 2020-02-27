package com.sjh.web.reply.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjh.web.Pagination;
import com.sjh.web.reply.Reply;
import com.sjh.web.reply.service.IReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	IReplyService service;
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ResponseEntity<String> write(@RequestBody Reply reply, HttpSession session) {
		
		ResponseEntity<String> entity = null;

		try {
			service.replyWrite(reply);
			entity = new ResponseEntity<String>("register Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
		
	}
	
	@RequestMapping(value = "/list/{page}", method = RequestMethod.POST)
	public Map<String, Object> list(HttpServletRequest request, @RequestParam("bId") String bId
			, @PathVariable("page") int page) {
		
		// 전체 댓글 개수
		int listCnt = service.getReplyCnt(bId);
		
		// 페이징 설정
		Pagination pagination = new Pagination();
		pagination.setListSize(8);
		pagination.setRangeSize(5);
		pagination.replyPageInfo(page, listCnt);
		
		// 전체 댓글 리스트
		Map<String, Object> map = new HashMap<>();
		map.put("reply", service.replyListUp(bId, pagination));
		map.put("pagination", pagination);
		
		return map;
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<String> modify(@RequestBody Reply reply) {
		ResponseEntity<String> entity = null;
		
		try {
			service.replyUpdate(reply);
			entity = new ResponseEntity<String>("modify Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<String> remove(@RequestBody Reply reply) {
		ResponseEntity<String> entity = null;
		
		try {
			service.replyRemove(String.valueOf(reply.getrId()));
			entity = new ResponseEntity<String>("remove Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

}
