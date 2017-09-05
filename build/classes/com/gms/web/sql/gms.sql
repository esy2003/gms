-- userId, userPw, name, ssn, regdate
<!-- ***********************
2017.8.2
만드는순서(정순),지우는순서(역순)
[1] major_tab
[2] subject_tab
[3] member_tab
[4] prof_tab
[5] student_tab
[6] grade_tab
[7] board_tab
*********************** 
select * from major;
select * from Member;

CREATE SEQUENCE seq
START WITH 2000
INCREMENT BY 1
NOCACHE
NOCYCLE;

***********************
[1] MAJOR TABLE
2017.8.2
전공테이블
major_id, title
***********************
--DDL


CREATE TABLE Major (
	major_id VARCHAR2(10),
	title VARCHAR2(10),
	PRIMARY KEY(major_id)
);
***********************
[2] SUBJECT TABLE
2017.8.2
과목테이블
subj_id, title, major_id
***********************
CREATE TABLE Subject(
	subj_id VARCHAR2(10),
	title VARCHAR2(10),
	major_id VARCHAR2(10),
	PRIMARY KEY(subj_id),
	FOREIGN KEY(major_id) REFERENCES Major(major_id)
		ON DELETE CASCADE
);
***********************
[3] MEMBER TABLE
2017.8.2
회원관리테이블
userId, userPw, name, ssn, regdate,
phone, email, major_id, profile
***********************
DDL
CREATE TABLE Member (
	userId VARCHAR2(10),
	userPw VARCHAR2(10),
	name VARCHAR2(20),
	ssn VARCHAR2(15),
	regdate DATE,
	major_id VARCHAR2(10),
	phone VARCHAR2(20),
	email VARCHAR2(20),
	profile VARCHAR(20),
	PRIMARY KEY(userId),
	FOREIGN KEY(major_id) REFERENCES (major_id)
		ON DELETE CASCADE
);
***********************
PROF TABLE
2017.8.2
교수테이블
userId, salary
***********************
CREATE TABLE Prof (
	userId VARCHAR2(10),
	salary VARCHAR2(10),
	PRIMARY KEY(userId),
	FOREIGN KEY(userId) REFERENCES Member(userId)
		ON DELETE CASCADE	 
);
***********************
STUDENT TABLE
2017.8.2
학생테이블
userId, stu_no
***********************
CREATE TABLE Student (
	userId VARCHAR2(10),
	stu_no VARCHAR2(10),
	PRIMARY KEY(userId),
	FOREIGN KEY(userId) REFERENCES Member(userId)
		ON DELETE CASCADE
);
***********************
Grade TABLE
2017.8.2
성적테이블
grade_seq,score, exam_date, userId, subj_id
***********************
CREATE TABLE Grade(
	grade_seq NUMBER,
	score VARCHAR2(3),
	exam_date VARCHAR2(20),
	subj_id VARCHAR2(10),
	userId VARCHAR2(10),
	PRIMARY KEY(grade_seq),
	FOREIGN KEY(userId) REFERENCES Member(userId)
		ON DELETE CASCADE,
	FOREIGN KEY(subj_id) REFERENCES Subject(subj_id)
		ON DELETE CASCADE
);
--DML
INSERT INTO Grade(grade_seq, score, exam_date, subj_id, userId) 
VALUES(seq.nextval, '81', '2017-06', 'java', 'jang');


select * from grade g
inner join member m on g.userId=m.userId;

	--inner join
	select avg(score) from (select distinct 
	m.userId id, m.name name,
	g.score score, g.exam_date exam_date
	from Grade g
	inner join subject s on g.subj_id=s.subj_id
	inner join member m on m.userId = g.userId) t

	where t.id = 'jang';

	select * from board b, member m
	where b.userId = m.userId;
	
	select * from board b inner join member m
	on b.userId = m.userId
	where m.userId = 'lee';

SELECT * FROM grade;
***********************
BOARD TABLE
2017.8.2
게시판테이블
article_seq,userId, title, content, hitcount, regdate
***********************
CREATE TABLE Board (
	article_seq NUMBER,
	userId VARCHAR2(10),
	title VARCHAR2(20),
	content VARCHAR2(100),
	hitcount NUMBER,
	regdate DATE,
	PRIMARY KEY(article_seq),
	FOREIGN KEY(userId) REFERENCES Member(userId)
		ON DELETE CASCADE
);
 -->
