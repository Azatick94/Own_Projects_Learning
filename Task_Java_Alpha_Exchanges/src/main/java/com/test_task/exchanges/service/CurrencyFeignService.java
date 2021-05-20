package com.test_task.exchanges.service;

import com.test_task.exchanges.client.GiphyClient;
import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.dto.open_exchange.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.test_task.exchanges.util.AppUtil.*;

@Service
public class CurrencyFeignService {

    private final OpenExchangeClient openExchangeClient;
    private final GiphyClient giphyClient;

    @Value("${openexchange.app_id}")
    private String appId;

    @Value("${giphy.api_key}")
    private String apiKey;

    public CurrencyFeignService(OpenExchangeClient openExchangeClient, GiphyClient giphyClient) {
        this.openExchangeClient = openExchangeClient;
        this.giphyClient = giphyClient;
    }

    public Gif getGiphy(String coin) throws NoSuchFieldException, IllegalAccessException {
        Gif giphy;

        // getting today currency
        Currency currencyObjectToday = openExchangeClient.getTodayResult(appId);
        Double currencyToday = getCurrencyFromOpenExchangeRates(currencyObjectToday, coin);

        // getting yesterday currency
        Currency currencyObjectYesterday = openExchangeClient.getYesterdayResult(getPreviousDate(), appId);
        Double currencyYesterday = getCurrencyFromOpenExchangeRates(currencyObjectYesterday, coin);

        if (currencyToday > currencyYesterday) {
            giphy = giphyClient.getRichLink(apiKey);
        } else {
            giphy = giphyClient.getBrokeLink(apiKey);
        }
        return giphy;
    }
}
