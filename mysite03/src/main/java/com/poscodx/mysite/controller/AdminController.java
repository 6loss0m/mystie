package com.poscodx.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.security.AuthUser;
import com.poscodx.mysite.service.FileUploadService;
import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;
import com.poscodx.mysite.vo.UserVo;

@Auth(Role="ADMIN")
@Controller
@RequestMapping("/admin")
// 모든 controller가 인증을 받고 들어올 수 있도록
public class AdminController {

	/*
	@Autowired
	private SiteVo site;
	autowired에서 scanning 하고 있을 때는 없다가, 나중에 siteVo에서 주입할 때 에러가 발생
	*/
	
	@Autowired
	private ServletContext servletContext; // Controller 생길 때 만들어서 같이 넣어줌.
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.getSite(1L);
		model.addAttribute("siteVo", siteVo);
		System.out.println(siteVo);
		return "admin/main";
	}
	
	@RequestMapping("/main")
	public String main2(Model model) {
		SiteVo siteVo = siteService.getSite(1L);
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	@RequestMapping(value="/main/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {		
		SiteVo siteVo = siteService.getSite(1L);
		model.addAttribute("siteVo", siteVo);
		return "admin/";
	}
	
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String update(
			@AuthUser UserVo authUser,
			SiteVo vo,
			@RequestParam("f") MultipartFile file
			) {
		
		if(!file.isEmpty()) {
			String url = fileUploadService.restore(file);
			vo.setProfile(url);
		}
		SiteVo site = applicationContext.getBean(SiteVo.class);
		
		siteService.updateSite(vo);
		servletContext.setAttribute("siteVo", vo);
		
//		site.setTitle(vo.getTitle());
//		site.setWelcome(vo.getWelcome());
//		site.setProfile(vo.getProfile());
//		site.setDescription(vo.getDescription());
		BeanUtils.copyProperties(vo, site);
		
		
		return "redirect:/admin/";
	}
	
}
