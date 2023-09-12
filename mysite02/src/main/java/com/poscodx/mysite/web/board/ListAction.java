package com.poscodx.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		List<BoardVo> list = null;

		Long no = -1L;
		if (authUser != null) {
			no = authUser.getNo();
		}
		
		String keyword = request.getParameter("kwd");
		if(keyword == null) {
			list = new BoardDao().findAll();
		}else {
			list = new BoardDao().findByKeyword(keyword);
		}
		request.setAttribute("list", list);
		request.setAttribute("user_no", no);
//		for (BoardVo vo : list) {
//			System.out.println("[Board List] " + vo);
//		}

		WebUtil.forward("board/list", request, response);
	}

}
