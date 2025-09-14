CREATE SCHEMA IF NOT EXISTS void_api;

CREATE TABLE IF NOT EXISTS void_api.void_role(
  id serial PRIMARY KEY,
  role VARCHAR (50) UNIQUE NOT NULL
);

INSERT INTO void_api.void_role(role)
VALUES ('VOID_DWELLER');
INSERT INTO void_api.void_role(role)
VALUES ('VOID_LORD');
INSERT INTO void_api.void_role(role)
VALUES ('VOID_WHISPER');
INSERT INTO void_api.void_role(role)
VALUES ('VOID_JAILER');
INSERT INTO void_api.void_role(role)
VALUES ('ADMIN');

CREATE TABLE IF NOT EXISTS void_api.account(
  id serial PRIMARY KEY,
  username VARCHAR (50) UNIQUE NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES void_api.void_role (id)
);

INSERT INTO void_api.account(username, role_id)
VALUES ('cb_Test_1', 1);

CREATE TABLE IF NOT EXISTS void_api.account_details(
  id serial PRIMARY KEY,
  creation_date VARCHAR (100) NOT NULL,
  first_name VARCHAR (50),
  last_name VARCHAR (50),
  last_login_date VARCHAR (100) NOT NULL,
  phone_number VARCHAR (25),
  account_id INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES void_api.account (id)
);

CREATE TABLE IF NOT EXISTS void_api.account_security(
  id serial PRIMARY KEY,
  email VARCHAR (100) UNIQUE NOT NULL,
  login_token VARCHAR (200) UNIQUE NOT NULL,
  password VARCHAR (100) NOT NULL,
  account_id INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES void_api.account (id)
);

CREATE TABLE IF NOT EXISTS void_api.cry(
  id serial PRIMARY KEY,
  text VARCHAR (240) NOT NULL,
  creation_date VARCHAR (50) NOT NULL,
  expiration_date VARCHAR (50) NOT NULL,
  last_amplified_date VARCHAR (50),
  is_root_cry VARCHAR (50) NOT NULL,
  account_id INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES void_api.account (id)
);

CREATE TABLE IF NOT EXISTS void_api.invite_code(
  id serial PRIMARY KEY,
  code VARCHAR (50) UNIQUE NOT NULL,
  account_id INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES void_api.account (id)
);