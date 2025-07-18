CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, email, password)
VALUES ('admin', 'teste@teste.com', '$2a$10$I5yDatiRbJt./eKtrXM0cuC32rvxAcsQLbOgpJTaSakJVWNKS6cr6');
