package com.test_task.exchanges.controller;

import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.service.CurrencyFeignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URISyntaxException;

import static com.test_task.exchanges.config.GeneralConfigs.*;

@Controller
public class CurrencyFeignController {

    CurrencyFeignService service;

    public CurrencyFeignController(CurrencyFeignService service) {
        this.service = service;
    }

    @GetMapping(value = "rest/feign/exchanges")
    public RedirectView getCurrencyComparison(@RequestParam(defaultValue = DEFAULT_COIN) String coin) throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        Gif giphy = service.getGiphy(coin);
        return new RedirectView(giphy.getData().get(0).getBitlyGifUrl());
    }
}
