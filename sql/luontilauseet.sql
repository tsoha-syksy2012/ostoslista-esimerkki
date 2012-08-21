-- Käyttäjä-taulun luonti
CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar UNIQUE,
  password varchar
);

-- Ostoslista-taulun luonti
CREATE TABLE lists (
  id serial PRIMARY KEY,
  user_id integer REFERENCES users(id) ON DELETE CASCADE,
  name varchar NOT NULL,
  is_default boolean DEFAULT FALSE
);

-- Tavara-taulun luonti
CREATE TABLE items (
  id serial PRIMARY KEY,
  list_id integer REFERENCES lists(id) ON DELETE CASCADE,
  name varchar NOT NULL
);
