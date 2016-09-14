-- drop existing tables (if exists)
DROP TABLE POKEMON_ATTACK;
DROP TABLE POKEMON;
DROP TABLE ATTACK;
DROP TABLE POKEMON_TYPE;

-- create new database tables

CREATE TABLE POKEMON_TYPE (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25),
    PRIMARY KEY(id)
);

CREATE TABLE ATTACK (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY(id)
);


CREATE TABLE POKEMON (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25),
    hit_points INT,
    combat_power INT,
    type_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY (type_id) REFERENCES POKEMON_TYPE(id)
);

CREATE TABLE POKEMON_ATTACK(
    pokemon_id INT,
    attack_id INT,
    FOREIGN KEY (pokemon_id) REFERENCES POKEMON(id),
    FOREIGN KEY (attack_id) REFERENCES ATTACK(id)
);


-- insert some dummy data

INSERT INTO POKEMON_TYPE (name) VALUES
('GRASS'),
('FIRE'),
('WATER'),
('NORMAL'),
('FLYING'),
('POSION'),
('ELECTRIC'),
('ROCK'),
('PSYCHIC'),
('GROUND'),
('ICE'),
('FIGHTING'),
('BUG'),
('GHOST'),
('DRAGON'),
('FAIRY');

INSERT INTO ATTACK (name) VALUES ('Thunderbolt');

INSERT INTO POKEMON (name, hit_points, combat_power, type_id) VALUES ('Pikachu', 55, 45, 7);

INSERT INTO POKEMON_ATTACK (pokemon_id, attack_id) VALUES (1,1);
