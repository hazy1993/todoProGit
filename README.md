실행방법

1.gradle 의존성 주입

2.run 시작

---------------------------------------------

API 상세 요약

todos 

/todos (공통)

post 저장
{
"title": "제목"
,"content": "내용"
,"execDt":  "20240513" // 목표일자 혹은 실행일자
}

get 게시글 list 조회

get /{id}
게시글 번호로 조회

put /{id}
게시글 번호로 수정
{
"title": "제목수정"
,"content": "내용수정"
,"execDt":  "20240514" // 목표일자 혹은 실행일자
}

delete /{id}
게시글 번호로 삭제

search? 게시글 , 내용으로 검색

title=제목으로검색
content=내용으로 검색


----------------------------------------------------
users

post /users/signup 회원가입

{
"userId":"testId"    //아이디
,"pw" : "testPw"     //비밀번호
,"userNm" : "testPw" //회원이름
}

post /users/login 로그인

{
"userId":"test" //아이디
,"pw" : "test"  //비밀번호
}


get /users/me //아이디로 회원조회
{
"userId":"test"
}

put /users/me //아이디에 해당하는 비밀번호 변경
{
"userId":"test"
,"pw" : "testtest"
}

delete /users/me //아이디에 해당하는 계정 삭제
{
"userId":"test"
}