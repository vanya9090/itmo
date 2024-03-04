DROP TYPE IF EXISTS status_m CASCADE;
DROP TYPE IF EXISTS status_u CASCADE;
DROP TYPE IF EXISTS status_s CASCADE;

DROP TABLE IF EXISTS human CASCADE;
DROP TABLE IF EXISTS machine CASCADE;
DROP TABLE IF EXISTS university CASCADE;
DROP TABLE IF EXISTS location CASCADE;
DROP TABLE IF EXISTS mission CASCADE;
DROP TABLE IF EXISTS mission_machine CASCADE;
DROP TABLE IF EXISTS human_mission CASCADE;
DROP TABLE IF EXISTS human_university CASCADE;
DROP TABLE IF EXISTS human_machine CASCADE;

CREATE TYPE status_m AS ENUM ('successed', 'failed', 'planned', 'continues');
CREATE TYPE status_s AS ENUM ('working', 'fired', 'leaved');
CREATE TYPE status_u AS ENUM ('expelled', 'successed', 'planned');

CREATE TABLE IF NOT EXISTS human (
  passport VARCHAR(10) NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  lastname TEXT NOT NULL,
  hope TEXT
);

CREATE TABLE IF NOT EXISTS machine (
  serial_number INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  purpose TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS university (
  id SERIAL PRIMARY KEY, 
  name TEXT NOT NULL,
  type_u TEXT NOT NULL,
  UNIQUE(name, type_u)
);

CREATE TABLE IF NOT EXISTS location (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  coordinates POINT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS mission (
  id SERIAL PRIMARY KEY,
  location INTEGER REFERENCES location(id) NOT NULL,
  university INTEGER REFERENCES university(id) NOT NULL,
  purpose TEXT NOT NULL,
  start_date DATE,
  end_date DATE,
  status status_m NOT NULL
);

CREATE TABLE IF NOT EXISTS speciality (
  speciality_code INTEGER NOT NULL PRIMARY KEY,
  name TEXT UNIQUE NOT NULL 
);

CREATE DATABASE IF NOT EXISTS speciality_human (
  speciality INTEGER REFERENCES speciality(speciality_code) NOT NULL,
  human INTEGER REFERENCES human(human_passport) NOT NULL,
  start_date DATE,
  end_date DATE,
  status status_s
);

CREATE TABLE IF NOT EXISTS mission_machine (
  mission_id INTEGER REFERENCES mission(id) NOT NULL,
  machine_num INTEGER REFERENCES machine(serial_number) NOT NULL,
  PRIMARY KEY (mission_id, machine_id)
);

CREATE TABLE IF NOT EXISTS human_mission (
  mission_id INTEGER REFERENCES mission(id) NOT NULL,
  human_passport INTEGER REFERENCES human(passport) NOT NULL,
  PRIMARY KEY (mission_id, human_passport)
);

CREATE TABLE IF NOT EXISTS human_university (
  human_passport INTEGER REFERENCES human(passport) NOT NULL,
  university_id INTEGER REFERENCES university(id) NOT NULL,
  PRIMARY KEY (human_passport, university_id),
  start_date DATE,
  end_date DATE,
  status status_u NOT NULL
);

CREATE TABLE IF NOT EXISTS human_machine (
  human_passport INTEGER REFERENCES human(passport) NOT NULL,
  machine_id INTEGER REFERENCES machine(id) NOT NULL,
  part TEXT NOT NULL,
  PRIMARY KEY (human_passport, machine_id) 
);


