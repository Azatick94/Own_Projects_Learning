package com.test_task.exchanges.service;

import com.test_task.exchanges.util.AppUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.test_task.exchanges.config.GeneralConfigs.EXCHANGE_APP_ID;
import static com.test_task.exchanges.config.GeneralConfigs.EXCHANGE_BASE_URL;

@Service
public class OpenExchangeRestApiService {

    public static Double getCurrency(String urlString, String coin) throws Exception {
        URL url = new URL(urlString);
        Double matchResultDouble = null;

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            String message = con.getResponseMessage();
            if (!message.equals("OK")) {
                throw new Exception("Bad Response from openexchangerates.org");
            }

            // reading response into String
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            String patternString = "\"" + coin + "\": [\\d\\.]+";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(content);

            boolean b = matcher.find();
            if (!b) {
                throw new Exception("Pattern didn't match");
            }

            String matchResult = matcher.group(0);
            matchResultDouble = Double.parseDouble(matchResult.replace("\"" + coin + "\": ", ""));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchResultDouble;
    }

    public static String prepareTodayUrl() {
        return EXCHANGE_BASE_URL +
                "/latest.json?app_id=" +
                EXCHANGE_APP_ID;
    }

    public static String prepareYesterdayUrl() {
        return EXCHANGE_BASE_URL + "/historical/" + AppUtil.getPreviousDate() + ".json?app_id=" + EXCHANGE_APP_ID;
    }
}
