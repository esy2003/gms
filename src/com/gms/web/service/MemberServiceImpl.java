package com.gms.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.dao.MemberDAOImpl;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;
import com.gms.web.service.MemberService;

public class MemberServiceImpl implements MemberService {
	public static MemberServiceImpl getInstance() {
		return new MemberServiceImpl();
	}
	MemberBean member;
	//생성자 처리가 이 문제의 핵심(아래)
	private MemberServiceImpl() {
	}
	@Override
	public String addMember(Map<String, Object> map) {
		System.out.println("MemberService 진입");
		MemberBean m = (MemberBean)map.get("member");
		System.out.println("넘어온 회원 정보 : " + m.toString());
		@SuppressWarnings("unchecked")
		List<MajorBean> list = (List<MajorBean>)map.get("major");
		System.out.println("넘어온 수강 과목 : " + list);
		String result = "";
		if (MemberDAOImpl.getInstance().insert(map).equals("1")) {
			result = "main";
		}
		else if (MemberDAOImpl.getInstance().insert(map).equals("0")) {
			result = "login_fail";
		}
		return result;
	}
	@Override
	public List<StudentBean> getMembers(Command cmd) {
		return MemberDAOImpl.getInstance().selectAll(cmd);
	}

	@Override
	public String countMembers(Command cmd) {
		return MemberDAOImpl.getInstance().count(cmd);
	}
	
	@Override
	public List<StudentBean> findByName(Command cmd) {
		return MemberDAOImpl.getInstance().selectByName(cmd);
	}

	@Override
	public StudentBean findById(Command cmd) {
		return MemberDAOImpl.getInstance().selectById(cmd);
	}
	
	@Override
	public String modify(MemberBean bean) {
		return (MemberDAOImpl.getInstance().update(bean).equals("1"))?"ok":"no";
	}
	@Override
	public String remove(Command cmd) {
		return (MemberDAOImpl.getInstance().delete(cmd).equals("1"))?"ok":"no";
	}
    @Override
    public Map<String, Object> login(MemberBean bean) {
    	Command cmd = new Command();
    	Map<String, Object> map = new HashMap<>();
    	cmd.setSearch(bean.getUserId());
    	MemberBean m = MemberDAOImpl.getInstance().login(cmd);
    	String page=(m!=null)?
    			(bean.getUserPw().equals(m.getUserPw()))?
    					"main":"login_fail":"join";
    	map.put("page", page);
    	map.put("user", m);
    	return map;
    }
}
