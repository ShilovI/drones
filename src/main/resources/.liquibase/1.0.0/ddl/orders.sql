--liquibase formatted sql

--changeset ShilovI:1.0.0/ddl/orders_seq
--rollback drop sequence drones.orders_seq;
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where table_schema = 'drones' and table_name = 'orders';
create sequence drones.orders_seq;

--changeset ShilovI:1.0.0/ddl/orders
--rollback drop table drones.orders;
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where table_schema = 'drones' and table_name = 'orders';
create table drones.orders
(
    id         bigint default nextval('drones.orders_seq'::regclass)                    not null,
    drone_id bigint
        constraint orders_drone_id_fk
            references drones.drones (id)                                                       ,
    status                   varchar(50)                                check (status = 'CREATING' or
                                                                              status = 'CREATED' or
                                                                              status = 'DELIVERING' or
                                                                              status = 'DELIVERED'),
    created             timestamp(0) default now()                                      not null,
    updated             timestamp(0) default now()                                      not null
);

comment on table drones.orders is 'The table stores orders';
comment on column drones.orders.drone_id is 'Id of drone which is carrying out order';
comment on column drones.orders.status is 'Order status';

create unique index orders_id_uindex
    on drones.orders (id);

create unique index orders_drone_id_index
    on drones.orders (drone_id);