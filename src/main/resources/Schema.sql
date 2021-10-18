CREATE DATABASE IF NOT EXISTS champion_db DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS participant (
id BIGINT(20) NOT NULL AUTO_INCREMENT ,
participant_name VARCHAR(255) NOT NULL ,
participant_email VARCHAR(500) NOT NULL,
creation_date DATETIME NOT NULL ,
modification_date DATETIME NOT NULL 
, PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS league (
id BIGINT(20) NOT NULL AUTO_INCREMENT ,
league_champion BIGINT(20) ,
creation_date DATETIME NOT NULL ,
modification_date DATETIME NOT NULL ,
PRIMARY KEY (id),
CONSTRAINT FK_Winner_Participant FOREIGN KEY (league_champion)
REFERENCES participant(id) ON DELETE CASCADE);


CREATE TABLE IF NOT EXISTS round (
id BIGINT(20) NOT NULL AUTO_INCREMENT ,
round_status VARCHAR(255) DEFAULT 'OPEN' ,
related_league BIGINT(20) NOT NULL,
creation_date DATETIME NOT NULL ,
modification_date DATETIME NOT NULL ,
PRIMARY KEY (id),
CONSTRAINT FK_Round_Related_League FOREIGN KEY (related_league)
REFERENCES league(id) ON DELETE CASCADE);


CREATE TABLE IF NOT EXISTS tennis_match (
id BIGINT(20) NOT NULL AUTO_INCREMENT ,
participant_1 BIGINT(20) NOT NULL,
participant_2 BIGINT(20) NOT NULL,
match_time datetime,
match_winner BIGINT(20) ,
related_round BIGINT(20) NOT NULL,
participant_score_1 INT UNSIGNED,
participant_score_2 INT UNSIGNED,
creation_date DATETIME NOT NULL ,
modification_date DATETIME NOT NULL ,
PRIMARY KEY (id),
CONSTRAINT FK_Participant_1 FOREIGN KEY (participant_1)
REFERENCES participant(id) ON DELETE CASCADE,
CONSTRAINT FK_Participant_2 FOREIGN KEY (participant_2)
REFERENCES participant(id) ON DELETE CASCADE,
CONSTRAINT FK_Match_Winner FOREIGN KEY (match_winner)
REFERENCES participant(id) ON DELETE CASCADE,
CONSTRAINT FK_Related_Round FOREIGN KEY (related_round)
REFERENCES round(id) ON DELETE CASCADE
);




