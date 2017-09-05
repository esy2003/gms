package com.gms.web.domain;

import java.io.Serializable;

import lombok.Data;
@Data
public class ArticleBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId, title, content, regdate;
	private int articleSeq, hitCount;
}
