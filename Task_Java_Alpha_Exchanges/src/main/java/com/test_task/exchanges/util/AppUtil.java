package com.test_task.exchanges.util;

import com.test_task.exchanges.dto.open_exchange.Currency;
import com.test_task.exchanges.dto.open_exchange.Rates;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.test_task.exchanges.config.GeneralConfigs.*;
import static com.test_task.exchanges.config.GeneralConfigs.Q_BROKE;

public class AppUtil {

    public static String getPreviousDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static Double getCurrencyFromOpenExchangeRates(Currency currency, String coin) throws IllegalAccessException, NoSuchFieldException {
        Class<? extends Rates> c = currency.getRates().getClass();
        Field f = c.getDeclaredField(coin.toLowerCase());
        f.setAccessible(true);
        return (Double) f.get(currency.getRates());
    }

    // Giphy URls preparation
    // ----------------------------------------
    public static String prepareGiphyUrlRich() {
        return GIPHY_BASE_URL + "?api_key=" + GIPHY_API_KEY + "&limit=" + LIMIT + "&q=" + Q_RICH;
    }

    public static String prepareGiphyUrlBroke() {
        return GIPHY_BASE_URL + "?api_key=" + GIPHY_API_KEY + "&limit=" + LIMIT + "&q=" + Q_BROKE;
    }
    // ----------------------------------------

    // OpenExchange URls prepatation
    // ----------------------------------------
    public static String prepareTodayUrl() {
        return EXCHANGE_BASE_URL +
                "/latest.json?app_id=" +
                EXCHANGE_APP_ID;
    }

    public static String prepareYesterdayUrl() {
        return EXCHANGE_BASE_URL + "/historical/" + AppUtil.getPreviousDate() + ".json?app_id=" + EXCHANGE_APP_ID;
    }
    // ----------------------------------------
}
