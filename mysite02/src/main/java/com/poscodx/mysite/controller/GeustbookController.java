package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.GuestBookDao;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.GuestBookVo;
import com.poscodx.mysite.vo.UserVo;

public class GeustbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("a"); // action 제어
		if ("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String contents = request.getParameter("content");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);

			System.out.println(vo);

			new GuestBookDao().insert(vo);

			response.sendRedirect("/mysite02/guestbook");
		} else if ("deleteForm".equals(action)) {
			Long no = (Long) request.getAttribute("no");

			request.setAttribute("no", no);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(action)) {

			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");

			System.out.println("[delete action] no : " + no + ", password : " + password);

			new GuestBookDao().deleteByNo(no, password);

			response.sendRedirect("/mysite02/guestbook");

		} else {
			List<GuestBookVo> list = new GuestBookDao().findAll();

			request.setAttribute("list", list);

			for (GuestBookVo vo : list) {
				System.out.println(vo);
			}

			request.getRequestDispatcher("/WEB-INF/views/guestbook/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
