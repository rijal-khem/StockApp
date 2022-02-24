package com.rizal.StockApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AlphaVantageResponse implements Serializable {

    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    public GlobalQuote getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(GlobalQuote globalQuote) {
        this.globalQuote = globalQuote;
    }

    @Override
    public String toString() {
        return "AlphaVantageResponse{" +
                "globalQuote=" + globalQuote +
                '}';
    }
}
