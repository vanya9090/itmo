INSERT INTO scientist (inn, name, lastname) VALUES
('1234567890', 'Frank', 'Peabody'),
('1234567899', 'John', 'Johnson');

INSERT INTO target (name, description) VALUES
('soil samples', 'soil and rock samples from great depths'),
('obtain soil samples', 'obtain soil better than others'),
('drilling machine', 'artesian action drill with the principle of a rotating hammer'),
('geological survey', 'geological survey ...');

INSERT INTO work (industry, name, description, planned_start_date, planned_end_date, start_date, end_date, target_id) VALUES
('mining industry', 'obtain soil samples', 'obtain by drill machine', '2017-03-01', '2017-04-10', '2017-03-01', '2017-04-11', 1),
('engineering industry', 'drilling machine', 'creation of a drilling machine', '2016-03-01', '2017-01-01', '2016-03-01', '2017-01-01', 3);

INSERT INTO location (name, latitude, longitude) VALUES
('antartica', 68.34, 77.58),
('Miskatonic University', 71.41, 41.81);

INSERT INTO result (category, name, description, target_id) VALUES
('thing', 'drilling machine', 'one example of drilling machine', 2);

INSERT INTO result (category, name, description) VALUES
('thing', 'soil samples', 'soil and rock samples from great depths'),
('conclusion', 'no soil samples', 'no land under the ice');

INSERT INTO scientist_work (work_id, inn, start_date, end_date, part, location, target) VALUES
(1, '1234567890', '2016-03-01', '2017-01-01', 'all drilling machine', 2, 3),
(1, '1234567899', '2017-03-01', '2017-04-11', 'geologist', 1, 4);

INSERT INTO work_result (work_id, result_id) VALUES
(1, 2),
(2, 1);

INSERT INTO used_result (work_id, result_id) VALUES
(1, 2)
