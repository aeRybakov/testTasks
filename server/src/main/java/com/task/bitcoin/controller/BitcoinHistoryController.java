package com.task.bitcoin.controller;

import com.task.bitcoin.service.BitcoinHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinHistoryController {
    @Autowired
    BitcoinHistoryService bitcoinHistoryService;

    @GetMapping("/get_history")
    public Map<String, Double> pay(@PathParam(value = "startData") @DateTimeFormat(pattern="yyyy-MM-dd") Date startData, @PathParam(value = "endData") @DateTimeFormat(pattern="yyyy-MM-dd") Date endData) {

        Map<String, Double> result = bitcoinHistoryService.getHistory(startData, endData);

        return result;
    }
}
