create table tbl_watchlists(
    id_watchlist serial primary key,
    id_user bigint NOT NULL,
    watchlist_name varchar(25) NOT NULL,
    watchlist_description text,
    is_private boolean DEFAULT FALSE
)