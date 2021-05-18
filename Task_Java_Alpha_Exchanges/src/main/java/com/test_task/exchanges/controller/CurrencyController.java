package com.test_task.exchanges.controller;

import com.test_task.exchanges.service.GiphyRestApiService;
import com.test_task.exchanges.service.OpenExchangeRestApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import static com.test_task.exchanges.config.GeneralConfigs.DEFAULT_COIN;

@Controller
@RequestMapping("/rest/exchanges")
public class CurrencyController {

    @GetMapping
    public RedirectView getCurrencyComparison(@RequestParam(defaultValue = DEFAULT_COIN) String currency) throws Exception {

        String currencyUpper = currency.toUpperCase();
        RedirectView redirectView;

        Double currencyToday = OpenExchangeRestApiService.getCurrency(OpenExchangeRestApiService.prepareTodayUrl(), currencyUpper);
        Double currencyYesterday = OpenExchangeRestApiService.getCurrency(OpenExchangeRestApiService.prepareYesterdayUrl(), currencyUpper);

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
