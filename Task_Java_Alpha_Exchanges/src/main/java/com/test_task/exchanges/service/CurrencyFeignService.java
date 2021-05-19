package com.test_task.exchanges.service;

import com.test_task.exchanges.client.GiphyClient;
import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.dto.open_exchange.Currency;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

import static com.test_task.exchanges.util.AppUtil.*;
import static com.test_task.exchanges.util.AppUtil.prepareGiphyUrlBroke;

@Service
public class CurrencyFeignService {

    private final OpenExchangeClient openExchangeClient;
    private final GiphyClient giphyClient;

    public CurrencyFeignService(OpenExchangeClient openExchangeClient, GiphyClient giphyClient) {
        this.openExchangeClient = openExchangeClient;
        this.giphyClient = giphyClient;
    }

    public Gif getGiphy(String coin) throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        Gif giphy;

        // getting today currency
        Currency currencyObjectToday = openExchangeClient.getTodayResult(
                new URI(prepareTodayUrl()));
        Double currencyToday = getCurrencyFromOpenExchangeRates(currencyObjectToday, coin);

        // getting yesterday currency
        Currency currencyObjectYesterday = openExchangeClient.getYesterdayResult(
                new URI(prepareYesterdayUrl()));
        Double currencyYesterday = getCurrencyFromOpenExchangeRates(currencyObjectYesterday, coin);

        if (currencyToday > currencyYesterday) {
            // return from https://giphy.com/search/rich
            giphy = giphyClient.getRichLink(new URI(prepareGiphyUrlRich()));
        } else {
            // return from https://giphy.com/search/broke
            giphy = giphyClient.getBrokeLink(new URI(prepareGiphyUrlBroke()));
        }
        return giphy;
    }
}
