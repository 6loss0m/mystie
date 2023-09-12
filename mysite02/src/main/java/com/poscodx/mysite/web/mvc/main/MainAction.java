package com.poscodx.mysite.web.mvc.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.web.mvc.Action;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		request.getSession().setAttribute("authUser", response);
		request
		.getRequestDispatcher("/WEB-INF/views/main/index.jsp")
		.forward(request, response);
	}

}
