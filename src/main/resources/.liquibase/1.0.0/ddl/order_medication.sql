--liquibase formatted sql

--changeset ShilovI:1.0.0/ddl/order_medication_seq
--rollback drop sequence drones.order_medication_seq;
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where table_schema = 'drones' and table_name = 'order_medication';
create sequence drones.order_medication_seq;

--changeset ShilovI:1.0.0/ddl/order_medication
--rollback drop table drones.order_medication;
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where table_schema = 'drones' and table_name = 'order_medication';
create table drones.order_medication
(
    id         bigint default nextval('drones.order_medication_seq'::regclass)   not null,
    order_id bigint                                                              not null
        constraint order_medication_order_id_fk
            references drones.orders (id)                                        not null,
    medication_code varchar(128)                                                 not null
        constraint order_medication_medication_code_fk
            references drones.medication (code)                                  not null
);

comment on table drones.order_medication is 'The table stores relations between orders and medication';
comment on column drones.order_medication.order_id is 'Foreign key to orders table';
comment on column drones.order_medication.medication_code is 'Foreign key to medication table';

create index order_medication_index
    on drones.order_medication (order_id, medication_code);