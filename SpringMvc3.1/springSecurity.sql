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


--Create role table.
create table if not exists ss_role (
id tinyint unsigned primary key auto_increment,
name varchar(50) unique not null,
enabled boolean not null default true,
description tinytext
)
comment='Spring security role table.';


--Create user role relationship table.
create table if not exists ss_user_role (
user_id tinyint unsigned not null,
role_id tinyint unsigned not null,
enabled boolean not null default true,
primary key(user_id,role_id),
foreign key(user_id) references ss_user(id),
foreign key(role_id) references ss_role(id)
)
comment='Spring security user role relationship table.';

--Create role authority relationship table.
create table if not exists ss_role_authority (
role_id tinyint unsigned not null,
authority_id tinyint unsigned not null,
enabled boolean not null default true,
primary key(role_id, authority_id),
foreign key(role_id) references ss_role(id),
foreign key(authority_id) references ss_authority(id)
)
comment='Spring security role authority relationship table.';

SELECT r.id role_id,r.name role,a.authority FROM ss_user u INNER JOIN ss_user_role ur ON u.id=ur.user_id AND ur.enabled=true INNER JOIN ss_role r ON ur.role_id=r.id AND r.enabled=true INNER JOIN ss_role_authority ra ON r.id=ra.role_id AND ra.enabled=true INNER JOIN ss_authority a ON ra.authority_id=a.id AND a.enabled=true WHERE u.id = ?

-- ACL table
CREATE TABLE acl_sid (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    principal BOOLEAN NOT NULL,
    sid VARCHAR(100) NOT NULL,
    UNIQUE unique_acl_sid (sid, principal)
);

CREATE TABLE acl_class (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE acl_object_identity (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    object_id_class BIGINT UNSIGNED NOT NULL,
    object_id_identity BIGINT NOT NULL,
    parent_object BIGINT UNSIGNED,
    owner_sid BIGINT UNSIGNED,
    entries_inheriting BOOLEAN NOT NULL,
    UNIQUE (object_id_class, object_id_identity),
    FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
);

CREATE TABLE acl_entry (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acl_object_identity BIGINT UNSIGNED NOT NULL,
    ace_order INTEGER NOT NULL,
    sid BIGINT UNSIGNED NOT NULL,
    mask INTEGER UNSIGNED NOT NULL,
    granting BOOLEAN NOT NULL,
    audit_success BOOLEAN NOT NULL,
    audit_failure BOOLEAN NOT NULL,
    UNIQUE (acl_object_identity, ace_order),
    FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    FOREIGN KEY (sid) REFERENCES acl_sid (id)
);