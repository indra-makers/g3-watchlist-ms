create table tbl_coin_price_alerts(
    id_coin_price_alert serial primary key,
    goal_price double precision NOT NULL,
    symbol varchar(5) NOT NULL,
    id_watchlist_coin int NOT NULL,
    id_user bigint NOT NULL,
    CONSTRAINT fk_id_watchlist_tbl_coin_price_alerts FOREIGN KEY (id_watchlist_coin) REFERENCES tbl_watchlists_coins(id_watchlist_coin)
)