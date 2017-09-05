package com.gms.web.dao;

import java.util.List;

import com.gms.web.domain.ArticleBean;
import com.gms.web.domain.MemberBean;

public interface ArticleDAO {
	public String count();
    public String insert(ArticleBean bean);
    public List<ArticleBean> list();
    public List<ArticleBean> findById(String id);
    public ArticleBean findbySeq(String seq);
    public String update(ArticleBean bean);
    public String delete(String seq);
}