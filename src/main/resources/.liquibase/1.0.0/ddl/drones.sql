--liquibase formatted sql

--changeset ShilovI:1.0.0/ddl/drones
--rollback drop table drones.drones;
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where table_schema = 'drones' and table_name = 'drones';
create table drones.drones
(
    serial_number           varchar(100)                                                not null,
    weight_limit            bigint                                                      not null
            constraint max_weight_limit
                check (weight_limit <= 500),
    model                   varchar(50)                                check (model = 'Lightweight' or
                                                                              model = 'Middleweight' or
                                                                              model = 'Cruiserweight' or
                                                                              model = 'Heavyweight'),
    state                   varchar(50)                                check (state = 'IDLE' or
                                                                              state = 'LOADING' or
                                                                              state = 'LOADED' or
                                                                              state = 'DELIVERING' or
                                                                              state = 'DELIVERED' or
                                                                              state = 'RETURNING'),
    created               timestamp(0) default now()                                    not null,
    updated               timestamp(0) default now()                                    not null,
    active                boolean      default true
);

comment on table drones.drones is 'The table stores meta information about drones';
comment on column drones.drones.id is 'Primary key';
comment on column drones.drones.serial_number is 'Serial number of a drone';
comment on column drones.drones.weight_limit is 'Weight limit - ni more than 500 gramm';
comment on column drones.drones.model is 'Model - one of "Lightweight", "Middleweight", "Cruiserweight", "Heavyweight"';
comment on column drones.drones.state is 'State';
comment on column drones.drones.active is 'A soft deleted flag: true - active, false - deleted';

create unique index drone_id_uindex
    on drones.drones (serial_number);

create unique index drone_state_index
    on drones.drones (state);