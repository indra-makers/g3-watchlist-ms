package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.config.ErrorCodes;
import com.co.indra.coinmarketcap.watchlist.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.watchlist.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.watchlist.model.entities.CoinPriceAlert;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchList;
import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinPriceAlertRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListCoinRepository;
import com.co.indra.coinmarketcap.watchlist.repositories.WatchListRepository;
import com.co.indra.coinmarketcap.watchlist.userApi.models.UserApi;
import com.co.indra.coinmarketcap.watchlist.userApi.service.UserApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListService {
	@Autowired
	private WatchListRepository watchListRepository;
	@Autowired
	private WatchListCoinRepository watchListCoinRepository;
	@Autowired
	private CoinPriceAlertRepository coinPriceAlertRepository;

	@Autowired
	private UserApiService users;

	public void createWatchList(WatchList watchList) {
		UserApi userApi;
		userApi = users.extractUser(watchList.getIdUser());
		if (watchList.getIdUser() == userApi.getUserId()) {
			watchListRepository.createWatchlist(watchList);
		} else {
			throw new NotFoundException(ErrorCodes.USER_DOES_NOT_EXIST.getMessage());
		}
	}

	public UserApi getUser(Long idUser){

		return users.extractUser(idUser);

	}

	public void addCoinToWatchList(WatchListCoin watchListCoin, Long idWatchList) {
		if (!watchListCoinRepository.findWatchListCoinBySymbol(watchListCoin.getSymbol()).isEmpty()) {
			throw new BusinessExceptions(ErrorCodes.SYMBOL_EXISTS_IN_WATCHLIST);
		} else if (watchListCoin.getSymbol() == null) {
			throw new BusinessExceptions(ErrorCodes.MISSING_COIN_PARAMETERS);
		}
		watchListCoinRepository.addCoinToWatchList(watchListCoin, idWatchList);
	}

	public List<WatchList> getWatchlist(Long idUser) {
		if (watchListRepository.findWatchListByUserId(idUser).isEmpty()) {
			throw new NotFoundException(ErrorCodes.USER_DOES_NOT_EXIST);
		}
		return watchListRepository.findWatchListByUserId(idUser);
	}

	// Eliminar Watchlist
	public void removeWatchlist(Long idWatchList) {

		if (watchListRepository.findWatchListById(idWatchList).isEmpty()) {
			throw new NotFoundException(ErrorCodes.WATCHlLIST_NOT_EXIST);
		}
		if (watchListCoinRepository.findWatchListCoinByWatchlist(idWatchList).isEmpty()) {
			watchListRepository.deleteWatchlist(idWatchList);
		} else {
			throw new BusinessExceptions(ErrorCodes.WATCHLIST_RELATED_TO_A_CURRENCY);
		}

	}

	public void addCoinAlertToWatchlist(Long idWatchlistCoin, CoinPriceAlert coinPriceAlert) {
		if (watchListCoinRepository.findWatchListCoinByCoin(idWatchlistCoin).isEmpty()) {
			throw new NotFoundException(ErrorCodes.WATCHlLIST_NOT_EXIST);
		}
		if (!coinPriceAlertRepository
				.findCoinPriceAlertBySymbolAndIdWatchListCoin(coinPriceAlert.getSymbol(), idWatchlistCoin.intValue())
				.isEmpty()) {
			throw new BusinessExceptions(ErrorCodes.ONLY_ONE_GOAL_PER_COIN);
		}
		coinPriceAlertRepository.addCoinAlertToWatchlist(idWatchlistCoin, coinPriceAlert);
	}
}
