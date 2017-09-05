-- user_id, user_pw, name, ssn, regdate


CREATE TABLE Member (
	userId VARCHAR2(10),
	userPw VARCHAR2(10),
	name VARCHAR2(20),
	ssn VARCHAR2(15),
	regdate DATE,
	PRIMARY KEY(userId)
);
SELECT * FROM Member;

-- user_id, title, content;
-- article_seq, hitcount;
-- regdate;

SELECT * FROM Board;

SELECT * FROM Member;

SELECT COUNT(*) AS count FROM Board;




SELECT * FROM TAB;

