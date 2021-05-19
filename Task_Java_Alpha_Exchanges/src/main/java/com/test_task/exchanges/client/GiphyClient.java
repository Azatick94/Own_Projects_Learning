package com.test_task.exchanges.client;


import com.test_task.exchanges.dto.giphy.Gif;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

//https://stackoverflow.com/questions/43733569/how-can-i-change-the-feign-url-during-the-runtime
@FeignClient(name = "GIPHY-CLIENT")
public interface GiphyClient {

    @RequestLine("GET")
    Gif getRichLink(URI RichUri);

    @RequestLine("GET")
    Gif getBrokeLink(URI BrokeUri);
}
