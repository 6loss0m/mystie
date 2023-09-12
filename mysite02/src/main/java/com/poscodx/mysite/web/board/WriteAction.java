package com.poscodx.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		//////////////////////////////////////////////////////

		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String no = request.getParameter("no");
		BoardVo vo = new BoardVo();
		BoardDao dao = new BoardDao();
//		System.out.println("title : " + title + ", content : " + contents + ", no : " + no);
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(authUser.getNo());

		if (no.equals("")) {
			vo.setgNo(dao.findMaxgNo());
			dao.insertBoard(vo);
		} else {
			vo.setgNo(dao.findByNo(Long.parseLong(no)).getgNo());
			vo.setoNo(dao.findByNo(Long.parseLong(no)).getoNo() + 1);
			vo.setDepth(dao.findByNo(Long.parseLong(no)).getDepth() + 1);
			dao.insertReply(vo);
		}

		response.sendRedirect(request.getContextPath() + "/board");
	}
}
