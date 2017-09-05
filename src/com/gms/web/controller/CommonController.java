package com.gms.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gms.web.constants.Action;
import com.gms.web.domain.MemberBean;
import com.gms.web.service.MemberService;
import com.gms.web.service.MemberServiceImpl;
import com.gms.web.util.DispatcherServlet;
import com.gms.web.util.Separator;

@WebServlet({"/home.do", "/common.do"})
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		MemberBean bean = null;
		System.out.println("commonController get 진입");
		Separator.init(request);
		switch (Separator.cmd.getAction()) {
		case Action.MOVE:
			DispatcherServlet.send(request, response);
			break;
		case Action.LOGIN:
			MemberService service = MemberServiceImpl.getInstance();
			bean = new MemberBean();
			bean.setUserId(request.getParameter("input_id"));
			bean.setUserPw(request.getParameter("input_pass"));
			
			Map<String, Object> map=service.login(bean);
			System.out.println(map.get("page"));
			if (map.get("page").equals("main")) {
				session.setAttribute("user", map.get("user"));
			}
			Separator.cmd.setPage(String.valueOf((map.get("page"))));
			Separator.cmd.process();//위에서 셋페이지에 담기만하면 담긴채로 끝나므로
			//프로세스를 재실행 해줘야 한다.그래야 view(경로)가 셋페이지를 가지고 재설정됌
			DispatcherServlet.send(request, response);
			break;
		case Action.LOGOUT:
			session.invalidate();
			DispatcherServlet.send(request, response);
			break;
		default:
			break;
		}
	}
}
