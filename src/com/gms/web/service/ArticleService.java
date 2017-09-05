package com.gms.web.service;

import java.util.List;

import com.gms.web.domain.ArticleBean;

public interface ArticleService {
	public String count();
    public String write(ArticleBean bean);
    public List<ArticleBean> list();
    public List<ArticleBean> findById(String id);
    public ArticleBean findbySeq(String seq);
    public String modify(ArticleBean bean);
    public String remove(String seq);
   
}