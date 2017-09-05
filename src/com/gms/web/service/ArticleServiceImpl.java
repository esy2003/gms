package com.gms.web.service;

import java.util.List;

import com.gms.web.dao.ArticleDAO;
import com.gms.web.dao.ArticleDAOImpl;
import com.gms.web.domain.ArticleBean;
import com.gms.web.domain.MemberBean;

public class ArticleServiceImpl implements ArticleService {
	public static ArticleServiceImpl getInstance() {
		return new ArticleServiceImpl();
	}
	ArticleBean bean;
	private ArticleServiceImpl() {
	}
	@Override
	public String count() {
		return ArticleDAOImpl.getInstance().count();
	}

	@Override
	public String write(ArticleBean bean) {
		return ArticleDAOImpl.getInstance().insert(bean).equals("1")?"게시글작성완료":"게시글작성오류";
	}

	@Override
	public List<ArticleBean> list() {
		return ArticleDAOImpl.getInstance().list();
	}

	@Override
	public List<ArticleBean> findById(String id) {
		return ArticleDAOImpl.getInstance().findById(id);
	}

	@Override
	public ArticleBean findbySeq(String seq) {
		return ArticleDAOImpl.getInstance().findbySeq(seq);
	}

	@Override
	public String modify(ArticleBean bean) {
		return ArticleDAOImpl.getInstance().update(bean).equals("1")?"수정되었습니다":"수정 실패";
	}

	@Override
	public String remove(String seq) {
		return ArticleDAOImpl.getInstance().delete(seq).equals("1")?"삭제되었습니다":"삭제실패";
	}

}
