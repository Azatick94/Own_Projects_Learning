package com.test_task.exchanges.client;

import com.test_task.exchanges.dto.Currency;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

//https://stackoverflow.com/questions/43733569/how-can-i-change-the-feign-url-during-the-runtime
@FeignClient(name = "OPEN-EXCHANGE-CLIENT")
public interface OpenExchangeClient {

    @RequestLine("GET")
    Currency getTodayResult(URI TodayUri);

    @RequestLine("GET")
    Currency getYesterdayResult(URI YesterdayUri);
}
