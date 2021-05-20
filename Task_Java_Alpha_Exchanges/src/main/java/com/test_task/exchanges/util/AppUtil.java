package com.test_task.exchanges.util;

import com.test_task.exchanges.dto.open_exchange.Currency;
import com.test_task.exchanges.dto.open_exchange.Rates;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
}
