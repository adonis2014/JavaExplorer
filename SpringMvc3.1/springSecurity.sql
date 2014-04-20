--Create user table.
create table if not exists ss_user (
id tinyint unsigned primary key auto_increment,
name varchar(50) unique not null,
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
authority varchar(50) unique not null,
is_expression boolean not null default false,
enabled boolean not null default true,
description tinytext
)
comment='Spring security authority table.';

insert into ss_authority (authority) values('IS_AUTHENTICATED_FULLY'),('IS_AUTHENTICATED_ANONYMOUSLY'),('IS_AUTHENTICATED_REMEMBERED');

--Create user authority relationship table.
create table if not exists ss_user_authority (
user_id tinyint unsigned not null,
authority_id tinyint unsigned not null,
enabled boolean not null default true,
primary key(user_id,authority_id),
foreign key(user_id) references ss_user(id),
foreign key(authority_id) references ss_authority(id)
)
comment='Spring security user authority relationship table.';

--Create resource table
create table if not exists ss_resource (
id smallint unsigned primary key auto_increment,
parent_id smallint unsigned,
name varchar(20) not null,
enabled boolean not null default true,
description tinytext,
foreign key(parent_id) references ss_resource(id)
)
comment='Spring security resource table.';

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


SELECT r.id,r.parent_id,r.name,sa.method,a.authority,a.is_expression FROM ss_resource r 
INNER JOIN ss_resource_authority sa ON r.id = sa.resource_id AND r.enabled = true AND sa.enabled = true 
INNER JOIN ss_authority a ON sa.authority_id = a.id AND a.enabled = true 
ORDER BY r.id;

