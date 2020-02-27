package com.sjh.web.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sjh.web.member.Member;
import com.sjh.web.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	// Join
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {
		return "/member/joinForm";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinWork(Member member) {
		
		service.memberRegister(member);
		
		return "/member/joinOk";
	}
	
	
	// Login
	@RequestMapping("/loginForm")
	public String loginForm(Member member) {
		return "/member/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView loginWork(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member mem = service.memberSearch(member);
		
		ModelAndView mav = new ModelAndView();
		
		if(mem == null) {
			mav.addObject("canLogin", false);
			mav.setViewName("/member/loginForm");
		}else {
			session.setAttribute("member", mem);
			mav.setViewName("redirect:/");
		}

		return mav;
	}
	
	// Modify
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(HttpSession session) {
		
		Member mem = (Member)session.getAttribute("member");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", service.memberSearch(mem));
		mav.setViewName("/member/modifyForm");
		
		return mav;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyWork(Member member, HttpSession session) {
		
		Member mem = service.memberModify(member);
		
		session.setAttribute("member", mem);
		
		return "/member/modifyOk";
	}
	
	// Logout
	@RequestMapping("/logout")
	public String logoutWork(HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// Remove
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		mav.addObject("member", member);
		mav.setViewName("/member/removeForm");
		
		return mav;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ModelAndView removeWork(Member member, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		Member loginMem = (Member)session.getAttribute("member");
		
		if(loginMem.getmPw().equals(member.getmPw())) {
			session.invalidate();
			service.memberRemove(member);
			mav.setViewName("redirect:/");
		}else {
			mav.addObject("canRemove", false);
			mav.setViewName("/member/removeForm");
		}
		
		return mav;
	}
	
}
