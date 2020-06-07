
CREATE DATABASE IF NOT EXISTS jCERS CHARACTER SET ascii;

USE jCERS;

CREATE TABLE IF NOT EXISTS Student (
    student_id SMALLINT(5) UNSIGNED ZEROFILL AUTO_INCREMENT NOT NULL,
    fname VARCHAR(30),
    lname VARCHAR(30),
    gender ENUM('M', 'F', 'O'),
    dob DATE,
    telephone VARCHAR(15),
    email VARCHAR(50),
    address VARCHAR(60),
    city VARCHAR(40),
    state VARCHAR(2),
    zip VARCHAR(5),
    debt MEDIUMINT,
    created DATETIME NOT NULL,
    modified DATETIME,
    CONSTRAINT pk_student PRIMARY KEY (student_id)
);

CREATE TABLE IF NOT EXISTS PaymentType (
    type_id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
    ptype VARCHAR(20),
    CONSTRAINT pk_type PRIMARY KEY (type_id)
);

CREATE TABLE IF NOT EXISTS Payment (
    payment_id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    student_id SMALLINT UNSIGNED,
    type_id TINYINT UNSIGNED,
    amount MEDIUMINT UNSIGNED,
    created DATETIME NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_student_id FOREIGN KEY (student_id)
        REFERENCES Studentt (student_id),
    CONSTRAINT fk_payment_type_id FOREIGN KEY (type_id)
        REFERENCES PaymentType (type_id)
);

CREATE TABLE IF NOT EXISTS Instructor (
    instructor_id SMALLINT(5) UNSIGNED ZEROFILL AUTO_INCREMENT NOT NULL,
    fname VARCHAR(30),
    lname VARCHAR(30),
    gender ENUM('M', 'F', 'O'),
    dob DATE,
    telephone VARCHAR(15),
    email VARCHAR(50),
    address VARCHAR(60),
    city VARCHAR(40),
    state VARCHAR(2),
    zip VARCHAR(5),
    created DATETIME NOT NULL,
    modified DATETIME,
    CONSTRAINT pk_instructor PRIMARY KEY (instructor_id)
);

CREATE TABLE IF NOT EXISTS Semester (
    semester_id SMALLINT(5) UNSIGNED ZEROFILL AUTO_INCREMENT NOT NULL,
    semester_year CHAR(4),
    season ENUM('Spring', 'Summer', 'Fall'),
    CONSTRAINT pk_semester PRIMARY KEY (semester_id)
);

CREATE TABLE IF NOT EXISTS Room (
    room_id SMALLINT(5) UNSIGNED ZEROFILL AUTO_INCREMENT NOT NULL,
    room_number VARCHAR(6),
    capacity TINYINT UNSIGNED,
    computer BOOL,
    projector BOOL,
    CONSTRAINT pk_room PRIMARY KEY (room_id)
);

CREATE TABLE IF NOT EXISTS Course (
    course_id SMALLINT(5) UNSIGNED ZEROFILL AUTO_INCREMENT NOT NULL,
    course_code VARCHAR(10),
    course_name VARCHAR(50),
    course_desc TEXT,
    tuition SMALLINT UNSIGNED,
    credits TINYINT UNSIGNED,
    CONSTRAINT pk_course PRIMARY KEY (course_id)
);

CREATE TABLE IF NOT EXISTS Course_Info (
    cinfo_id SMALLINT(5) UNSIGNED ZEROFILL AUTO_INCREMENT NOT NULL,
    room_id SMALLINT UNSIGNED,
    semester_id SMALLINT UNSIGNED,
    instructor_id SMALLINT UNSIGNED,
    CONSTRAINT pk_course_info PRIMARY KEY (cinfo_id),
    CONSTRAINT fk_course_info_room_id FOREIGN KEY (room_id)
        REFERENCES Room (room_id),
    CONSTRAINT fk_course_info_semester_id FOREIGN KEY (semester_id)
        REFERENCES Semester (semester_id),
    CONSTRAINT fk_course_info_instructor_id FOREIGN KEY (instructor_id)
        REFERENCES Instructor (instructor_id)
);

CREATE TABLE IF NOT EXISTS StudentCourse (
    student_id SMALLINT UNSIGNED,
    course_id SMALLINT UNSIGNED,
    cinfo_id SMALLINT UNSIGNED,
    CONSTRAINT pk_studentcourse PRIMARY KEY (student_id , course_id),
    CONSTRAINT fk_studentcourse_student_id FOREIGN KEY (student_id)
        REFERENCES Studentt (student_id),
    CONSTRAINT fk_studentcourse_course_id FOREIGN KEY (course_id)
        REFERENCES Course (course_id),
    CONSTRAINT fk_studentcourse_cinfo_id FOREIGN KEY (cinfo_id)
        REFERENCES Course_Info (cinfo_id)
);

