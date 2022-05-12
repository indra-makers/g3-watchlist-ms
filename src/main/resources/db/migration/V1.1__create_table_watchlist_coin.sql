create table watchlist_coin(
    watchlist_coin_id serial primary key,
    watchlist_id bigint NOT NULL,
    symbol varchar(25) NOT NULL,
    CONSTRAINT fk_watchlist_id FOREIGN KEY (watchlist_id) REFERENCES watchlist(watchlist_id)
)