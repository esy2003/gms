package com.gms.web.domain;

import java.io.Serializable;

import lombok.Data;
@Data
public class GradeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String gradeSeq, score, examDate, subjId, userId;
}
