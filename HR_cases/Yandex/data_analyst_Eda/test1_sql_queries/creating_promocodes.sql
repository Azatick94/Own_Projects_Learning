DROP TABLE IF EXISTS promocodes;

CREATE TABLE promocodes (
                        id int NOT NULL UNIQUE,
                        name varchar,
                        discount int
                        );
-- name - название промокода, discount - скидка в рублях
