

drop table if exists log;
drop table if exists account;

create table if not exists account(
account_number int(6) unsigned auto_increment primary key,
id varchar(30) not null unique,
balance decimal(10,2) not null,
type tinyint(1) default 0
);

create table if not exists log(
count int unsigned auto_increment primary key,
account_number int(6) unsigned,
id varchar(30) not null,
amount decimal(10,2) not null,
balance_before decimal(10,2) not null,
balance_after decimal(10,2) not null,
date varchar(100) not null,
foreign key (account_number) references account(account_number),
foreign key (id) references account(id)
);

insert into account (account_number, id, balance) values(173932,"TEST",300);
insert into account (account_number, id, balance, type) values(173442,"TEST2",10000,1);


