<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
public static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
public static final String MYSQL_DRIVER = "";
public static final String MYSQL_URL = "";
public static final String MSSQL_DRIVER = "";
public static final String MSSQL_URL = "";
public static final String MARIADB_DRIVER = "";
public static final String MARIA_URL = "";
public static final String DB2_DRIVER = "";
public static final String DB2_URL = "";
public static final String USERNAME = "hanbit";
public static final String PASSWORD = "hanbit";
public static final String TABLE_MEMBER = "Member";
public static final String TABLE_BOARD = "Board";
public static final String MEM_ID = "userId";
public static final String MEM_PW = "userPw";
public static final String MEM_SSN = "ssn";
public static final String MEM_NAME = "name";
public static final int GET_HITCOUNT = 0;
public static final String MEM_REGDATE = "regdate";
public static final String BOARD_TITLE = "title";
public static final String BOARD_CONTENT = "content";
public static final String BOARD_HITCOUNT = "hitcount";
public static final String ARTICLE_SEQ = "article_seq";
%>