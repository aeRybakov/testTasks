package com.task.bitcoin.bitcoinclient.controller;

import be.ceau.chart.BarChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.BarData;
import be.ceau.chart.dataset.BarDataset;
import com.google.gson.Gson;
import com.task.bitcoin.bitcoinclient.model.BitcoinModel;
import com.task.bitcoin.bitcoinclient.model.DataSetModel;
import com.task.bitcoin.bitcoinclient.service.BitcoinHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BitcoinHistoryController {
    @Autowired
    BitcoinHistoryService service;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHistory(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, Model model) {
        if(startDate == null || endDate == null){
            endDate = new Date();
            Calendar instance = Calendar.getInstance();
            instance.setTime(endDate);
            instance.add(Calendar.DAY_OF_MONTH, -10);
            startDate = instance.getTime();
         }
        model.addAttribute("startDate", service.convertDateToString(startDate));
        model.addAttribute("endDate", service.convertDateToString(endDate));
        Map<String, Double> result = service.getHistory(startDate, endDate);
        //creating dataset for chart in json format

        BitcoinModel bitcoinModel = new BitcoinModel();
        List<String> labels = result.keySet().stream().collect(Collectors.toList());
        bitcoinModel.setLabels(labels);
        List<DataSetModel> dataSetModels = new ArrayList<>();
        DataSetModel dataSetModel = new DataSetModel();
        dataSetModel.setLabel("Bitcoin");
        dataSetModel.setData(result.values().stream().collect(Collectors.toList()));
        dataSetModels.add(dataSetModel);
        bitcoinModel.setDatasets(dataSetModels);
        Gson gson = new Gson();
        model.addAttribute("jsonData", gson.toJson(bitcoinModel));

        return "index";
    }


}
