package com.poscodx.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardServie;
	
	@RequestMapping(value="")
	public String main(
			@RequestParam(value="p", required = true, defaultValue = "1") int page,
			@RequestParam(value="kwd", required = true, defaultValue = "") String keyword,
			Model model) {
		List<BoardVo> list = boardServie.getBoardsList(page, keyword);
		model.addAttribute("list", list);
		return "board/list";
	}
	
}
