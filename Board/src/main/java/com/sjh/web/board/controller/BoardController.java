package com.sjh.web.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sjh.web.Pagination;
import com.sjh.web.board.Board;
import com.sjh.web.board.service.BoardService;
import com.sjh.web.reply.Reply;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	// write
	@RequestMapping("/writeForm")
	public String writeForm(HttpSession session, Board board) {
		
		return "/board/writeForm";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writeWork(Board board) {
		
		service.boardWrite(board);
		
		return "redirect:/board/list";
	}
	
	// content
	@RequestMapping("/content")
	public ModelAndView contentView(@RequestParam("bId") String bId) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("board", service.boardView(bId));
		mav.addObject("reply", new Reply());
		mav.setViewName("/board/content");
		
		return mav;
	}
	
	// modify
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(@RequestParam("bId") String bId, Board board) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		Board b = service.boardSearch(bId);
		
		mav.addObject("board", b);
		mav.setViewName("/board/modifyForm");

		return mav;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyWork(Board board, HttpSession session) {
		
		service.boardModify(board);
		
		return "redirect:/board/list";
	}
	
	//list
	@RequestMapping("/list")
	public ModelAndView listView(Board board
			, @RequestParam(required = false, defaultValue = "1") int page
			, @RequestParam(required = false, defaultValue = "1") int range) {

		// 전체리스트 개수
        int listCnt = service.getBoardCnt();
        
        // 페이징 설정
        Pagination pagination = new Pagination();
        pagination.listPageInfo(page, range, listCnt);
        
        // 전체 리스트
     	List<Board> list = service.boardListUp(pagination);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("listCnt",listCnt);
		mav.addObject("pagination", pagination);
		mav.setViewName("/board/list");
		
		return mav;
	}
	
	// remove 
	@RequestMapping("/remove")
	public String removeWork(@RequestParam("bId") String bId) {
		
		service.boardRemove(bId);
		
		return "redirect:/board/list;";
	}
	
	
}
