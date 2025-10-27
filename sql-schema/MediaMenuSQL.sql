CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
	username VARCHAR,
	hashed_password VARCHAR NOT NULL,
	email VARCHAR NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE artist(
	id SERIAL PRIMARY KEY,
	mbid VARCHAR(36) UNIQUE,
	artist_name VARCHAR NOT NULL
);

CREATE TABLE release_group (
	id SERIAL PRIMARY KEY,
	mbid VARCHAR(36) UNIQUE,
	artist_id INTEGER REFERENCES artist(id) NOT NULL,
	title VARCHAR NOT NULL,
	format VARCHAR
);

CREATE TABLE track (
	id SERIAL PRIMARY KEY,
	mbid VARCHAR(36) UNIQUE,
	release_id INTEGER REFERENCES release_group(id),
	artist_id INTEGER REFERENCES artist(id),
	title VARCHAR NOT NULL
);

CREATE TABLE scrobble(
	id SERIAL PRIMARY KEY,
	user_id INTEGER REFERENCES app_user(id) NOT NULL,
	track_id INTEGER REFERENCES track(id) NOT NULL,
	scrobbles INTEGER,
	listened_at TIMESTAMP,
	first_listened_at TIMESTAMP
);


CREATE TABLE release_rating(
	user_id INTEGER REFERENCES app_user(id) NOT NULL,
	release_id INTEGER REFERENCES release_group(id) NOT NULL,
	rating INTEGER check (rating between 0 and 10),
	rated_at TIMESTAMP,
	PRIMARY KEY (user_id, release_id)
);

CREATE TABLE track_rating(
	user_id INTEGER REFERENCES app_user(id) NOT NULL,
	track_id INTEGER REFERENCES track(id) NOT NULL,
	rating INTEGER check (rating between 0 and 10),
	rated_at TIMESTAMP,
	PRIMARY KEY (user_id, track_id)
)
