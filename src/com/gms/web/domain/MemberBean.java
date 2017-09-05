package com.gms.web.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId, userPw, name, ssn, regdate, email, major, subject, phone, profile;
}