/* Studentt Relation Data Insert */

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Gabriel', 'Cook', 'M', '1986-12-21', '387-334-6126', 'gcook@nexus.com', '354 Yellow St #4R', 'Lincoln', 'NE', '35683', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Rodolfo', 'Livingston', 'M', '1975-04-15', '814-630-8931', 'rodolfo.liv@usnet.us', '862 Bricker St #1L', 'Omaha', 'NE', '38413', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Emilia', 'Bennett', 'F', '1999-06-03', '519-418-5291', 'ebennett@nexus.com', '110 9th St #2L', 'New York City', 'NY', '11249', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Apolena', 'Vaclava', 'F', '1997-05-29', '211-938-3381', 'vaclaca89@nexus.com', '221 Orange Av #7R', 'Boston', 'MA', '55271', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Kyo', 'Minoru', 'F', '1990-01-18', '237-119-3910', 'koyo90@usnet.com', '783 Digg St #3R', 'Boston', 'MA', '55331', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Eamonn', 'Cowal', 'M', '1991-03-27', '782-371-7192', 'ecow@usnet.com', '89 34th St #2L', 'Bismarck', 'ND', '77211', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Vladimira', 'Ester', 'F', '1995-11-02', '389-381-8911', 'esterv@nexus.com', '400 98th St #3L', 'New York City', 'NY', '11255', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Mathilde', 'Margaux', 'F', '1989-10-12', '671-781-3811', 'mm@nexus.com', '13 Domingo Av #8C', 'Lincoln', 'NE', '35111', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Nikolas', 'Red', 'M', '2001-09-18', '839-213-7829', 'red5@nexus.com', '22 Zecker Av #4B', 'Boston', 'MA', '55121', 0, NOW(), NULL);

INSERT INTO Studentt (fname, lname, gender, dob, telephone, email, address, city, state, zip, debt, created, modified)
	VALUES ('Tony', 'Percy', 'M', '1988-04-13', '748-981-2181', 'tony.percy@usnet.com', '45 Washington St #4B', 'Seattle', 'WA', '77221', 0, NOW(), NULL);

/* Instructor Relation Data Insert */

INSERT INTO Instructor (fname, lname, gender, dob, telephone, email, address, city, state, zip, created, modified)
	VALUES ('Lien', 'Elisabeth', 'F', '1973-05-03', '526-738-8930', 'elisabeth.lien@forward.us', '89 Lincoln St #4E', 'New York City', 'NY', '21231', NOW(), NULL);

INSERT INTO Instructor (fname, lname, gender, dob, telephone, email, address, city, state, zip, created, modified)
	VALUES ('Sophie', 'Alida', 'F', '1976-03-25', '892-673-6719', 'sophie.alida@nexus.com', '72 Jefferson St #5R', 'New York City', 'NY', '21949', NOW(), NULL);

INSERT INTO Instructor (fname, lname, gender, dob, telephone, email, address, city, state, zip, created, modified)
	VALUES ('Leopold', 'Rowan', 'M', '1965-01-28', '911-738-8992', 'lrowan@forward.us', '65 9th St #8C', 'Brooklyn', 'NY', '21199', NOW(), NULL);

INSERT INTO Instructor (fname, lname, gender, dob, telephone, email, address, city, state, zip, created, modified)
	VALUES ('Zeph', ' Bill', 'M', '1969-02-10', '782-891-1900', 'zeph.bill@usnet.com', '149 5th St #5D', 'Bronx', 'NY', '24911', NOW(), NULL);

INSERT INTO Instructor (fname, lname, gender, dob, telephone, email, address, city, state, zip, created, modified)
	VALUES ('Nobu', 'Taichi', 'F', '1976-06-17', '892-019-7409', 'nt55@usnet.com', '33 West Av #1R', 'New York City', 'NY', '21982', NOW(), NULL);

/* Course Relation Data Insert */

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('ENGCOMP', 'English Composition', NULL, 350, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('GRAESS', 'Grammar Essentials', NULL, 350, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('INTECWR', 'Introduction to Technical Writing', NULL, 350, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('DIGMARK', 'Digital Marketing', NULL, 500, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('BCMAR101', 'Marketing 101', NULL, 500, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('BCPUBRE', 'Public Relations', NULL, 500, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('MBMEBIC', 'Medical Billing', NULL, 450, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('MBMEDTE', 'Medical Terminology', NULL, 1000, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('FACFIPL', 'Financial Planning', NULL, 1000, 3);

INSERT INTO Course (course_code, course_name, course_desc, tuition, credits)
	VALUES ('BUSWRI', 'Business Writing', NULL, 350, 3);

/* Semester Relation Data Insert */

INSERT INTO Semester (semester_year, season)
	VALUES ('2018', 'Spring');

INSERT INTO Semester (semester_year, season)
	VALUES ('2018', 'Summer');

INSERT INTO Semester (semester_year, season)
	VALUES ('2018', 'Fall');

INSERT INTO Semester (semester_year, season)
	VALUES ('2019', 'Spring');

INSERT INTO Semester (semester_year, season)
	VALUES ('2019', 'Summer');

INSERT INTO Semester (semester_year, season)
	VALUES ('2019', 'Fall');

/* Room Relation Data Insert */

INSERT INTO Room (room_number, capacity, computer, projector)
	VALUES('1025C', 30, TRUE, TRUE);

INSERT INTO Room (room_number, capacity, computer, projector)
	VALUES('1040C', 30, FALSE, FALSE);

INSERT INTO Room (room_number, capacity, computer, projector)
	VALUES('925B', 60, TRUE, TRUE);

INSERT INTO Room (room_number, capacity, computer, projector)
	VALUES('925A', 60, TRUE, TRUE);

INSERT INTO Room (room_number, capacity, computer, projector)
	VALUES('350A', 20, FALSE, FALSE);
