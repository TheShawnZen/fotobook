DROP DATABASE IF EXISTS fotobook;

CREATE DATABASE fotobook;
USE fotobook;

ALTER SCHEMA `fotobook` DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS friend;
DROP TABLE IF EXISTS friendRequest;
DROP TABLE IF EXISTS picture;
DROP TABLE IF EXISTS user;

CREATE TABLE user(
	id				INT				NOT NULL AUTO_INCREMENT,
	email			VARCHAR(255)	NOT NULL,
	username		VARCHAR(255)	NOT NULL,
	pwd				VARCHAR(255)	NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (username),
    UNIQUE (email)
);

CREATE TABLE picture(
    id              INT             NOT NULL AUTO_INCREMENT,
    userID          INT             NOT NULL,
    img				LONGBLOB		NOT NULL,
    privacy			VARCHAR(255)	NOT NULL,
    caption         VARCHAR(255)        NULL,
    PRIMARY KEY(id, userID),
    FOREIGN KEY(userID) REFERENCES user(id)
);

CREATE TABLE friendRequest(
    id              INT             NOT NULL AUTO_INCREMENT,
    userSenderID    INT             NOT NULL,
    userReceiverID  INT             NOT NULL,
    PRIMARY KEY(id, userSenderID,userReceiverID),
    FOREIGN KEY(userSenderID) REFERENCES user(id),
    FOREIGN KEY(userReceiverID) REFERENCES user(id),
    UNIQUE(userSenderID, userReceiverID)
);

CREATE TABLE friend(
    id              INT             NOT NULL AUTO_INCREMENT,
    userOneID       INT             NOT NULL,
    userTwoID       INT             NOT NULL,
    PRIMARY KEY(id, userOneID,userTwoID),
    FOREIGN KEY(userOneID) REFERENCES user(id),
    FOREIGN KEY(userTwoID) REFERENCES user(id)
);

CREATE TABLE likes(
    id              INT             NOT NULL AUTO_INCREMENT,
    userID          INT             NOT NULL,
    pictureID       INT             NOT NULL,
    PRIMARY KEY(id, userID,pictureID),
    FOREIGN KEY(userID) REFERENCES user(id),
    FOREIGN KEY(pictureID) REFERENCES picture(id)
);

CREATE TABLE comment(
    id              INT             NOT NULL AUTO_INCREMENT,
    userID          INT             NOT NULL,
    pictureID       INT             NOT NULL,
    msg             VARCHAR(255)    NOT NULL,
    PRIMARY KEY(id, userID,pictureID),
    FOREIGN KEY(userID) REFERENCES user(id),
    FOREIGN KEY(pictureID) REFERENCES picture(id)
);
