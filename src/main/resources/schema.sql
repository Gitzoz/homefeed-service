CREATE TABLE IF NOT EXISTS greeting
(
    id             BIGINT PRIMARY KEY,
    greeting_text  VARCHAR(255),
    promotion_link VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product_promotion
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255)  NOT NULL,
    promotion_text   VARCHAR(1000) NOT NULL,
    teaser_image_url VARCHAR(512)  NOT NULL
);


CREATE TABLE IF NOT EXISTS products
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    promotion_id BIGINT           NOT NULL,
    name         VARCHAR(255)     NOT NULL,
    price        DOUBLE PRECISION NOT NULL,
    image_url    VARCHAR(512)     NOT NULL,

    CONSTRAINT fk_products_promotion
        FOREIGN KEY (promotion_id)
            REFERENCES product_promotion (id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
);


CREATE INDEX IF NOT EXISTS idx_products_promotion_id
    ON products (promotion_id);


CREATE TABLE IF NOT EXISTS sales
(
    id        BIGINT PRIMARY KEY,
    deep_link VARCHAR(255),
    image_url VARCHAR(255)
);