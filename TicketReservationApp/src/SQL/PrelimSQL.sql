CREATE TABLE MOVIE (
  Name				int(15) NOT NULL,
  Year         			int(4),
  PRIMARY key (Name, Year)
);

CREATE TABLE THEATRE (
  Name				int(15) NOT NULL,
  PRIMARY key (Name)
);

CREATE TABLE THEATREMOVIES (
	MovieName 			varchar(50),
    TheatreName			varchar(50),
    
    PRIMARY KEY (MovieName, TheatreName),
	FOREIGN key (MovieName) REFERENCES Movie(Name)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	FOREIGN key (TheatreName) REFERENCES Theatre(Name)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE THEATRE (
  Date				Date NOT NULL,
  PRIMARY key (DateTime)
);

CREATE TABLE THEATREMOVIESHOWTIMES (
	MovieName 			varchar(50),
    TheatreName			varchar(50),
    Showtime			Date,
    
    PRIMARY KEY (MovieName, TheatreName),
	FOREIGN key (MovieName) REFERENCES Movie(Name)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	FOREIGN key (TheatreName) REFERENCES THEATREMOVIES(Name)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	FOREIGN key (Showtime) REFERENCES THEATREMOVIES(Date)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


