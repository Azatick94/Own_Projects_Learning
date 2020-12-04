DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id int NOT NULL UNIQUE,
    promocode_id int
);


