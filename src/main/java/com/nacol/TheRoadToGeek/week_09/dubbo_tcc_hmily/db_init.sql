create schema hmily;
create schema hmily1;
use hmily_account_1;
#drop table account_0;
#drop table account_1;
create table `account_0`(
                            `id` bigint not null primary key comment 'id',
                            `user_id` bigint not null comment '用户id',
                            `balance` double not null default 0 comment '余额',
                            `frozen_balance` double not null default 0 comment '余额'
);
create table `account_1`(
                            `id` bigint not null primary key comment 'id',
                            `user_id` bigint not null comment '用户id',
                            `balance` double not null default 0 comment '余额',
                            `frozen_balance` double not null default 0 comment '余额'
);
