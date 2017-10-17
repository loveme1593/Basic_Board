 create table board_customer(
 cus_id varchar(50) not null,
 cus_pw varchar(50) not null,
 cus_nickname varchar(50) not null,
 primary key(cus_id)
 );
 
 create table board(
 board_num int auto_increment,
 board_title varchar(100) not null,
 board_content varchar(1000) not null,
 board_date datetime default CURRENT_TIMESTAMP,
 board_id varchar(50) not null,
 board_nickname varchar(50) not null,
 board_hits int default 0,
 board_replies int default 0,
 primary key(board_num)
 );
 
 create table reply(
 board_num int,
 reply_num int auto_increment primary key,
 rreply_num int,
 reply_id varchar(50) not null,
 reply_content varchar(200),
 reply_date datetime default CURRENT_TIMESTAMP,
 reply_nickname varchar(50) not null,
 rreply_id varchar(50),
 rreply_nickname varchar(50),
 foreign key(board_num) references board(board_num)
 on delete cascade
 );

