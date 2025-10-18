CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
	username VARCHAR NOT NULL,
	hashed_password VARCHAR NOT NULL,
	email VARCHAR NOT NULL,
	profile_picture VARCHAR,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE artist(
	id SERIAL PRIMARY KEY,
	mbid VARCHAR(36),
	artist_name VARCHAR NOT NULL
);

CREATE TABLE music_release (
	id SERIAL PRIMARY KEY,
	mbid VARCHAR(36),
	artist_id INTEGER REFERENCES artist(id) NOT NULL,
	title VARCHAR NOT NULL
);

CREATE TABLE track (
	id SERIAL PRIMARY KEY,
	mbid VARCHAR(36),
	release_id INTEGER REFERENCES music_release(id),
	artist_id INTEGER REFERENCES artist(id),
	title VARCHAR NOT NULL
);

CREATE TABLE scrobbles(
	id SERIAL PRIMARY KEY,
	user_id INTEGER REFERENCES app_user(id) NOT NULL,
	track_id INTEGER REFERENCES track(id) NOT NULL,
	scrobbles INTEGER,
	listened_at TIMESTAMP,
	first_listened_at TIMESTAMP
);

CREATE TABLE track_rating(
	user_id INTEGER REFERENCES app_user(id) NOT NULL,
	track_id INTEGER REFERENCES track(id) NOT NULL,
	mbid VARCHAR(36),
	rating INTEGER,
	rated_at TIMESTAMP,
	PRIMARY KEY (user_id, track_id)
);

CREATE TABLE release_rating(
	user_id INTEGER REFERENCES app_user(id) NOT NULL,
	release_id INTEGER REFERENCES music_release(id) NOT NULL,
	mbid VARCHAR(36),
	rating INTEGER,
	rated_at TIMESTAMP,
	PRIMARY KEY (user_id, release_id)
);