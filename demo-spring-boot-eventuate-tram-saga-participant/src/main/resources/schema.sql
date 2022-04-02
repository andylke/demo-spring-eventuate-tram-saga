use eventuate;

drop table if exists account;

create table account (
  account_id bigint(20) not null auto_increment,
  customer_id bigint(20) not null,
  available_amount decimal(19,3) not null,
  reserved_amount decimal(19,3) not null,
  primary key(account_id)
);
