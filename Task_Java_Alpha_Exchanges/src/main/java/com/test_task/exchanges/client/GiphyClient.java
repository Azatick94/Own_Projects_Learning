package com.test_task.exchanges.client;

import com.test_task.exchanges.dto.giphy.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GIPHY-CLIENT", url = "${giphy.base_url}")
public interface GiphyClient {

    @RequestMapping(method = RequestMethod.GET, value = "?{api_key}&limit=1&q=rich")
    Gif getRichLink(@RequestParam("api_key") String apiKey);

    @RequestMapping(method = RequestMethod.GET, value = "?{api_key}&limit=1&q=broke")
    Gif getBrokeLink(@RequestParam("api_key") String apiKey);
}
