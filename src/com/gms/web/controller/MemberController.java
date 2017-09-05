package com.gms.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gms.web.command.Command;
import com.gms.web.constants.Action;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.proxy.BlockHandler;
import com.gms.web.proxy.PageHandler;
import com.gms.web.proxy.PageProxy;
import com.gms.web.service.MemberService;
import com.gms.web.service.MemberServiceImpl;
import com.gms.web.util.DispatcherServlet;
import com.gms.web.util.ParamsIterator;
import com.gms.web.util.Separator;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController 진입");
		Separator.init(request);
		MemberBean member = new MemberBean();
		MemberService service = MemberServiceImpl.getInstance();
		Map<?,?> map = null;
		Command cmd = new Command();
		PageProxy pxy = new PageProxy(request);
		pxy.setPageSize(5);
		pxy.setBlockSize(5);

		switch (Separator.cmd.getAction()) {
		case Action.MOVE:
			DispatcherServlet.send(request, response);
			break;
		case Action.JOIN:
			System.out.println("====JOIN 진입====");
			map = ParamsIterator.execute(request);
			member.setUserId((String)map.get("join-userId"));
			member.setUserPw((String)map.get("join-userPw"));
			member.setName((String)map.get("join-name"));
			member.setSsn((String)map.get("join-birthday"));
			member.setEmail((String)map.get("join-email"));
			member.setPhone((String)map.get("join-phone"));
			member.setProfile("defaultImg.jpg");
			String[] subjects = ((String)map.get("subjects")).split(",");
			List<MajorBean> list = new ArrayList<>();
			MajorBean major = null;
			for (int i=0;i<subjects.length;i++) {
				major = new MajorBean();
				major.setUserId((String)map.get("join-userId"));
				major.setTitle((String)map.get("join-name"));
				major.setMajorId(String.valueOf((int)((Math.random()*50000)+10000)));
				major.setSubjId((String)subjects[i]);
				list.add(major);
			}
			Map<String, Object> tempMap = new HashMap<>();
			tempMap.put("member", member);
			tempMap.put("major", list);
			String page = service.addMember(tempMap);
			Separator.cmd.setPage(page);
			if (Separator.cmd.getPage().equals("main")) {
				Separator.cmd.setDir("common");
				Separator.cmd.process();
				DispatcherServlet.send(request, response);
			}
			else {
				Separator.cmd.setDir("common");
				Separator.cmd.setPage(Action.LOGIN_FAIL);
				Separator.cmd.process();
				DispatcherServlet.send(request, response);
			}
			break;
		case Action.LIST:
			System.out.println("memberList 진입");
			pxy.setTheNumberOfRows(Integer.parseInt(service.countMembers(cmd)));
			System.out.println("카운트~"+service.countMembers(cmd));
			pxy.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
			pxy.execute(BlockHandler.attr(pxy), service.getMembers(PageHandler.attr(pxy)));
			DispatcherServlet.send(request, response);
			break;
		case Action.UPDATE:
			cmd.setSearch(request.getParameter("id"));
			//service.modify(service.findById(cmd));
			DispatcherServlet.send(request, response);
			break;
		case Action.SEARCH:
			map = ParamsIterator.execute(request);
			
			cmd = PageHandler.attr(pxy);
			cmd.setPageNumber(request.getParameter("pageNumber"));
			cmd.setColumn("name");
			cmd.setSearch(String.valueOf(map.get("search")));
			cmd.setStartRow(PageHandler.attr(pxy).getStartRow());
			cmd.setEndRow(PageHandler.attr(pxy).getEndRow());
			String pageNum = cmd.getPageNumber();
			pxy.setTheNumberOfRows(Integer.parseInt(service.countMembers(cmd)));
			pxy.setPageNumber(Integer.parseInt(pageNum));
			pxy.execute(BlockHandler.attr(pxy), service.findByName(cmd));
			request.setAttribute("list", service.findByName(cmd));
			DispatcherServlet.send(request, response);
			break;
		case Action.DELETE:
			String path = request.getContextPath();
			System.out.println("넘어온 삭제할 아이디 : " + request.getParameter("id"));
			/*service.remove(request.getParameter("id"));*/
			response.sendRedirect(path + "/member.do?action=list&page=member_list&pageNumber=1");
			break;
		case Action.DETAIL:
			cmd.setSearch(request.getParameter("id"));
			request.setAttribute("student", service.findById(cmd));
			DispatcherServlet.send(request, response);
			break;
		default:
			break;
		}
	}
}