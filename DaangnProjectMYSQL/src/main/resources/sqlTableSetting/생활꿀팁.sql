show tables;

drop table if exists daangn_life_comment_like;
drop table if exists daangn_life_comment;
drop table if exists daangn_life_board_file;
drop table if exists daangn_life_board_like;
drop table if exists daangn_life_board;
drop table if exists daangn_life_category;





create table daangn_life_category(
    idx int primary key auto_increment,
    categoryName varchar(50) not null
);

insert into daangn_life_category (categoryName) values ('동네질문');
insert into daangn_life_category (categoryName) values ('동네사건사고');
insert into daangn_life_category (categoryName) values ('동네맛집');
insert into daangn_life_category (categoryName) values ('동네소식');
insert into daangn_life_category (categoryName) values ('생활정보');
insert into daangn_life_category (categoryName) values ('취미생활');
insert into daangn_life_category (categoryName) values ('일상');
insert into daangn_life_category (categoryName) values ('해주세요');
insert into daangn_life_category (categoryName) values ('동네사진전');
insert into daangn_life_category (categoryName) values ('분실/실종센터');


create table daangn_life_board(
	idx int primary key auto_increment,
	userRef int not null,
	constraint dg_life_bd_userRef foreign key (userRef) references daangn_member(idx) on delete cascade,
	categoryRef int not null,
	constraint dg_life_bd_categoryRef foreign key (categoryRef) references daangn_life_category(idx),
	title varchar(200) not null,
	content text not null,
	loc1 varchar(50) not null,
	loc2 varchar(50) not null,
	loc3 varchar(50) not null,
	readCount int default 0,
	likeCount int default 0,
	commentCount int default 0,
	createDate timestamp default current_timestamp,
	updateDate timestamp default current_timestamp on update current_timestamp,
	ip varchar(150) not null
);

create table daangn_life_board_file(
	idx int primary key auto_increment,
    boardRef int not null,
    constraint dg_life_bg_file_boardRef foreign key (boardRef) references daangn_life_board(idx) on delete cascade,
    saveFileName varchar(500) not null
);

create table daangn_life_board_like(
    idx int PRIMARY KEY AUTO_INCREMENT,
	userRef int not null,
    constraint dg_life_bd_like_userRef foreign key (userRef) references daangn_member(idx) on delete cascade,
    boardRef int not null,
    constraint dg_life_bg_like_boardRef foreign key (boardRef) references daangn_life_board(idx) on delete cascade
);

create table daangn_life_comment(
	idx int primary key auto_increment,
	userRef int not null,
	constraint dg_life_cm_userRef foreign key (userRef) references daangn_member(idx) on delete cascade,
	boardRef int not null,
	constraint dg_life_cm_boardRef foreign key (boardRef) references daangn_life_board(idx) on delete cascade,
	parentComIdx int null,
	constraint dg_life_cm_parentComIdx foreign key (parentComIdx) references daangn_life_comment(idx) on delete cascade,
	comment varchar(500) not null,
	likeCount int default 0,
	childCommentCount int default 0,
	isOwner int default 0,
	ip varchar(150) not null,
	createDate timestamp default current_timestamp,
	updateDate timestamp default current_timestamp on update current_timestamp
);

create table daangn_life_comment_like(
	userRef int not null,
	constraint dg_life_cm_like_userRef foreign key (userRef) references daangn_member(idx) on delete cascade,
    commentRef int not null,
    constraint dg_life_cm_like_commentRef foreign key (commentRef) references daangn_life_comment(idx) on delete cascade
);

select * from daangn_life_category;
select * from daangn_life_board;
select * from daangn_life_board_file;
select * from daangn_life_board_like;
select * from daangn_life_comment;
select * from daangn_life_comment_like;