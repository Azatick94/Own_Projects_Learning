package com.test_task.exchanges.controller;

import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.service.CurrencyFeignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CurrencyFeignController {

    private final CurrencyFeignService service;

    public CurrencyFeignController(CurrencyFeignService service) {
        this.service = service;
    }

    @GetMapping(value = "rest/feign/exchanges")
    public RedirectView getCurrencyComparison(@RequestParam(defaultValue = "RUB") String coin) throws NoSuchFieldException, IllegalAccessException {

        Gif giphy = service.getGiphy(coin);
        return new RedirectView(giphy.getData().get(0).getBitlyGifUrl());
    }
}
