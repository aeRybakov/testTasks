package com.task.bitcoin.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BitcoinModel {

    private Map<String,Double> bpi;
    private String disclaimer;
    private Map<String, String> time;

    public Map<String, Double> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Double> bpi) {
        this.bpi = bpi;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Map<String, String> getTime() {
        return time;
    }

    public void setTime(Map<String, String> time) {
        this.time = time;
    }

}

