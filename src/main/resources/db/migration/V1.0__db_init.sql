drop table if exists flyway_schema_history;
drop table if exists order_service;

create table order_service
(
    id        bigint not null auto_increment primary key,
    customer_name      varchar(255)
) engine = InnoDB;
