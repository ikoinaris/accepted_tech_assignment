CREATE TABLE match (
    id INT PRIMARY KEY ,
    description VARCHAR(255),
    match_date DATE,
    match_time TIME,
    team_a VARCHAR(100),
    team_b VARCHAR(100),
    sport INT
);

CREATE TABLE match_odds (
    id INT PRIMARY KEY ,
    match_id INT NOT NULL,
    specifier VARCHAR(10),
    odd DOUBLE(10,1),
    FOREIGN KEY (match_id) REFERENCES match(id)
);