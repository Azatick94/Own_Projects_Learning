package com.test_task.exchanges.controller;

import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.Currency;
import com.test_task.exchanges.service.GiphyRestApiService;
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
import static com.test_task.exchanges.util.AppUtil.getCurrencyFromOpenExchangeRates;

@Controller
@Import(FeignClientsConfiguration.class)
public class CurrencyFeignController {

    OpenExchangeClient openExchangeClient;

    @Autowired
    public CurrencyFeignController(Decoder decoder, Encoder encoder) {
        openExchangeClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(OpenExchangeClient.class));
    }

    public CurrencyFeignController(OpenExchangeClient openExchangeClient) {
        this.openExchangeClient = openExchangeClient;
    }

    @GetMapping(value = "rest/feign/exchanges")
    public RedirectView getCurrencyComparison(@RequestParam(defaultValue = DEFAULT_COIN) String coin) throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        RedirectView redirectView;

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
            redirectView = new RedirectView(GiphyRestApiService.prepareGiphyUrlRich());
        } else {
            // return from https://giphy.com/search/broke
            redirectView = new RedirectView(GiphyRestApiService.prepareGiphyUrlBroke());
        }
        return redirectView;
    }
}
