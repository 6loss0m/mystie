package com.poscodx.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Access Control(접근제어)
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user?a=loginform");
			return;
		}
		/////////////////////////////////////////////////////////////////////
		
		Long no = Long.parseLong(request.getParameter("no"));
//		System.out.println("---"+no+"---");
		new BoardDao().deleteByNo(no);
		
		response.sendRedirect(request.getContextPath() + "/board");

	}

}
