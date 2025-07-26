CREATE TABLE wishlist
(
    user_id     BIGINT NOT NULL,
    products_id BIGINT NOT NULL,
    CONSTRAINT pk_wishlist PRIMARY KEY (user_id, products_id)
);

ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_on_products FOREIGN KEY (products_id) REFERENCES products (id);

ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_on_user FOREIGN KEY (user_id) REFERENCES user (id);