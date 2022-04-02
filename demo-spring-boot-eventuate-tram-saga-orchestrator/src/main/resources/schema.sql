use eventuate;

drop table if exists transaction;

create table transaction (
  transaction_id bigint(20) not null auto_increment,
  customer_id bigint(20) not null,
  transaction_amount decimal(19,3) not null,
  transaction_state varchar(20) not null,
  remarks varchar(100) null,
  primary key(transaction_id)
);
