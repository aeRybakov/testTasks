package com.task.bitcoin.bitcoinclient.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BitcoinHistoryService {
    private static final String SERVER_PATH = "http://127.0.0.1:8081/bitcoin/get_history?startData={starDate}&&endData={endDate}";

    @Autowired
    RestTemplate restTemplate;

    public Map<String,Double> getHistory(Date startDate, Date endDate){
        if(startDate == null || endDate == null){
            return new HashMap<>();
        }
        if(startDate.getTime() > endDate.getTime()){
            return new HashMap<>();
        }
        try {
            String json = restTemplate.getForObject(SERVER_PATH, String.class, convertDateToString(startDate), convertDateToString(endDate));
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Double>>(){}.getType();
            Map<String,Double> map = gson.fromJson(json, type);
            if(map == null){
                return new HashMap<>();
            }

            return map;
        }catch (Exception ex){
            return new HashMap<>();
        }



    }

    public String convertDateToString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}

