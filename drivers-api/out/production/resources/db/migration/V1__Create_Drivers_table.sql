create table DRIVERS (
  ID serial,
  name varchar(100) NOT NULL,
  license_number VARCHAR(100) NOT NULL,
  type VARCHAR(100) NOT NULL,
  last_updated_date VARCHAR(100) NOT NULL,
  last_updated_time VARCHAR(100) NOT NULL,
  expiration_date VARCHAR(100) NOT NULL
);