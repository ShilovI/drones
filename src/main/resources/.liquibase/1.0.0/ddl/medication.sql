--liquibase formatted sql

--changeset ShilovI:1.0.0/ddl/medication
--rollback drop table drones.medication;
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where table_schema = 'drones' and table_name = 'medication';
create table drones.medication
(
    code                varchar(128)                                              not null,
    name                varchar(128)                                              not null,
    weight              bigint                                                    not null,
    image               bytea                                                             ,
    created             timestamp(0) default now()                                not null,
    updated             timestamp(0) default now()                                not null,
    active              boolean                                               default true
);

comment on table drones.medication is 'The table stores medication';
comment on column drones.medication.id is 'Primary key';
comment on column drones.medication.weight is 'Weight';
comment on column drones.medication.code is 'Code';
comment on column drones.medication.image is 'Picture of a case';
comment on column drones.medication.active is 'A soft deleted flag: true - active, false - deleted';



create unique index medication_id_uindex
    on drones.medication (code);

create unique index medication_code_uindex
    on drones.medication (code);