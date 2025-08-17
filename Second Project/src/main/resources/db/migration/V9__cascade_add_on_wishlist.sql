alter table wishlist
drop foreign key fk_wishlist_on_products;

alter table wishlist
    add constraint fk_wishlist_on_products
        foreign key (products_id) references products (id)
            on delete cascade;