select * from subject;
INSERT INTO Member(userId, userPw, name, ssn, regdate, phone, email, major_id, profile)
VALUES('lee3', '1', '이상윤', '830912-1236308', SYSDATE, '010-1010-1010', 'asd@nate.com', 'art', 'defaultimg.jpg');

INSERT INTO Board(article_seq,userId, title, content, hitcount, regdate)
VALUES(seq.nextval,'jung','애국가2절','강산위에 저소나무 철갑을두른듯',0,SYSDATE);

select * from grade;

SELECT * FROM Member WHERE userId = 'jung';

UPDATE Member SET userPw='3' WHERE userId = 'jung';

SELECT userId FROM Board WHERE title LIKE '%우리%';

SELECT DISTINCT userId FROM Board WHERE userId LIKE '%o%'
<!-- 위 구문 DISTINCT 는 중복값 제외한 값 검색(제거가 아님) -->

SELECT * FROM Board WHERE title LIKE '_리%';

SELECT * FROM Member ORDER BY name, ssn;
<!-- 이름과 ssn순 정렬 -->

SELECT * FROM Member ORDER BY name DESC, ssn ASC;
<!-- 이름 역순(ㅎ부터) ssn정순 -->

SELECT SUM(article_seq) FROM Board;
<!-- 숫자 총합 -->

SELECT * FROM Member m, Board b
WHERE m.userId = b.userId;
<!-- 위 구문은 두개 테이블 조인 -->

SELECT * FROM Member m, Board b
WHERE m.userId = b.userId
ORDER BY article_seq DESC;
<!-- 위 구문은 2중 테이블 조인 후 article_seq 역순 정렬 -->

SELECT * FROM Member m, Board b
WHERE m.userId = b.userId
ORDER BY article_seq ASC;
<!-- 위 구문은 2중 테이블 조인 후 article_seq 정순 정렬 -->

select * from student;

SELECT * FROM Member m, Board b, Grade g
WHERE m.userId = b.userId AND m.userId = g.userId;
<!-- 3중 테이블 userId 조인 -->

SELECT DISTINCT name FROM Member m, Board b
WHERE m.userId = b.userId AND ROWNUM <=3
ORDER BY article_seq DESC
<!-- 중복된값 제거(DISTINCT) 후 ssn 찾는다 멤버와보드아이디가 같은 녀석을 3명까지만 ssn 을 나이어린 순으로(숫자 높은 순) -->


create view student(num, userId, name, ssn, regdate, phone, email, title)
as
select rownum num, t.userId, t.name, t.ssn, t.regdate, t.phone, t.email, t.title
from (
    select a.userId userId, a.name, rpad(substr(a.ssn,1,8),14, '*') ssn, a.phone, a.email, 
    listagg(s.title, ',') within group (order by s.title) title, to_char(a.regdate,'yyyy/mm/dd') regdate 
    from member a
        left join major m on a.userId like m.userId
        left join subject s on m.subj_id like s.subj_id
    group by a.userId, a.name, a.ssn, a.phone, a.email, a.regdate
    order by regdate
) t
order by rownum desc;

select t2.*
from (select rownum seq, t.*
	from (select *
		from student
		where name like '%장%'
		order by num desc) t ) t2
where t2.seq between 1 and 5;

select * from student
where (select count(*)-5 from student) < num;

select * from student
where num between (select count(*)-(5*2) from student) and (select count(*)-((5*1)+1) from student);

select t.*
from (select rownum rnum, s.*
		from student s) t
where t.rnum between 5*1+1 and 5*2;

select * from student
where num between (select count(*)-(5*3) from student) and (select count(*)-((5*2)+1) from student);

select * from member;

INSERT INTO PROF(userId, salary)
VALUES('jfla','5500');
<!-- 교수추가 -->

insert into student(userId, stu_no) values ('555','20161017');
<!-- 학생추가 -->


select * from prof;

select * from member m, prof p
where m.userId=p.userId;
<!-- 멤버에서교수뽑기(salary 있으면 교수) -->

