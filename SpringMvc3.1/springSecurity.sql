--Create user table.
create table if not exists ss_user (
id tinyint unsigned primary key auto_increment,
name varchar(20) unique not null,
pd char(41) not null,
enabled boolean not null default true,
locked boolean  not null default false,
expired_time timestamp default '2038-01-19 03:14:07',
create_time timestamp default 0,
update_time timestamp default current_timestamp on update current_timestamp
)
comment='Spring security user table.';

--Create authority table.
create table if not exists ss_authority (
id tinyint unsigned primary key auto_increment,
authority varchar(20) unique not null,
description tinytext
)
comment='Spring security authority table.';

insert into ss_authority (authority) values('ROLE_USER'),('ROLE_ADMIN')

--Create user authority relationship table.
create table if not exists ss_user_authority (
user_id tinyint unsigned not null,
authority_id tinyint unsigned not null,
primary key(user_id,authority_id),
foreign key(user_id) references ss_user(id),
foreign key(authority_id) references ss_authority(id)
)
comment='Spring security user authority relationship table.';

insert into ss_user(name,pd,create_time)values('user3',password('123'),current_timestamp),('ÀîËÄ',password('123'),current_timestamp)
insert into ss_user_authority values(3,1),(3,2);

--Create resource table
create table if not exists ss_resource (
id smallint unsigned primary key auto_increment,
parent_id smallint unsigned not null default 1,
pathname varchar(20) not null,
enabled boolean not null default true,
description tinytext,
foreign key(parent_id) references ss_resource(id)
)
comment='Spring security resource table.';

insert into ss_resource (pathname) values('upload');

--Create resource authority method table
create table if not exists ss_resource_authority (
resource_id smallint unsigned not null,
method enum('ALL','GET','DELETE','HEAD','OPTIONS','POST','PUT','PATCH','TRACE') not null default 'ALL',
authority_id tinyint unsigned not null,
enabled boolean not null default true,
primary key(resource_id,method,authority_id),
foreign key(resource_id) references ss_resource(id),
foreign key(authority_id) references ss_authority(id),
index ri(resource_id)
)
comment='Spring security resource authority table.';

insert into ss_resource_authority (resource_id,authority_id) values(1,2)
drop table ss_resource_authority

select * from ss_resource r inner join ss_resource_authority sa on r.id=sa.resource_id where r.pathname='upload';

select r.id,r.parent_id,r.pathname,ps.pathname parent_pathname,sa.method,a.authority from ss_resource r 
inner join ss_resource ps on r.parent_id = ps.id and ps.enabled=true 
inner join ss_resource_authority sa on r.id=sa.resource_id and r.enabled=true 
inner join ss_authority a on sa.authority_id=a.id and sa.enabled=true 
where r.pathname='upload' order by sa.method;

SELECT r.id,r.parent_id,r.pathname,sa.method,a.authority FROM ss_resource r 
INNER JOIN ss_resource_authority sa ON r.id=sa.resource_id AND r.enabled=true 
INNER JOIN ss_authority a ON sa.authority_id=a.id AND sa.enabled=true 
ORDER BY r.id;