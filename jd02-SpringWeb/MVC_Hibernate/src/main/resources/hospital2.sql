
create table hospital2.users(
	userName varchar(50) not null primary key,
    position varchar(100) not null,
    specialization int(11),
    login varchar(100) not null,
    password varchar(100) not null,
	enabled boolean not null
);
create table hospital2.authorities (
	userName varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(userName) references users(userName)
);
create unique index ix_auth_userName on authorities (userName,authority);