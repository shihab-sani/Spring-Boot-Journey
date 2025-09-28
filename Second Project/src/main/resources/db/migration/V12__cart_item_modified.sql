alter table cart_items
    alter column cart_id set default ((uuid_to_bin(uuid())));