select * from member m, student s
where m.userId = s.userId;
<!-- 멤버에서학생뽑기(stu_no 있으면 학생) -->

select t.id id,avg(score) avg
from (select
    m.userId id,
    g.score score
  from grade g
    inner join member m on m.userId = g.userId) t
group by t.id
having avg(score)>70
order by avg(score) desc
;
<!-- 모든 인원에서 시험친 인원 평균 순위뽑아내는것 -->

(select
        m.userId id,
        g.score score
      from grade g
        inner join subject s on g.subj_id = s.subj_id
        inner join member m on m.userId = g.userId) t
<!-- 1단계 안쪽구문, 이것의 실행결과는 사람3명의 점수 4개 총 12개 결과 출력 -->

(select t.id, avg(t.score) avg
  from (select
        m.userId id,
        g.score score
      from grade g
        inner join subject s on g.subj_id = s.subj_id
        inner join member m on m.userId = g.userId) t
    
group by t.id
having avg(score)>70
order by avg(score) desc
) t2
<!-- 2단계 한번 덮음, 결과값은 3개 아이디의 평균 점수를 desc(높은)순서로 출력(등수)-->

select rownum no, t2.*
from (select t.id id, avg(t.score) avg
  from (select
        m.userId id,
        g.score score
      from grade g
        inner join subject s on g.subj_id = s.subj_id
        inner join member m on m.userId = g.userId) t
    
group by t.id
having avg(score)>70
order by avg(score) desc
) t2
where rownum < 4;
<!-- 3번 덮음, 3명의 점수를 등수대로 출력해서 앞에 번호를 rownum 으로 잡아주면 1부터 순차로 올라감(<4로 했기때문에 3까지만)-->


select *
from emp e
where e.seq in
(select d.seq seq
    from dep d
    join emp e on d.seq=e.seq);
<!--where 절에서의 중첩질의. 비식별관계(부모자식관계)에서만 사용가능 -->


create table Professor(
	profno number primary key,
	name varchar2(20),
	userid varchar2(20),
	position varchar2(20),
	sal number,
	hiredate varchar2(20),
	comm number,
	deptno number
);
create table Dept(
	deptno number primary key,
	dname varchar2(20),
	college number,
	loc varchar2(30)
);
create table Salgrade(
	grade number primary key,
	losal number,
	hisal number
);
insert into Dept values (101, '컴공', 100, '1호관');
insert into Dept values (102, '멀티미디어', 100, '2호관');
insert into Dept values (201, '전자공학', 200, '3호관');
insert into Dept values (202, '기계공학', 200, '4호관');
insert into Dept values (100, '정보미디어', 10, null);
insert into Dept values (200, '메카', 10, null);
insert into Dept values (10, '공과대학', 0, null);

insert into salgrade values (1, 100, 300);
insert into salgrade values (2, 301, 400);
insert into salgrade values (3, 401, 500);


insert into Professor values(9001, '김유신', 'kim', '교수', 500, 2014, 20, 101);
insert into Professor values(9002, '힝골딩', 'hong', '조교수', 320, 2013, 0, 100);
insert into Professor values(9003, '이순신', 'lee', '전임강사', 240, 2015, 0, 102);
insert into Professor values(9004, '유관순', 'yoo', '부교수', 400, 2010, 17, 202);
insert into Professor values(9005, '박쥐', 'park', '교수', 540, 2014, 15, 201);
insert into Professor values(9006, '황소', 'hwang', '조교수', 370, 2013, 20, 200);
insert into Professor values(9007, '장군', 'jang', '전임강사', 290, 2015, 0, 101);
insert into Professor values(9008, '유리', 'yool', '부교수', 410, 2010, 15, 201);

create table stud(
	stuno number primary key,
	name varchar2(20),
	userid varchar2(20),
	grade varchar2(10),
	birthdate varchar2(20),
	tel varchar2(20),
	deptno number,
	profno number,
	height number
);

