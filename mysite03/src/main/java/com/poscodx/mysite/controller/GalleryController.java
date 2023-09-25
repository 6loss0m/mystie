package com.poscodx.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.service.GalleryService;
import com.poscodx.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String main(Model model) {
		List<GalleryVo> list = galleryService.getImages();
		model.addAttribute("list", list);
		return "gallery/index";
	}
	// remove, add는 @Auth
	
	@Auth(Role="ADMIN")
	@RequestMapping("/upload")
	public String upload() {
		return "redirect:/gallery";
	}

	@Auth(Role="ADMIN")
	@RequestMapping("/remove")
	public String remove(@PathVariable Long no) {
		galleryService.removeImage(no);
		return "redirect:/gallery";
	}
}
