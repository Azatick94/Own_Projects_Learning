package com.test_task.exchanges.controller;

import com.test_task.exchanges.client.GiphyClient;
import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.service.CurrencyFeignService;
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

import java.net.URISyntaxException;

import static com.test_task.exchanges.config.GeneralConfigs.*;

@Controller
@Import(FeignClientsConfiguration.class)
public class CurrencyFeignController {

    private final CurrencyFeignService service;

    //https://stackoverflow.com/questions/43733569/how-can-i-change-the-feign-url-during-the-runtime
    @Autowired
    public CurrencyFeignController(Decoder decoder, Encoder encoder, GiphyClient giphyClient, OpenExchangeClient openExchangeClient, CurrencyFeignService service) {
        openExchangeClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(OpenExchangeClient.class));
        giphyClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(GiphyClient.class));
        this.service = service;
    }

    @GetMapping(value = "rest/feign/exchanges")
    public RedirectView getCurrencyComparison(@RequestParam(defaultValue = DEFAULT_COIN) String coin) throws URISyntaxException, NoSuchFieldException, IllegalAccessException {

        Gif giphy = service.getGiphy(coin);
        return new RedirectView(giphy.getData().get(0).getBitlyGifUrl());
    }
}
