package com.test_task.exchanges.controller;

import com.test_task.exchanges.client.GiphyClient;
import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.dto.open_exchange.Currency;
import com.test_task.exchanges.util.AppUtil;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;

import static com.test_task.exchanges.config.GeneralConfigs.*;
import static com.test_task.exchanges.util.AppUtil.*;

@Controller
@Import(FeignClientsConfiguration.class)
public class CurrencyFeignController {

    OpenExchangeClient openExchangeClient;
    GiphyClient giphyClient;

    @Autowired
    public CurrencyFeignController(Decoder decoder, Encoder encoder) {
        openExchangeClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(OpenExchangeClient.class));
        giphyClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(GiphyClient.class));
    }

    @GetMapping(value = "rest/feign/exchanges")
    public RedirectView getCurrencyComparison(@RequestParam(defaultValue = DEFAULT_COIN) String coin) throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        Gif giphy;

        // getting today currency
        Currency currencyObjectToday = openExchangeClient.getTodayResult(
                new URI(EXCHANGE_BASE_URL + "/latest.json?app_id=" + EXCHANGE_APP_ID));
        Double currencyToday = getCurrencyFromOpenExchangeRates(currencyObjectToday, coin);

        // getting yesterday currency
        Currency currencyObjectYesterday = openExchangeClient.getYesterdayResult(
                new URI(EXCHANGE_BASE_URL + "/historical/" + AppUtil.getPreviousDate() + ".json?app_id=" + EXCHANGE_APP_ID));
        Double currencyYesterday = getCurrencyFromOpenExchangeRates(currencyObjectYesterday, coin);

        if (currencyToday > currencyYesterday) {
            // return from https://giphy.com/search/rich
            giphy = giphyClient.getRichLink(new URI(prepareGiphyUrlRich()));
        } else {
            // return from https://giphy.com/search/broke
            giphy = giphyClient.getBrokeLink(new URI(prepareGiphyUrlBroke()));
        }
        return new RedirectView(giphy.getData().get(0).getBitlyGifUrl());
    }
}
