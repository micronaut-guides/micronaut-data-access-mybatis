DROP TABLE IF EXISTS GENRE;

CREATE TABLE GENRE (
  id    BIGINT SERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(255)              NOT NULL
);

INSERT INTO GENRE VALUES (1, 'Microservices');
INSERT INTO GENRE VALUES (2, 'DevOps');
