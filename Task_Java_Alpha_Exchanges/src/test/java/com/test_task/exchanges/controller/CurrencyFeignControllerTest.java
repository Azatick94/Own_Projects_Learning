package com.test_task.exchanges.controller;

import com.test_task.exchanges.client.GiphyClient;
import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.dto.open_exchange.Currency;
import com.test_task.exchanges.service.CurrencyFeignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.test_task.exchanges.test_data.TestData.getAnyCurrency;
import static com.test_task.exchanges.test_data.TestData.getAnyGif;
import static com.test_task.exchanges.util.AppUtil.getPreviousDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest()
class CurrencyFeignControllerTest {

    @MockBean
    OpenExchangeClient openExchangeClient;

    @MockBean
    GiphyClient giphyClient;

    @Autowired
    CurrencyFeignService service;

    @Value("${openexchange.app_id}")
    private String appId;

    @Value("${giphy.api_key}")
    private String apiKey;

    Gif gifRich = getAnyGif("rich");
    Gif gifBroke = getAnyGif("broke");

    @Test
    void testCurrencyControllerRich() throws NoSuchFieldException, IllegalAccessException {

        Currency currencyToday = getAnyCurrency(75.0, "rub");
        Currency currencyYesterday = getAnyCurrency(70.0, "rub");

        // mocking openExchangeClient
        when(openExchangeClient.getTodayResult(appId))
                .thenReturn(currencyToday);
        when(openExchangeClient.getYesterdayResult(getPreviousDate(), appId))
                .thenReturn(currencyYesterday);

        // mocking giphyClient
        when(giphyClient.getRichLink(apiKey))
                .thenReturn(gifRich);
        when(giphyClient.getBrokeLink(apiKey))
                .thenReturn(gifBroke);

        Gif giphy = service.getGiphy("RUB");
        assertEquals("rich", giphy.getData().get(0).getBitlyGifUrl());
    }

    @Test
    void testCurrencyControllerBroke() throws NoSuchFieldException, IllegalAccessException {

        Currency currencyToday = getAnyCurrency(70.0, "rub");
        Currency currencyYesterday = getAnyCurrency(75.0, "rub");

        // mocking openExchangeClient
        when(openExchangeClient.getTodayResult(appId))
                .thenReturn(currencyToday);
        when(openExchangeClient.getYesterdayResult(getPreviousDate(), appId))
                .thenReturn(currencyYesterday);

        // mocking giphyClient
        when(giphyClient.getRichLink(apiKey))
                .thenReturn(gifRich);
        when(giphyClient.getBrokeLink(apiKey))
                .thenReturn(gifBroke);

        Gif giphy = service.getGiphy("RUB");
        assertEquals("broke", giphy.getData().get(0).getBitlyGifUrl());
    }
}