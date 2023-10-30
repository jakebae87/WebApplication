<h1>개발환경</h1>

<ul>
  <li>DB: mysql</li>
  <li>View: JSP</li>
</ul>

<h2>MySQL 설정</h2>
<p>Create Database Account</p>
create database web_project;<br>
create user web_user@localhost identified by '0816';<br>
grant all privileges on web_project.* to web_user@localhost;<br><hr>

[member 테이블 생성]<br>
create  table member (    
id varchar(10) primary key, password varchar(10), name varchar(30),<br>
age int(3) not null, jno int(1), info varchar(30), point float(7,2),<br>
birthday varchar(10), rid varchar(10));
<br><br>
이미지를 추가하기 위해 member table에 column 추가<br>
alter table member add uploadfile varchar(50) default "resources/uploadImages/basicman4.png";

[jo 테이블 생성]<br>
create table jo (<br>
jno int(1), jname varchar(10) not null,id varchar(10) not null,<br>
project varchar(20) not null, slogan varchar(30) not null, Primary Key(jno)); <br><br>

[board 테이블 생성]<br>
create  table board (<br>
seq int(5) primary key AUTO_Increment, id varchar(10) not null,<br>
title varchar(200) not null, content Text(2000), regdate  datetime DEFAULT CURRENT_TIMESTAMP, <br>
cnt int default 0);<br><br>

[reply 테이블 생성]<br>
create  table reply (  
seq int(5) primary key AUTO_Increment, post int(5), id varchar(10) not null,<br>
content Text(200), regdate  datetime DEFAULT CURRENT_TIMESTAMP, <br>
foreign key (post) references board(seq) on update cascade on delete restrict);
