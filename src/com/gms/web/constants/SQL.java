package com.gms.web.constants;

public class SQL {
	public static final String MEMBER_INSERT = String.format("INSERT INTO %s( %s, %s, %s, %s, %s, %s, %s, %s)VALUES(?,?,?,?,now(),?,?,?)",
			DB.TABLE_MEMBER, DB.MEM_ID, DB.MEM_NAME, DB.MEM_PW, DB.MEM_SSN, DB.MEM_REGDATE, DB.MEM_PHONE, DB.MEM_EMAIL, DB.MEM_PROFILE);
	public static final String BOARD_INSERT = String.format("INSERT INTO %s(%s,%s, %s, %s, %s, %s) VALUES(article_seq.nextval,?,?,?,0,SYSDATE)",
			DB.TABLE_BOARD, DB.ARTICLE_SEQ ,DB.MEM_ID, DB.TITLE, DB.BOARD_CONTENT, DB.BOARD_HITCOUNT, DB.MEM_REGDATE);
	public static final String MEMBER_LIST = String.format("SELECT * FROM %s", DB.TABLE_MEMBER);
	public static final String BOARD_LIST = String.format("SELECT * FROM %s", DB.TABLE_BOARD);
	public static final String MEMBER_FINDBYNAME = String.format("SELECT * FROM %s WHERE %s=?", DB.TABLE_MEMBER, DB.MEM_NAME);
	public static final String BOARD_FINDBYSEQ = String.format("SELECT * FROM %s WHERE %s=?", DB.TABLE_BOARD, DB.ARTICLE_SEQ);
	public static final String MEMBER_FINDBYID = String.format("SELECT * FROM %s WHERE %s=?", DB.TABLE_MEMBER, DB.MEM_ID);
	public static final String BOARD_FINDBYID = String.format("SELECT * FROM %s WHERE %s=?", DB.TABLE_BOARD, DB.MEM_ID);
	public static final String MEMBER_COUNT = String.format("SELECT COUNT(*) AS count FROM %s", DB.TABLE_MEMBER);
	public static final String BOARD_COUNT = String.format("SELECT COUNT(*) AS count FROM %s", DB.TABLE_BOARD);
	public static final String MEMBER_UPDATE = String.format("UPDATE %s SET %s=? WHERE %s = ?", DB.TABLE_MEMBER, DB.MEM_PW, DB.MEM_ID);
	public static final String BOARD_UPDATE = String.format("UPDATE %s SET %s=?, %s=? WHERE %s = ?", DB.TABLE_BOARD, DB.TITLE, DB.BOARD_CONTENT, DB.ARTICLE_SEQ);
	public static final String MEMBER_DELETE = String.format("DELETE FROM %s WHERE %s=?", DB.TABLE_MEMBER, DB.MEM_ID);
	public static final String BOARD_DELETE = String.format("DELETE FROM %s WHERE %s=?", DB.TABLE_BOARD, DB.ARTICLE_SEQ);
	public static final String MAJOR_INSERT = String.format("INSERT INTO Major(major_id, title, userId, subj_id) VALUES(?,?,?,?)");

	/*	public static final String STUDENT_LIST = 
			" select t2.* " + 
			" from (select rownum seq, t.* " + 
				" from (select * " + 
					" from student " + 
					" order by num desc) t ) t2 " + 
			" where t2.seq between ? and ? ";*/

	public static final String STUDENT_LIST = 
			" select c.*, num from "
			         + " (select @Rnum := @Rnum + 1 num, t.* "
			          + " from student t,(select @rnum := 0) b) c "
			          + "  where num between ? and ? " ;
	public static final String STUDENT_COUNT = "SELECT COUNT(*) AS count FROM student where name like ?";
	public static final String STUDENT_SEARCH = String.format("SELECT * FROM Student where %s like ?", "name");
	public static final String STUDENT_FINDBYID = "SELECT * FROM Student where userId like ?";
}
