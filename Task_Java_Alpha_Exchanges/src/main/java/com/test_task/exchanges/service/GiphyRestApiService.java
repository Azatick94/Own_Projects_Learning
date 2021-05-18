package com.test_task.exchanges.service;

import org.springframework.stereotype.Service;

import static com.test_task.exchanges.config.GeneralConfigs.*;

@Service
public class GiphyRestApiService {

    public static String prepareGiphyUrlRich() {
        return GIPHY_BASE_URL + "?api_key=" + GIPHY_API_KEY + "&limit=" + LIMIT + "&q=" + Q_RICH;
    }

    public static String prepareGiphyUrlBroke() {
        return GIPHY_BASE_URL + "?api_key=" + GIPHY_API_KEY + "&limit=" + LIMIT + "&q=" + Q_BROKE;
    }
}
