DROP TABLE IF EXISTS scientist CASCADE;
DROP TABLE IF EXISTS task CASCADE ;
DROP TABLE IF EXISTS location CASCADE ;
DROP TABLE IF EXISTS result CASCADE ;
DROP TABLE IF EXISTS target CASCADE ;
DROP TABLE IF EXISTS scientist_task CASCADE ;
DROP TABLE IF EXISTS task_result CASCADE ;
DROP TABLE IF EXISTS used_result CASCADE ;

CREATE TABLE IF NOT EXISTS scientist (
  inn TEXT NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  lastname TEXT NOT NULL,
  CHECK (LENGTH(inn)=10)
);

CREATE TABLE IF NOT EXISTS target (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  description TEXT
);

CREATE TABLE IF NOT EXISTS task (
  id SERIAL PRIMARY KEY,
  industry TEXT,
  name TEXT NOT NULL,
  description TEXT,
  planned_start_date DATE NOT NULL,
  planned_end_date DATE NOT NULL,
  start_date DATE,
  end_date DATE,
  target_id INTEGER REFERENCES target(id) NOT NULL,
  CHECK (planned_end_date > planned_start_date)
);


CREATE TABLE IF NOT EXISTS location (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  latitude DECIMAL NOT NULL,
  longitude DECIMAL NOT NULL,
  UNIQUE (name, longitude, latitude),
  CHECK ((longitude between 0 AND 180) AND (latitude BETWEEN 0 AND 90))
);

CREATE TABLE IF NOT EXISTS result (
  id SERIAL PRIMARY KEY,
  category TEXT NOT NULL,
  name TEXT NOT NULL,
  description TEXT,
  target_id INTEGER REFERENCES target(id),
  UNIQUE (category, name)
);

CREATE TABLE IF NOT EXISTS scientist_task (
  task_id INTEGER REFERENCES task (id)     NOT NULL,
  inn TEXT REFERENCES scientist(inn)       NOT NULL,
  start_date DATE,
  end_date DATE,
  part TEXT,
  location INTEGER REFERENCES location(id) NOT NULL,
  target INTEGER REFERENCES target(id),
  PRIMARY KEY (task_id, inn),
  CHECK (end_date > start_date)
);

CREATE TABLE IF NOT EXISTS task_result (
  task_id INTEGER REFERENCES task (id)    NOT NULL,
  result_id INTEGER REFERENCES result(id) NOT NULL,
  PRIMARY KEY (task_id, result_id)
);

CREATE TABLE IF NOT EXISTS used_result (
  task_id INTEGER REFERENCES task (id)    NOT NULL,
  result_id INTEGER REFERENCES result(id) NOT NULL,
  PRIMARY KEY (task_id, result_id)
);
