package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class ModifyAction implements Action {

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

		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long no = Long.parseLong(request.getParameter("n"));
		int curPage = Integer.parseInt(request.getParameter("p"));

//		System.out.println("[Modify] title : " + title + ", contents : " + contents + ", no : " + no);

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setNo(no);

		new BoardDao().updateBoard(vo);

		response.sendRedirect("board?a=view&n=" + no + "&p=" + curPage);
	}

}
