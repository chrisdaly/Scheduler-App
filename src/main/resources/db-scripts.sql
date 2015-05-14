create table if not exists schedule(id varchar primary key, text varchar, done boolean);
create table if not exists users(
	username varchar not null primary key, 
	password varchar not null, 
	enabled boolean not null
);

create table if not exists authorities (
	username varchar not null,
	authority varchar not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

-- Create user chris with password='pass' (bcrypt hash) http://bcrypthashgenerator.apphb.com/
insert into users values('chris','$2a$10$DkumvYFDnWPZPFJImYmAWer7VFiW9tOZeLUD76A4JqJt97UNX3uWW',true);
insert into authorities values('chris','ROLE_USER');
insert into authorities values('chris','ROLE_ADMIN');

-- Create user john with password='pass'
insert into users values('john','$2a$10$DkumvYFDnWPZPFJImYmAWer7VFiW9tOZeLUD76A4JqJt97UNX3uWW',true);
insert into authorities values('john','ROLE_USER');


-- Association table user<->todo
create table if not exists owners (
	username varchar, 
	todoid varchar,
	constraint fk_owners_users foreign key(username) references users(username),
	constraint fk_owners_todo foreign key(todoid) references schedule(id)
);