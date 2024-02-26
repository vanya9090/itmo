INSERT INTO human (name, lastname, speciality, hope) VALUES 
('John', 'Johnson', 'geologist', 'mechanical device assistance');

INSERT INTO human (name, lastname, speciality) VALUES
('Thom', 'Yorke', 'physicist'),
('Frank', 'Peabody', 'professor'),
('Jim', 'Morrison', 'professor');

INSERT INTO machine (name, purpose) VALUES
('drilling rig', 'obtain soil samples'),
('washing machine', 'wash clothes');

INSERT INTO university (name) VALUES
('Miskatonic University'),
('ITMO');

INSERT INTO location (name, coordinates) VALUES
('Antarctica', POINT(69.3, 65)),
('Moscow', POINT(55.7558, 37.6173));

INSERT INTO mission (location, university, purpose) VALUES
(1, 1, 'obtain soil samples'),
(2, 1, 'wash sneakers in machine');

INSERT INTO human_mission (human_id, mission_id) VALUES
(1, 1),
(2, 2),
(4, 2);

INSERT INTO human_university (human_id, university_id) VALUES
(1, 1);

INSERT INTO mission_machine (mission_id, machine_id) VALUES
(1, 1),
(2, 2);

INSERT INTO human_machine (human_id, machine_id, part) VALUES
(3, 1, 'construct');
