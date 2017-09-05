package com.gms.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
	public String getNow() {
		return new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss").format(new Date());
		//위 로직은 현재 연월일 시간 뽑아내는 로직, 중간의 a는 12시간으로 나누는것. 없으면 24시간으로 나옴
	}
}
