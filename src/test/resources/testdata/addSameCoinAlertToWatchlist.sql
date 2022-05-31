INSERT INTO tbl_watchlists(id_watchlist, id_user, watchlist_name, watchlist_description, is_private) values(999, 5,'Mi primera watchlist','Aqui va la descripcion',false);
INSERT INTO tbl_watchlists_coins(id_watchlist_coin, id_watchlist, symbol) values(999,999, 'ZZZ');
INSERT INTO tbl_coin_price_alerts(id_coin_price_alert, goal_price, symbol, id_watchlist_coin) values (666, 2000, 'ZZZ', 999);