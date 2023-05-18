create sequence vam_seq start with 1 INCREMENT by 1;

create table vam_board(
bno number,
title varchar2(150) not null,
content varchar2(2000) not null,
writer varchar2(50) not null,
regdate date default sysdate,
updatedate date default sysdate,
constraint pk_board primary key(bno));

desc vam_board;

insert into vam_board(bno,title,content,writer) values(vam_seq.nextval,'테스트 제목','테스트 내용','작가1');
insert into vam_board(bno,title,content,writer) values(vam_seq.nextval,'테스트 제목2','테스트 내용2','작가2');
select * from vam_board;
commit;

insert into vam_board(bno,title,content,writer)
(
select vam_seq.nextval, title, content, writer
from vam_board);

commit;

select count(*) from vam_board;

select rn, bno, title, content, writer, regdate, updatedate from
    (
        select rownum as rn, bno, title, content, writer, regdate, updatedate
        from vam_board
        where rownum <= 20
    )
    where rn > 10;
   
-- 회원 테이블 
create table book_member(
memberId varchar2(50) not null,
memberPw varchar2(100) not null,
memberName varchar2(30) not null,
memberMail varchar2(100) not null,
memberAddr1 varchar2(100) not null,
memberAddr2 varchar2(100) not null,
memberAddr3 varchar2(100) not null,
adminCk number not null,
regDate date not null,
money number not null,
point number not null,
primary key(memberId)
);

desc book_member;
commit;

select * from book_member;

update book_member set adminck=1 where memberId = 'test';
commit;

-- 국가 테이블
create table vam_nation(
nationId varchar2(2) primary key,
nationName varchar2(50)
);

desc vam_nation;

insert into vam_nation values('01','국내');
insert into vam_nation values('02','국외');

-- 작가 시퀀스
create sequence author_seq start with 1 INCREMENT by 1;

-- 작가 테이블
create table vam_author(
authorId number primary key,
authorName varchar2(50),
nationId varchar2(2),
authorIntro long,
foreign key(nationId) references vam_nation(nationId)
);

insert into vam_author(authorId, authorName, nationId, authorIntro) values(author_seq.nextval, '유홍준', '01', '작가 소개입니다.');
insert into vam_author(authorId, authorName, nationId, authorIntro) values(author_seq.nextval, '김난도', '01', '작가 소개입니다.');
insert into vam_author(authorId, authorName, nationId, authorIntro) values(author_seq.nextval, '폴크루그먼', '02', '작가 소개입니다.');
commit;
select * from vam_author where authorId=1795;

alter table vam_author add regDate date default sysdate;
alter table vam_author add updateDate date default sysdate;

commit;


-- 인덱스명 찾기
select * from user_indexes;

-- 재귀 복사
insert into vam_author(authorId, authorName, nationId)
    (
        select author_seq.nextval, authorName, nationId from vam_author
    );

commit;

select * from
(
select /*+INDEX_DESC(vam_author SYS_C007644) */
			rownum as rn, authorId, authorName, nationId, regDate, updateDate
			from vam_author
			where
			rownum <= 30
		)
		where rn > 20
        ;
        
create sequence book_seq start with 1 increment by 1;
-- 상품 테이블
create table vam_book(
bookId number primary key,
bookName varchar2(50) not null,
authorId number,
publeYear Date not null,
publisher varchar2(70) not null,
cateCode varchar2(30),
bookPrice number not null,
bookStock number not null,
bookDiscount number(2,2),
bookIntro clob,
bookContents clob,
regDate date default sysdate,
updateDate date default sysdate
);
commit;
desc vam_book;
drop table vam_book;

select * from vam_book;

-- 카테고리 테이블
create table vam_bcate(
tier number(1) not null,
cateName varchar2(30) not null,
cateCode varchar2(30) not null,
cateParent varchar2(30),
primary key(cateCode),
foreign key(cateParent) references vam_bcate(cateCode)
);
desc vam_bcate;
commit;

-- 데이터 삽입
insert into vam_bcate(tier, cateName, cateCode) values (1, '국내', '100000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '소설', '101000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '한국소설', '101001','101000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '영미소설', '101002','101000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '일본소설', '101003','101000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '시/에세이', '102000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '한국시', '102001','102000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '해외시', '102002','102000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '경제/경영', '103000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '경영일반', '103001','103000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '경영이론', '103002','103000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '경제일반', '103003','103000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '경제이론', '103004','103000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '자기계발', '104000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '성공/처세', '104001','104000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '자기능력계발', '104002','104000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '인간관계', '104003','104000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '인문', '105000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '심리학', '105001','105000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '교육학', '105002','105000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '철학', '105003','105000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '역사/문화', '106000','100000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '역사일반', '106001','106000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '세계사', '106002','106000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '한국사', '106003','106000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '과학', '107000','100000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '과학이론', '107001','107000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '수학', '107002','107000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '물리학', '107003','107000');
insert into vam_bcate(tier, cateName, cateCode) values (1, '국외', '200000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '문학', '201000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '소설', '201001','201000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '시', '201002','201000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '희곡', '201003','201000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '인문/사회', '202000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '교양', '202001','202000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '철학', '202002','202000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '경제/경영', '203000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '경제학', '203001','203000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '경영학', '203002','203000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '투자', '203003','203000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '과학/기술', '204000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '교양과학', '204001','204000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '물리학', '204002','204000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '수학', '204003','204000'); 
select * from vam_bcate;
alter table vam_book add foreign key (authorId) references vam_author(authorId);
alter table vam_book add foreign key (cateCode) references vam_bcate(cateCode);
commit;

-- 19
--재귀 복사
insert into vam_book(bookId, bookName, authorId, publeYear, publisher, cateCode, bookPrice, bookStock, bookDiscount,bookIntro, bookContents)
(select book_seq.nextval,bookName, authorId, publeYear, publisher, cateCode, bookPrice, bookStock, bookDiscount,bookIntro, bookContents from vam_book);
commit;

SELECT * FROM USER_INDEXES WHERE TABLE_NAME = 'VAM_BOOK';

-- 27
create table vam_image(
bookId int,
fileName varchar2(100) not null,
uploadPath varchar2(100) not null,
uuid varchar2(100) not null,
primary key(uuid),
foreign key(bookId) references vam_book(bookId)
);
desc vam_image;
commit;

select * from vam_book;
select * from vam_image;

-- 29 sequence 관련
select book_seq.nextval from dual;
select book_seq.currval from dual;
-- 29: sequence가 table과 연동되어있지 않음 --> 그냥 시퀀스 이름.currval로 secret key 지정
