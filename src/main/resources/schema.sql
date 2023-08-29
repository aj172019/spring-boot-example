 DROP TABLE IF EXISTS shop CASCADE;
CREATE TABLE IF NOT EXISTS shop
(
    shop_id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    shop_name         VARCHAR(255) NOT NULL,
    shop_address      VARCHAR(255) NOT NULL,
    shop_detail_address VARCHAR(255) NOT NULL,
    shop_tier         VARCHAR(255) NOT NULL,
    shop_tier_code    VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS review CASCADE;
CREATE TABLE IF NOT EXISTS review
(
    review_id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    review_contents   VARCHAR(255) NOT NULL,
    shop_id           BIGINT,
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id)
);

DROP TABLE IF EXISTS shedlock CASCADE;
CREATE TABLE IF NOT EXISTS shedlock
(
    name              VARCHAR(64),
    lock_until        TIMESTAMP NULL,
    locked_at         TIMESTAMP NULL,
    locked_by         VARCHAR(255),
    PRIMARY KEY (name)
);
