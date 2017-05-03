CREATE KEYSPACE tracing WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

USE tracing;

CREATE TABLE span(
    id  varchar primary key,
    parent_id varchar,
    source varchar,
    begin_time bigint,
    end_time bigint,
    duration int
);