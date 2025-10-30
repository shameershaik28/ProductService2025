CREATE TABLE products
(
    id            BINARY(16)   NOT NULL,
    name          VARCHAR(255) NULL,
    category      VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);