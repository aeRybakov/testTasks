package com.task.bitcoin.service;

import com.google.gson.Gson;
import com.task.bitcoin.model.BitcoinModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BitcoinHistoryService {
    private static final String BITCOIN_PATH = "https://api.coindesk.com/v1/bpi/historical/close.json?start={starDate}&end={endDate}";

    @Autowired
    RestTemplate restTemplate;

    public Map<String,Double> getHistory(Date startDate, Date endDate){
        if(startDate == null || endDate == null){
            return new HashMap<>();
        }
        if(startDate.getTime() > endDate.getTime()){
            return new HashMap<>();
        }
        String json = restTemplate.getForObject(BITCOIN_PATH, String.class, convertDateToString(startDate),convertDateToString(endDate));
        Gson gson = new Gson();
        BitcoinModel bitcoinModel = gson.fromJson(json, BitcoinModel.class);
        if(bitcoinModel == null){
            return new HashMap<>();
        }

        return bitcoinModel.getBpi();
    }

    private String convertDateToString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
