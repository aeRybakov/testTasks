package com.task.bitcoin.bitcoinclient.model;

import java.util.List;

public class DataSetModel {
    private String label;
    private List<Double> data;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
