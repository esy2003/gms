package com.gms.web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gms.web.constants.DB;
import com.gms.web.constants.SQL;
import com.gms.web.constants.Vendor;
import com.gms.web.domain.ArticleBean;
import com.gms.web.factory.DatabaseFactory;

public class ArticleDAOImpl implements ArticleDAO {
	//public static ArticleDAOImpl instance = new ArticleDAOImpl(); -> 하면 게터세터 생성가능.
	//게터 세터 생성 후에 세터 지우고 게터안에 클래스포네임 넣어줌
	public static ArticleDAOImpl getInstance() {
		return new ArticleDAOImpl();
	}
	ArticleBean bean;
	private ArticleDAOImpl() {}
	
	@Override
	public String count() {
		int count = 0;
		try {
			ResultSet rs = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD)
					.getConnection().prepareStatement(SQL.BOARD_COUNT).executeQuery();
			if (rs.next()) {
				count = Integer.parseInt(rs.getString("count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(count);
	}

	@Override
	public String insert(ArticleBean bean) {
		int rs = 0;
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.ORACLE,  DB.USERNAME, DB.PASSWORD).getConnection()
					.prepareStatement(SQL.BOARD_INSERT);
			pstmt.setString(1, bean.getUserId());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getContent());
			rs = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(rs);
	}

	@Override
	public List<ArticleBean> list() {
		List<ArticleBean> list = new ArrayList<>();
		try {
			ResultSet rs = DatabaseFactory.createDatabase(
					Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection()
					.prepareStatement(SQL.BOARD_LIST).executeQuery();
			ArticleBean bean = null;
			while(rs.next()) {
				bean = new ArticleBean();
				bean.setArticleSeq(rs.getInt(DB.ARTICLE_SEQ));
				bean.setUserId(rs.getString(DB.MEM_ID));
				bean.setTitle(rs.getString(DB.TITLE));
				bean.setContent(rs.getString(DB.BOARD_CONTENT));
				bean.setHitCount(rs.getInt(DB.BOARD_HITCOUNT));
				bean.setRegdate(rs.getString(DB.MEM_REGDATE));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ArticleBean> findById(String id) {
		List<ArticleBean> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD)
					.getConnection().prepareStatement(SQL.BOARD_FINDBYID);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			ArticleBean bean = null;
			while(rs.next()) {
				bean = new ArticleBean();
				bean.setArticleSeq(Integer.parseInt(rs.getString(DB.ARTICLE_SEQ)));
				bean.setUserId(rs.getString(DB.MEM_ID));
				bean.setTitle(rs.getString(DB.TITLE));
				bean.setContent(rs.getString(DB.BOARD_CONTENT));
				bean.setHitCount(rs.getInt(DB.BOARD_HITCOUNT));
				bean.setRegdate(rs.getString(DB.MEM_REGDATE));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArticleBean findbySeq(String seq) {
		try {
			bean = new ArticleBean();
			PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD)
					.getConnection().prepareStatement(SQL.BOARD_FINDBYSEQ);
			pstmt.setString(1, seq);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setArticleSeq(Integer.parseInt(rs.getString(DB.ARTICLE_SEQ)));
				bean.setUserId(rs.getString(DB.MEM_ID));
				bean.setTitle(rs.getString(DB.TITLE));
				bean.setContent(rs.getString(DB.BOARD_CONTENT));
				bean.setHitCount(rs.getInt(DB.BOARD_HITCOUNT));
				bean.setRegdate(rs.getString(DB.MEM_REGDATE));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public String update(ArticleBean bean) {
		
		ArticleBean temp = findbySeq(String.valueOf(bean.getArticleSeq()));
		// 위는 빈으로 들어온 시퀀스값을 찾아서 그녀석의 겟타이틀 값이 원래 입력받았던 값과 같으므로 빈의 겟타이틀이 널이면 그걸로 가져다 넣어준다
		String title = (bean.getTitle().equals(""))? temp.getTitle():bean.getTitle();
		// 위는 받아온 타이틀이 널이면 템프 안에 findBySeq 로 담아낸 녀석의 타이틀(기존의 것)을 넣고
		// 널이 아니면 받아온 빈의 타이틀을 그대로 넣는다는 구문. 아래 컨텐트도 동일
		String content = (bean.getContent().equals(""))? temp.getContent():bean.getContent();
		String rs = "";
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD)
					.getConnection().prepareStatement(SQL.BOARD_UPDATE);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bean.getArticleSeq());
			rs = String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(rs);
	}

	@Override
	public String delete(String seq) {
		int rs = 0;
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD)
					.getConnection().prepareStatement(SQL.BOARD_DELETE);
			pstmt.setString(1, seq);
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(rs);
	}
}
