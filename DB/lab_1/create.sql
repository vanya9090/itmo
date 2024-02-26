DROP TYPE IF EXISTS speciality_enum CASCADE;

DROP TABLE IF EXISTS human CASCADE;
DROP TABLE IF EXISTS machine CASCADE;
DROP TABLE IF EXISTS university CASCADE;
DROP TABLE IF EXISTS location CASCADE;
DROP TABLE IF EXISTS mission CASCADE;
DROP TABLE IF EXISTS mission_machine CASCADE;
DROP TABLE IF EXISTS human_mission CASCADE;
DROP TABLE IF EXISTS human_university CASCADE;
DROP TABLE IF EXISTS human_machine CASCADE;

CREATE TYPE speciality_enum AS ENUM ('geologist', 'physicist', 'professor');

CREATE TABLE IF NOT EXISTS human (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  lastname TEXT NOT NULL,
  speciality speciality_enum NOT NULL,
  hope TEXT
);

CREATE TABLE IF NOT EXISTS machine (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  purpose TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS university (
  id SERIAL PRIMARY KEY, 
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS location (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  coordinates POINT NOT NULL
);

CREATE TABLE IF NOT EXISTS mission (
  id SERIAL PRIMARY KEY,
  location INTEGER REFERENCES location(id) NOT NULL,
  university INTEGER REFERENCES university(id) NOT NULL,
  purpose TEXT NOT NULL
  );

CREATE TABLE IF NOT EXISTS mission_machine (
  mission_id INTEGER REFERENCES mission(id) NOT NULL,
  machine_id INTEGER REFERENCES machine(id) NOT NULL,
  PRIMARY KEY (mission_id, machine_id)
);

CREATE TABLE IF NOT EXISTS human_mission (
  mission_id INTEGER REFERENCES mission(id) NOT NULL,
  human_id INTEGER REFERENCES human(id) NOT NULL,
  PRIMARY KEY (mission_id, human_id)
);

CREATE TABLE IF NOT EXISTS human_university (
  human_id INTEGER REFERENCES human(id) NOT NULL,
  university_id INTEGER REFERENCES university(id) NOT NULL,
  PRIMARY KEY (human_id, university_id)
);

CREATE TABLE IF NOT EXISTS human_machine (
  human_id INTEGER REFERENCES human(id) NOT NULL,
  machine_id INTEGER REFERENCES machine(id) NOT NULL,
  part TEXT NOT NULL,
  PRIMARY KEY (human_id, machine_id) 
);


