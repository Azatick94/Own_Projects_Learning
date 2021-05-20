package com.test_task.exchanges.client;

import com.test_task.exchanges.dto.open_exchange.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OPEN-EXCHANGE-CLIENT", url = "${openexchange.base_url}")
public interface OpenExchangeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest.json?app_id={app_id}")
    Currency getTodayResult(@RequestParam("app_id") String appId);

    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json?app_id={app_id}")
    Currency getYesterdayResult(@RequestParam("date") String date, @RequestParam("app_id") String appId);
}
