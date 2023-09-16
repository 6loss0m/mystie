package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.PageVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		/* Searching */
		String search = "";
		if (request.getParameter("k") != null) {
			search = request.getParameter("k");
		}

		/* Paging */
		int pageSize = 5;
		int listSize = new BoardDao().totalBoardCount(search);
		int totalPage = listSize / pageSize;
		if (listSize % pageSize != 0) {
			totalPage++;
		}

		int curPage = Integer.parseInt(request.getParameter("p"));
		int startPage = (curPage - 1) / pageSize * pageSize + 1;
		int endPage = startPage + pageSize - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		/* List Making by Page No */
		List<BoardVo> list = new BoardDao().findAll((curPage - 1) * 5, search);
		request.setAttribute("list", list);
		request.setAttribute("search", search);

		PageVo pageVo = new PageVo();
		pageVo.setPageSize(pageSize);
		pageVo.setTotalPage(totalPage);
		pageVo.setStartPage(startPage);
		pageVo.setEndPage(endPage);
		pageVo.setCurPage(curPage);

		request.setAttribute("pageVo", pageVo);

		WebUtil.forward("board/list", request, response);
	}

}
