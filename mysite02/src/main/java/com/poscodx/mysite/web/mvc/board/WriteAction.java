package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class WriteAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo userVo = (UserVo) request.getSession().getAttribute("authUser");
		Long userNo = userVo.getNo();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Long boardNo = Long.parseLong(request.getParameter("board"));
		
		Long groupNo = -1L;
		Long orderNo = 1L;
		Long depth = 0L;

		int curPage = 1;
		
		// 게시글 작성
		if(boardNo == 0) {
			groupNo = new BoardDao().findMaxgNo();
			orderNo = 1L;
			depth = 1L;
		}
		// 답글 작성
		else {
			BoardVo parentVo = new BoardDao().findByNo(boardNo);
			groupNo = parentVo.getgNo();
			orderNo = parentVo.getoNo()+1;
			depth  = parentVo.getDepth()+1;
			curPage = Integer.parseInt(request.getParameter("p"));
			
			new BoardDao().updateNo(groupNo, parentVo.getoNo());
		}
		
		BoardVo vo = new BoardVo(); 
		vo.setUserNo(userNo);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setgNo(groupNo);
		vo.setoNo(orderNo);
		vo.setDepth(depth);
		
		new BoardDao().insert(vo);

		response.sendRedirect("/mysite02/board?p="+curPage);
	}

}