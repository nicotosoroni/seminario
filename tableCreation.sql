CREATE DATABASE ceramica_db;

USE ceramica_db;

CREATE TABLE Students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    enrollee_course INT
);

CREATE TABLE Piece (
    piece_id INT PRIMARY KEY AUTO_INCREMENT,
    piece_name VARCHAR(100),
    status VARCHAR(50),
    student_id INT,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

CREATE TABLE Courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100),
    instructor_id INT
);

CREATE TABLE ProductionProcess (
    piece_id INT,
    current_status VARCHAR(50),
    update_date DATE,
    PRIMARY KEY (piece_id, current_status),
    FOREIGN KEY (piece_id) REFERENCES Piece(piece_id)
);