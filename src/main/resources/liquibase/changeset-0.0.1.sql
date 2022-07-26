--liquibase formatted sql

--changeset Bainc:001
create table public.users(id bigint generated by default as identity primary key not null,
                          created timestamp default current_timestamp not null,
                          updated timestamp default current_timestamp not null,
                          status varchar(25) not null default 'ACTIVE',
                          username varchar(100) unique not null,
                          firstname varchar(100),
                          lastname varchar(100),
                          email varchar(100),
                          password varchar(255) not null);

--changeset Bainc:002
create table public.roles(id bigint generated by default as identity primary key not null,
                          created timestamp default current_timestamp not null,
                          updated timestamp default current_timestamp not null,
                          status varchar not null default 'ACTIVE',
                          name varchar not null unique);

--changeset Bainc:003
create table public.user_roles(user_id bigint not null, role_id bigint not null,
                                constraint users_id foreign key(user_id) references public.users(id),
                                constraint roles_id foreign key(role_id) references public.roles(id));
--changeset Bainc:004
insert into public.roles(name) values ('ROLE_USER'),('ROLE_ADMIN');