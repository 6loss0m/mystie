package com.poscodx.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

public class EncodingFilter extends HttpFilter implements Filter {
	/* 관심의 분리 */

	private static final long serialVersionUID = 1L;
	private String encoding;

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* requset 처리 */
		// boolean existJSessionId = false; session 처리시 사용
		request.setCharacterEncoding("UTF-8");

		chain.doFilter(request, response);

		/* response 처리 */
	}

	public void destroy() {
	}

}
