package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.poscodx.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*	Q. Handler가 Obejct로 오는 이유 handler를 제외한 다른 객체 존재 controller에 mapping된 게 아니면
		 * Default Servlet Handler Method가 존재 존재하지 않는 sevlet이면 default로 결정되어 반환
		 */

		// 1. handler 종류 확인
		if (!(handler instanceof HandlerMethod)) {
			// DefaultServletHandler가 처리하는 경우(정적 자원, /assets/**)
			return true;
		}
		// 여기부터는 무조건 HendlerMethod
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3-1. Handler Method의 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//////////////////// 과제 ////////////////////
		// 3-2. Handler Method의 @Auth가 없는 경우, Type(Class)의 @Auth 가져오기
		if(auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		// 4. @Auth 가 없는 경우
		if (auth == null) {
			return true; // handler에 @Auth를 안담겨 온경우
		}

		// 5. @Auth 가 붙어 있는 경우, 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) { // 인증이 안되어 있는 경우
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		//////////////////// 과제 ////////////////////
		// 6. 권한(Authorization) 체크를 위해서 @Auth의 Role 가져오기("USER", "ADMIN")
		String role = auth.Role(); // @에 붙어있는 Role
		// Role 추가하기
		String authUserRole = authUser.getRole();
		// 해당 페이지가 ADMIN 권한이고, 세션에 저장되어 있는 회원이 USER인 경우
		if(role.equals("ADMIN") && authUserRole.equals("USER")) {
			response.sendRedirect(request.getContextPath() + "/");
		}
		System.out.println(role + " : "+authUserRole);
		
		// 7. 인증 확인 !!!
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
