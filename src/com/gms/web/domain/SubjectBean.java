package com.gms.web.domain;

import java.io.Serializable;

import lombok.Data;
@Data
public class SubjectBean implements Serializable {
	private String subjId, title, majorId;

	private static final long serialVersionUID = 1L;

}
