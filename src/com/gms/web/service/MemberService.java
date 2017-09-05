package com.gms.web.service;

import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;

public interface MemberService {
	public String addMember(Map<String, Object> map);
	public List<StudentBean> getMembers(Command cmd);
	public String countMembers(Command cmd);
	public StudentBean findById(Command cmd);
	public List<StudentBean> findByName(Command cmd);
	public String remove(Command cmd);
	public String modify(MemberBean member);
	public Map<String, Object> login(MemberBean member);
}
