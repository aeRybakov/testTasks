package com.task.bitcoin.bitcoinclient.model;

import java.util.List;

public class BitcoinModel {
    private List<String> labels;
    private List<DataSetModel> datasets;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<DataSetModel> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<DataSetModel> datasets) {
        this.datasets = datasets;
    }
}
