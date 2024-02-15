CREATE SEQUENCE
    IF NOT EXISTS student_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE student
(
    id        BIGINT       NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE SEQUENCE
    IF NOT EXISTS grade_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE grade
(
    id         BIGINT  NOT NULL,
    student_id BIGINT  NOT NULL,
    point      DECIMAL NOT NULL,
    CONSTRAINT pk_grade PRIMARY KEY (id)
);

ALTER TABLE grade
    ADD CONSTRAINT FK_GRADE_ON_STUDENT
        FOREIGN KEY (student_id)
            REFERENCES student (id);