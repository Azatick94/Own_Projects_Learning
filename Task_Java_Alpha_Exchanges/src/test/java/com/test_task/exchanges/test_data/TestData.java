package com.test_task.exchanges.test_data;

import com.test_task.exchanges.dto.giphy.Datum;
import com.test_task.exchanges.dto.giphy.Gif;
import com.test_task.exchanges.dto.open_exchange.Currency;
import com.test_task.exchanges.dto.open_exchange.Rates;

import java.lang.reflect.Field;
import java.util.Collections;

public class TestData {

    public static Currency getAnyCurrency(double value, String coin) throws NoSuchFieldException, IllegalAccessException {

        // create currency Today
        Currency currency = new Currency();
        Rates rates = new Rates();

        Field f = rates.getClass().getDeclaredField(coin);
        f.setAccessible(true);
        f.set(rates, value);

        currency.setRates(rates);
        return currency;
    }

    public static Gif getAnyGif(String bitlyGifUrl) {
        Gif gif = new Gif();
        Datum datum = new Datum();
        datum.setBitlyGifUrl(bitlyGifUrl);
        gif.setData(Collections.singletonList(datum));
        return gif;
    }


}
