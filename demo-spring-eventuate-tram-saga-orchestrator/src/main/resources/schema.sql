use eventuate;

drop table if exists customer_order;

create table customer_order (
  order_id bigint(20) not null auto_increment,
  customer_id bigint(20) not null,
  order_amount decimal(19,3) not null,
  order_state varchar(20) not null,
  remarks varchar(100) null,
  primary key(order_id)
);
