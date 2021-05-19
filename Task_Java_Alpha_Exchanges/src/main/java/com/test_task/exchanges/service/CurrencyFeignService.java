package com.test_task.exchanges.service;

import com.test_task.exchanges.client.GiphyClient;
import com.test_task.exchanges.client.OpenExchangeClient;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.dto.open_exchange.Currency;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

import static com.test_task.exchanges.util.AppUtil.*;
import static com.test_task.exchanges.util.AppUtil.prepareGiphyUrlBroke;

@Service
@Import(FeignClientsConfiguration.class)
public class CurrencyFeignService {

    OpenExchangeClient openExchangeClient;
    GiphyClient giphyClient;

    //https://stackoverflow.com/questions/43733569/how-can-i-change-the-feign-url-during-the-runtime
    @Autowired
    public CurrencyFeignService(Decoder decoder, Encoder encoder) {
        openExchangeClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(OpenExchangeClient.class));
        giphyClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(GiphyClient.class));
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