insert into stud values (1001, '송중기', 'song', 2, '1990','010-1234-1234',101,9001, 180);
insert into stud values (1002, '송지효', 'song1', 1, '1989','010-1234-1234',101,9001, 160);
insert into stud values (1003, '송사리', 'song2', 1, '1991','010-1234-1234',101,9001, 15);
insert into stud values (1004, '송충이', 'song3', 1, '1990','010-1234-1234',201,9001, 15);
insert into stud values (1005, '송이송이', 'song4', 3, '1990','010-1234-1234',201,9001, 60);
insert into stud values (1006, '송송송', 'song5', 3, '1989','010-1234-1234',201,9001, 145);
insert into stud values (1007, '파송송', 'pa', 3, '1997','010-1234-1234',201,9001, 189);
insert into stud values (1008, '계란탁', 'gye', 4, '1994','010-1234-1234',201,9001, 174);
insert into stud values (1009, '옥수수', 'ok', 3, '1994','010-1234-1234',201,9001, 181);
insert into stud values (1010, '옥주현', 'ok1', 1, '1994','010-1234-1234',101,9001, 158);
insert into stud values (1011, '백수', 'baeck', 3, '1994','010-1234-1234',201,9001, 172);
insert into stud values (1012, '박수무당', 'park', 4, '1991','010-1234-1234',201,9001, 168);
insert into stud values (1013, '박제', 'park1', 4, '1991','010-1234-1234',201,9001, 170);
insert into stud values (1014, '남자', 'nam', 4, '1993','010-1234-1234',201,9001, 182);
insert into stud values (1015, '남강', 'nam1', 2, '1993','010-1234-1234',201,9001, 182);
insert into stud values (1016, '장수', 'jang', 2, '1993','010-1234-1234',201,9001, 194);
insert into stud values (1017, '풍뎅이', 'pung', 1, '1995','010-1234-1234',201,9001, 159);
insert into stud values (1018, '조개', 'cho', 1, '1995','010-1234-1234',201,9001, 165);
insert into stud values (1019, '정기', 'jung', 1, '1995','010-1234-1234',201,9001, 166);
insert into stud values (1020, '최고', 'choi', 1, '1994','010-1234-1234',201,9001, 167);
insert into stud values (1021, '고자', 'go', 1, '1993','010-1234-1234',201,9005, 168);
insert into stud values (1022, '자유', 'ja', 1, '1991','010-1234-1234',201,9005, 169);
insert into stud values (1023, '유희열', 'you', 3, '1997','010-1234-1234',201,9005, 173);
insert into stud values (1024, '김수로', 'kim', 3, '1997','010-1234-1234',201,9005, 173);
insert into stud values (1025, '탁재훈', 'tak', 3, '1997','010-1234-1234',201,9005, 183);
insert into stud values (1026, '이수근', 'lee', 4, '1997','010-1234-1234',201,9005, 155);
insert into stud values (1027, '강호동', 'kang', 3, '1997','010-1234-1234',101,9005, 155);
insert into stud values (1028, '민경훈', 'min', 4, '1998','010-1234-1234',101,9005, 165);
insert into stud values (1029, '서장훈', 'seo', 4, '1998','010-1234-1234',101,9005, 207);
insert into stud values (1030, '서유리', 'seo1', 4, '1998','010-1234-1234',101,9005, 165);
insert into stud values (1031, '고진감래', 'go1', 4, '1998','010-1234-1234',101,9005, 170);
insert into stud values (1032, '김장', 'kim1', 4, '1990','010-1234-1234',101,9005, 172);
insert into stud values (1033, '김치', 'kim2', 3, '1991','010-1234-1234',101,9005, 179);
insert into stud values (1034, '이쁨', 'lee1', 3, '1991','010-1234-1234',101,9005, 176);
insert into stud values (1035, '이본', 'lee2', 3, '1993','010-1234-1234',101,9005, 169);
insert into stud values (1036, '조장훈', 'cho1', 2, '1994','010-1234-1234',101,9005, 162);
insert into stud values (1037, '조석훈', 'cho2', 2, '1992','010-1234-1234',101,9005, 179);
insert into stud values (1038, '이석훈', 'lee3', 1, '1996','010-1234-1234',101,9005, 150);
insert into stud values (1039, '이성준', 'lee4', 2, '1996','010-1234-1234',101,9005, 149);
insert into stud values (1041, '김치년', 'kim4', 2, '1994','010-1234-1234',101,9005, 177);

