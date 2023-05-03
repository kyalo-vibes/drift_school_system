-- Insert subjects
INSERT INTO subjects (id, name, category) VALUES (1, 'Maths', 'Compulsory');
INSERT INTO subjects (id, name, category) VALUES (2, 'Eng', 'Compulsory');
INSERT INTO subjects (id, name, category) VALUES (3, 'Kis', 'Compulsory');
INSERT INTO subjects (id, name, category) VALUES (4, 'Bio', 'Sciences');
INSERT INTO subjects (id, name, category) VALUES (5, 'Phy', 'Sciences');
INSERT INTO subjects (id, name, category) VALUES (6, 'Chem', 'Sciences');
INSERT INTO subjects (id, name, category) VALUES (7, 'Geog', 'Humanities');
INSERT INTO subjects (id, name, category) VALUES (8, 'CRE', 'Humanities');
INSERT INTO subjects (id, name, category) VALUES (9, 'Histo', 'Humanities');
INSERT INTO subjects (id, name, category) VALUES (10, 'Agri', 'Technical');
INSERT INTO subjects (id, name, category) VALUES (11, 'Comp', 'Technical');
INSERT INTO subjects (id, name, category) VALUES (12, 'BeD', 'Technical');

-- Insert students
INSERT INTO students (id, first_name, last_name) VALUES (1, 'Alice', 'Mumbua');
INSERT INTO students (id, first_name, last_name) VALUES (2, 'Bob', "Kaloki");
INSERT INTO students (id, first_name, last_name) VALUES (3, 'Carol', 'Nduta');
INSERT INTO students (id, first_name, last_name) VALUES (4, 'Dave', 'Gatundu');

-- Insert exam results
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (1, 70, 1, 1);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (2, 65, 1, 2);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (3, 75, 1, 3);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (4, 80, 1, 4);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (5, 85, 1, 5);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (29, 95, 1, 6);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (6, 78, 1, 7);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (7, 82, 1, 10);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (8, 60, 2, 1);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (9, 55, 2, 2);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (10, 65, 2, 3);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (11, 70, 2, 4);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (12, 75, 2, 5);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (13, 68, 2, 7);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (14, 72, 2, 10);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (15, 73, 3, 1);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (16, 60, 3, 2);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (17, 80, 3, 3);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (18, 85, 3, 4);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (19, 82, 3, 5);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (20, 76, 3, 8);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (21, 70, 3, 10);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (22, 60, 4, 1);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (23, 58, 4, 2);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (24, 79, 4, 3);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (25, 81, 4, 4);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (26, 74, 4, 6);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (27, 67, 4, 9);
INSERT INTO exam_results (id, marks, student_id, subject_id) VALUES (28, 69, 4, 12);
