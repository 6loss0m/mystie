package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class WriteFormAction implements Action {

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
		
		if(request.getParameter("n") == null) {
			WebUtil.forward("board/write", request, response);
		}
		else {
			Long no = Long.parseLong(request.getParameter("n"));
			int curPage = Integer.parseInt(request.getParameter("p"));
			
			request
				.getRequestDispatcher("/WEB-INF/views/board/write.jsp?n=" + no + "&p=" + curPage)
				.forward(request, response);
		}
	}

}
