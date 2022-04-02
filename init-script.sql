CREATE DATABASE eventuate;

GRANT ALL PRIVILEGES ON eventuate.* TO 'mysqluser'@'%' WITH GRANT OPTION;

USE eventuate;

-- Eventuate Tram

CREATE TABLE cdc_monitoring (
  reader_id varchar(255) NOT NULL,
  last_time bigint(20) DEFAULT NULL,
  PRIMARY KEY (reader_id)
);

CREATE TABLE entities (
  entity_type varchar(255) NOT NULL,
  entity_id varchar(255) NOT NULL,
  entity_version longtext NOT NULL,
  PRIMARY KEY (entity_type,entity_id),
  KEY entities_idx (entity_type,entity_id)
);

CREATE TABLE events (
  event_id varchar(255) NOT NULL,
  event_type longtext,
  event_data longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  entity_type varchar(255) NOT NULL,
  entity_id varchar(255) NOT NULL,
  triggering_event longtext,
  metadata longtext,
  published tinyint(4) DEFAULT '0',
  PRIMARY KEY (event_id),
  KEY events_idx (entity_type,entity_id,event_id),
  KEY events_published_idx (published,event_id)
);

CREATE TABLE message (
  id varchar(255) NOT NULL,
  destination longtext NOT NULL,
  headers longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  payload longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  published smallint(6) DEFAULT '0',
  creation_time bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY message_published_idx (published,id)
);

CREATE TABLE offset_store (
  client_name varchar(255) NOT NULL,
  serialized_offset longtext,
  PRIMARY KEY (client_name)
);

CREATE TABLE received_messages (
  consumer_id varchar(255) NOT NULL,
  message_id varchar(255) NOT NULL,
  creation_time bigint(20) DEFAULT NULL,
  PRIMARY KEY (consumer_id,message_id)
);

CREATE TABLE snapshots (
  entity_type varchar(255) NOT NULL,
  entity_id varchar(255) NOT NULL,
  entity_version varchar(255) NOT NULL,
  snapshot_type longtext NOT NULL,
  snapshot_json longtext NOT NULL,
  triggering_events longtext,
  PRIMARY KEY (entity_type,entity_id,entity_version)
);

-- Eventuate Tram Saga

CREATE TABLE saga_instance_participants (
  saga_type VARCHAR(255) NOT NULL,
  saga_id VARCHAR(100) NOT NULL,
  destination VARCHAR(100) NOT NULL,
  resource VARCHAR(100) NOT NULL,
  PRIMARY KEY(saga_type, saga_id, destination, resource)
);


CREATE TABLE saga_instance (
  saga_type VARCHAR(255) NOT NULL,
  saga_id VARCHAR(100) NOT NULL,
  state_name VARCHAR(100) NOT NULL,
  last_request_id VARCHAR(100),
  end_state INT(1),
  compensating INT(1),
  saga_data_type VARCHAR(1000) NOT NULL,
  saga_data_json VARCHAR(1000) NOT NULL,
  PRIMARY KEY(saga_type, saga_id)
);

CREATE TABLE saga_lock_table (
  target VARCHAR(100) PRIMARY KEY,
  saga_type VARCHAR(255) NOT NULL,
  saga_Id VARCHAR(100) NOT NULL
);

CREATE TABLE saga_stash_table (
  message_id VARCHAR(100) PRIMARY KEY,
  target VARCHAR(100) NOT NULL,
  saga_type VARCHAR(255) NOT NULL,
  saga_id VARCHAR(100) NOT NULL,
  message_headers VARCHAR(1000) NOT NULL,
  message_payload VARCHAR(1000) NOT NULL
  );
