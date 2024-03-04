INSERT INTO human (passport, name, lastname, hope) VALUES 
('8978126743', 'John', 'Johnson', 'mechanical device assistance');

INSERT INTO human (passport, name, lastname) VALUES
('3278230917', 'Freddy', 'Ivanov'),
('2387835297', 'Frank', 'Peabody'),
('8237510842', 'Jim', 'Petrov');

INSERT INTO speciality (speciality_code, name) VALUES
(3298, 'geologist'),
(23875, 'physicist'),
(2387, 'programmer');

INSERT INTO machine (serial_number, name, purpose) VALUES
(8289, 'drilling rig', 'obtain soil samples'),
(2839, 'construction_crane', 'buid house');

INSERT INTO university (name, type_u) VALUES
('Miskatonic University', 'humanytarian'),
('ITMO', 'technical');

INSERT INTO location (name, coordinates) VALUES
('Antarctica', POINT(69.3, 65)),
('Moscow', POINT(55.7558, 37.6173));

INSERT INTO speciality_human (speciality_code, human_passport, start_date, status) VALUES
(3298, '8978126743', '2020-01-21', 'working');

INSERT INTO speciality_human (speciality_code, human_passport, start_date, end_date, status) VALUES
(2387, '8237510842', '2021-12-02', '2024-01-20', 'fired');

INSERT INTO mission (location, university, purpose, status) VALUES
(1, 1, 'obtain soil samples', 'continues'),
(2, 1, 'wash sneakers in machine', 'continues');

INSERT INTO human_mission (human_passport, mission_id) VALUES
('8978126743', 1),
('3278230917', 2),
('2387835297', 2);

INSERT INTO human_university (human_passport, university_id, start_date, end_date, status) VALUES
('8978126743', 1, '2021-05-19', '2028-01-10', 'studying');

INSERT INTO mission_machine (mission_id, machine_num) VALUES
(1, 8289),
(2, 2839);

INSERT INTO human_machine (human_passport, machine_num, part) VALUES
('2387835297', 8289, 'construction');
