.open todo.db

1.회원테이블 생성
CREATE TABLE users (
id number(5), 		  //회원 시퀀스 번호(회원번호	
pw VARCHAR(200),		
user_id VARCHAR(20)   //회원 id
user_nm varchar(20),
insert_dt sysdate,
update_dt sysdtae
);


1.dodoList 생성 생성
CREATE TABLE todos (
id number(5),
title VARCHAR,
content VARCHAR(1000),
reg_id VARCHAR(20),   //등록자
mod_id varchar(20),   //수정자
exec_dt varchar(8),  //실행일자 ?? / 목표일자
insert_dt sysdate,
update_dt sysdtae
);
