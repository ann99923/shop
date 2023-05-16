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

insert into vam_board(bno,title,content,writer) values(vam_seq.nextval,'�׽�Ʈ ����','�׽�Ʈ ����','�۰�1');
insert into vam_board(bno,title,content,writer) values(vam_seq.nextval,'�׽�Ʈ ����2','�׽�Ʈ ����2','�۰�2');
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
   
-- ȸ�� ���̺� 
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

-- ���� ���̺�
create table vam_nation(
nationId varchar2(2) primary key,
nationName varchar2(50)
);

desc vam_nation;

insert into vam_nation values('01','����');
insert into vam_nation values('02','����');

-- �۰� ������
create sequence author_seq start with 1 INCREMENT by 1;

-- �۰� ���̺�
create table vam_author(
authorId number primary key,
authorName varchar2(50),
nationId varchar2(2),
authorIntro long,
foreign key(nationId) references vam_nation(nationId)
);

insert into vam_author(authorId, authorName, nationId, authorIntro) values(author_seq.nextval, '��ȫ��', '01', '�۰� �Ұ��Դϴ�.');
insert into vam_author(authorId, authorName, nationId, authorIntro) values(author_seq.nextval, '�賭��', '01', '�۰� �Ұ��Դϴ�.');
insert into vam_author(authorId, authorName, nationId, authorIntro) values(author_seq.nextval, '��ũ��׸�', '02', '�۰� �Ұ��Դϴ�.');
commit;
select * from vam_author;

alter table vam_author add regDate date default sysdate;
alter table vam_author add updateDate date default sysdate;

commit;


-- �ε����� ã��
select * from user_indexes;

-- ��� ����
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
-- ��ǰ ���̺�
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
desc vam_book;
drop table vam_book;

select * from vam_book;

-- �ܷ�Ű �߰�
alter table vam_book add foreign key (authorId) references vam_author(authorId);
alter table vam_book add foreign key (cateCode) references vam_bcate(cateCode);
commit;

-- ī�װ� ���̺�
create table vam_bcate(
tier number(1) not null,
cateName varchar2(30) not null,
cateCode varchar2(30) not null,
cateParent varchar2(30),
primary key(cateCode),
foreign key(cateParent) references vam_bcate(cateCode)
);
desc vam_bcate;
drop table vam_bcate;
commit;

-- ������ ����
insert into vam_bcate(tier, cateName, cateCode) values (1, '����', '100000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '�Ҽ�', '101000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�ѱ��Ҽ�', '101001','101000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '���̼Ҽ�', '101002','101000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�Ϻ��Ҽ�', '101003','101000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '��/������', '102000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�ѱ���', '102001','102000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�ؿܽ�', '102002','102000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '����/�濵', '103000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�濵�Ϲ�', '103001','103000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�濵�̷�', '103002','103000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�����Ϲ�', '103003','103000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�����̷�', '103004','103000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '�ڱ���', '104000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '����/ó��', '104001','104000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�ڱ�ɷ°��', '104002','104000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�ΰ�����', '104003','104000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '�ι�', '105000','100000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�ɸ���', '105001','105000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '������', '105002','105000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, 'ö��', '105003','105000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '����/��ȭ', '106000','100000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�����Ϲ�', '106001','106000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�����', '106002','106000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�ѱ���', '106003','106000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '����', '107000','100000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�����̷�', '107001','107000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '����', '107002','107000');
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '������', '107003','107000');
insert into vam_bcate(tier, cateName, cateCode) values (1, '����', '200000');
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '����', '201000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�Ҽ�', '201001','201000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '��', '201002','201000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '���', '201003','201000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '�ι�/��ȸ', '202000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '����', '202001','202000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, 'ö��', '202002','202000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '����/�濵', '203000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '������', '203001','203000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�濵��', '203002','203000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '����', '203003','203000');    
    insert into vam_bcate(tier, cateName, cateCode, cateParent) values (2, '����/���', '204000','200000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '�������', '204001','204000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '������', '204002','204000');    
        insert into vam_bcate(tier, cateName, cateCode, cateParent) values (3, '����', '204003','204000'); 
select * from vam_bcate;
commit;