create table tbl_watchlists_coins(
    id_watchlist_coin serial primary key,
    id_watchlist bigint NOT NULL,
    symbol varchar(5) NOT NULL,
    CONSTRAINT fk_id_watchlist FOREIGN KEY (id_watchlist) REFERENCES tbl_watchlists(id_watchlist)
)