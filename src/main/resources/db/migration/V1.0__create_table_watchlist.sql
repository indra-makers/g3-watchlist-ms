create table watchlist(
    watchlist_id serial primary key,
    user_id bigint NOT NULL,
    watchlist_name varchar(25) NOT NULL,
    watchlist_description text,
    watchlist_privacy boolean DEFAULT FALSE
)