package com.test_task.exchanges.controller;

import com.test_task.exchanges.client.GiphyClient;
import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.dto.open_exchange.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;

import static com.test_task.exchanges.test_data.TestData.getAnyCurrency;
import static com.test_task.exchanges.test_data.TestData.getAnyGif;
import static com.test_task.exchanges.util.AppUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyFeignControllerTest {

    @MockBean
    OpenExchangeClient openExchangeClient;

    @MockBean
    GiphyClient giphyClient;

    @Autowired
    CurrencyFeignController currencyController;

    @Test
    void testCurrencyFeignController() throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        Currency currencyToday = getAnyCurrency(75.0, "rub");
        Currency currencyYesterday = getAnyCurrency(70.0, "rub");

        Gif gifRich = getAnyGif("rich");
        Gif gifBroke = getAnyGif("broke");

        // mocking openExchangeClient
        when(openExchangeClient.getTodayResult(any(URI.class)))
                .thenReturn(currencyToday);
        when(openExchangeClient.getYesterdayResult(new URI(prepareYesterdayUrl())))
                .thenReturn(currencyYesterday);

        // mocking giphyClient
        when(giphyClient.getRichLink(new URI(prepareGiphyUrlRich())))
                .thenReturn(gifRich);
        when(giphyClient.getBrokeLink(new URI(prepareGiphyUrlBroke())))
                .thenReturn(gifBroke);

        // testing output
        RedirectView redirectView = currencyController.getCurrencyComparison("RUB");
        assertEquals("rich", redirectView.getUrl());
    }
}